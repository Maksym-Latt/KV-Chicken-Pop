package com.manacode.chickenpop.ui.main.gamescreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Icon
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manacode.chickenpop.audio.rememberAudioController
import com.manacode.chickenpop.R
import com.manacode.chickenpop.game.GameEngine
import com.manacode.chickenpop.ui.main.component.GradientOutlinedText
import com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.TapOutcome
import com.manacode.chickenpop.ui.main.gamescreen.overlay.GameSettingsOverlay
import com.manacode.chickenpop.ui.main.gamescreen.overlay.WinOverlay
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    onExitToMenu: (Int) -> Unit,
    viewModel: GameViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val audio = rememberAudioController()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.startGame()
        audio.playGameMusic()
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                val current = viewModel.state.value
                if (current.phase == GameViewModel.GamePhase.Running) {
                    viewModel.pause()
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(state.phase) {
        when (state.phase) {
            GameViewModel.GamePhase.Running -> audio.resumeMusic()
            GameViewModel.GamePhase.Paused -> audio.pauseMusic()
            GameViewModel.GamePhase.Result -> {
                audio.stopMusic()
                audio.playGameWin()
            }
            GameViewModel.GamePhase.Intro -> audio.stopMusic()
        }
    }

    val effects = remember { mutableStateListOf<CellEffect>() }
    var effectCounter by remember { mutableLongStateOf(0L) }
    var previousChickens by remember { mutableStateOf<Map<Int, Int>>(emptyMap()) }
    val recentHitIds = remember { mutableStateListOf<Int>() }

    fun triggerEffect(slot: Int, type: EffectType) {
        val effect = CellEffect(id = effectCounter++, slot = slot, type = type)
        effects += effect
    }

    LaunchedEffect(state.chickens) {
        val current = state.chickens.associate { it.id to it.slotIndex }
        val removed = previousChickens.filterKeys { it !in current }
        removed.forEach { (id, slot) ->
            if (recentHitIds.contains(id)) {
                recentHitIds.remove(id)
            } else {
                triggerEffect(slot, EffectType.Escape)
            }
        }
        recentHitIds.removeAll { it !in current.keys }
        previousChickens = current
    }

    BackHandler {
        when (state.phase) {
            GameViewModel.GamePhase.Running -> viewModel.pause()
            GameViewModel.GamePhase.Paused -> viewModel.resume()
            GameViewModel.GamePhase.Result, GameViewModel.GamePhase.Intro -> {
                val finalScore = state.score
                viewModel.exitToMenu()
                onExitToMenu(finalScore)
            }
        }
    }

    Surface(color = Color(0xFFFFF4D9)) {
        Box(modifier = Modifier.fillMaxSize()) {
            GameBackground()
            BackgroundClouds()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                TopHud(
                    phase = state.phase,
                    timeSeconds = state.remainingSeconds,
                    score = state.score,
                    combo = state.combo,
                    speed = state.speedLevel,
                    rareHits = state.rareHits,
                    onPause = viewModel::pause
                )

                Spacer(modifier = Modifier.height(12.dp))

                GameBoard(
                    chickens = state.chickens,
                    effects = effects,
                    onTapSlot = { slot ->
                        when (val outcome = viewModel.tap(slot)) {
                            is TapOutcome.Hit -> {
                                recentHitIds += outcome.chickenId
                                val effectType = if (outcome.type == GameEngine.ChickenType.Rare) {
                                    EffectType.Rare
                                } else {
                                    EffectType.Hit
                                }
                                triggerEffect(slot, effectType)
                            }
                            TapOutcome.Miss -> triggerEffect(slot, EffectType.Miss)
                        }
                    },
                    onEffectConsumed = { effect -> effects.remove(effect) }
                )

                Spacer(modifier = Modifier.height(96.dp))
            }

            GameForegroundChicken()

            if (state.phase == GameViewModel.GamePhase.Paused) {
                GameSettingsOverlay(
                    onResume = viewModel::resume,
                    onRetry = viewModel::retry,
                    onHome = {
                        val finalScore = state.score
                        viewModel.exitToMenu()
                        onExitToMenu(finalScore)
                    }
                )
            }

            if (state.phase == GameViewModel.GamePhase.Result) {
                WinOverlay(
                    score = state.score,
                    combo = state.bestCombo,
                    onRetry = viewModel::retry,
                    onHome = {
                        val finalScore = state.score
                        viewModel.exitToMenu()
                        onExitToMenu(finalScore)
                    }
                )
            }
        }
    }
}

private data class CellEffect(val id: Long, val slot: Int, val type: EffectType)

private enum class EffectType { Hit, Miss, Escape, Rare }

@Composable
private fun TopHud(
    phase: GameViewModel.GamePhase,
    timeSeconds: Int,
    score: Int,
    combo: Int,
    speed: Int,
    rareHits: Int,
    onPause: () -> Unit,
) {
    val canPause = phase == GameViewModel.GamePhase.Running
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PauseButton(
                enabled = canPause,
                onClick = onPause,
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            ScoreBoard(
                score = score,
                timeSeconds = timeSeconds,
                modifier = Modifier.weight(1f)
            )
        }

        StatusRow(
            combo = combo,
            speed = speed,
            rareHits = rareHits
        )
    }
}

@Composable
private fun PauseButton(enabled: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val interaction = remember { MutableInteractionSource() }
    val shape = CircleShape
    val background = if (enabled) {
        Brush.verticalGradient(listOf(Color(0xFFFFD27F), Color(0xFFE6801C)))
    } else {
        Brush.verticalGradient(listOf(Color(0xFFE4C7A4), Color(0xFFB68A56)))
    }
    Box(
        modifier = modifier
            .shadow(18.dp, shape, clip = false, spotColor = Color(0x8843210C))
            .clip(shape)
            .background(background)
            .border(2.dp, Color(0xFF5A2A09), shape)
            .clickable(
                enabled = enabled,
                interactionSource = interaction,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Pause,
            contentDescription = "Pause",
            tint = Color.White,
            modifier = Modifier.fillMaxSize(0.52f)
        )
    }
}

@Composable
private fun ScoreBoard(score: Int, timeSeconds: Int, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(26.dp)
    val safeScore = score.coerceAtLeast(0)
    val scoreText = safeScore.toString().padStart(4, '0')
    val timeFormatted = run {
        val safeSeconds = timeSeconds.coerceAtLeast(0)
        val minutes = safeSeconds / 60
        val seconds = safeSeconds % 60
        String.format("%d:%02d", minutes, seconds)
    }

    Box(
        modifier = modifier
            .shadow(16.dp, shape, clip = false, spotColor = Color(0x553E1D0A))
            .clip(shape)
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFB347), Color(0xFFB46018))
                )
            )
            .border(2.dp, Color(0xFF3E1D0A), shape)
            .padding(horizontal = 22.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Score $scoreText",
                color = Color(0xFFFFF3D9),
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "Time $timeFormatted",
                color = Color(0xFFFFF3D9),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun StatusRow(combo: Int, speed: Int, rareHits: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        InfoBadge(
            label = "Combo",
            value = "x$combo",
            highlight = combo >= 2,
            modifier = Modifier.weight(1f)
        )
        InfoBadge(
            label = "Speed",
            value = (speed + 1).toString(),
            highlight = speed > 0,
            modifier = Modifier.weight(1f)
        )
        InfoBadge(
            label = "Rare",
            value = rareHits.toString(),
            highlight = rareHits > 0,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun InfoBadge(
    label: String,
    value: String,
    highlight: Boolean,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(18.dp)
    val gradient = if (highlight) {
        Brush.verticalGradient(listOf(Color(0xFFFFC56C), Color(0xFFB45B1F)))
    } else {
        Brush.verticalGradient(listOf(Color(0xFFECD7BC), Color(0xFFC59A6A)))
    }
    val labelColor = if (highlight) Color(0xFF3E1F0A) else Color(0xFF6B4A2F)
    val valueColor = if (highlight) Color.White else Color(0xFF3E2A1A)

    Box(
        modifier = modifier
            .clip(shape)
            .background(gradient)
            .border(2.dp, Color(0xFF4F250C), shape)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = label,
                color = labelColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = value,
                color = valueColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
private fun GameBoard(
    chickens: List<GameViewModel.Chicken>,
    effects: List<CellEffect>,
    onTapSlot: (Int) -> Unit,
    onEffectConsumed: (CellEffect) -> Unit,
) {
    val chickenBySlot = remember(chickens) { chickens.associateBy { it.slotIndex } }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        val columns = 3
        val rows = 4
        val spacing = 16.dp
        val cellSize = remember(maxWidth) {
            val totalSpacing = spacing * (columns - 1)
            val available = maxWidth - totalSpacing
            val safeWidth = if (available < 0.dp) 0.dp else available
            safeWidth / columns
        }
        val boardWidth = remember(cellSize) { cellSize * columns + spacing * (columns - 1) }

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .width(boardWidth)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing)
            ) {
                repeat(rows) { rowIndex ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(spacing)
                    ) {
                        repeat(columns) { columnIndex ->
                            val slot = rowIndex * columns + columnIndex
                            val chicken = chickenBySlot[slot]
                            val effect = effects.firstOrNull { it.slot == slot }
                            ChickenCell(
                                chicken = chicken,
                                effect = effect,
                                onTap = { onTapSlot(slot) },
                                onEffectConsumed = onEffectConsumed,
                                modifier = Modifier.size(cellSize)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChickenCell(
    chicken: GameViewModel.Chicken?,
    effect: CellEffect?,
    onTap: () -> Unit,
    onEffectConsumed: (CellEffect) -> Unit,
    modifier: Modifier
) {
    val shape = RoundedCornerShape(20.dp)
    val interaction = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .shadow(12.dp, shape, clip = false, ambientColor = Color(0x33231105), spotColor = Color(0x33231105))
            .clip(shape)
            .background(Color.Transparent)
            .clickable(
                interactionSource = interaction,
                indication = null,
                onClick = onTap
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.tile_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        val stateRes = remember(chicken) {
            when {
                chicken == null -> R.drawable.tile_house
                chicken.isRare -> R.drawable.tile_house_outside_chicken
                else -> R.drawable.tile_house_with_chicken
            }
        }

        Crossfade(targetState = stateRes, modifier = Modifier.fillMaxSize(), label = "chicken_state") { resId ->
            Image(
                painter = painterResource(id = resId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        if (chicken?.isRare == true) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(3.dp, Color(0xFFFFE082), shape)
            )
        }

        if (effect != null) {
            EffectOverlay(effect = effect, onEffectConsumed = onEffectConsumed)
        }
    }
}

@Composable
private fun EffectOverlay(effect: CellEffect, onEffectConsumed: (CellEffect) -> Unit) {
    LaunchedEffect(effect.id) {
        delay(320)
        onEffectConsumed(effect)
    }

    when (effect.type) {
        EffectType.Hit -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                GradientOutlinedText(
                    text = "POW!",
                    fontSize = 26.sp,
                    gradientColors = listOf(Color(0xFFFFF176), Color(0xFFFF5722))
                )
            }
        }
        EffectType.Rare -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                GradientOutlinedText(
                    text = "+100",
                    fontSize = 28.sp,
                    gradientColors = listOf(Color(0xFFFFF9C4), Color(0xFFFFC107))
                )
            }
        }
        EffectType.Miss, EffectType.Escape -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(72.dp)) {
                    val base = if (effect.type == EffectType.Miss) Color(0xFFB0BEC5) else Color(0xFFC8E6C9)
                    repeat(4) { index ->
                        val offsetX = size.width / 2f + (index - 1.5f) * size.width * 0.15f
                        val offsetY = size.height / 2f + (index % 2 - 0.5f) * size.height * 0.12f
                        drawCircle(
                            color = base.copy(alpha = 0.6f - index * 0.1f),
                            radius = size.minDimension * (0.32f + index * 0.04f),
                            center = Offset(offsetX, offsetY)
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun GameBackground() {
    Image(
        painter = painterResource(id = R.drawable.bg_menu),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun BoxScope.BackgroundClouds() {
    Image(
        painter = painterResource(id = R.drawable.cloud),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.TopStart)
            .padding(start = 24.dp, top = 48.dp)
            .fillMaxWidth(0.4f),
        contentScale = ContentScale.Fit,
        alpha = 0.9f
    )

    Image(
        painter = painterResource(id = R.drawable.cloud),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 64.dp)
            .fillMaxWidth(0.32f),
        contentScale = ContentScale.Fit,
        alpha = 0.85f
    )

    Image(
        painter = painterResource(id = R.drawable.cloud),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(end = 24.dp, top = 56.dp)
            .fillMaxWidth(0.36f),
        contentScale = ContentScale.Fit,
        alpha = 0.8f
    )
}

@Composable
private fun BoxScope.GameForegroundChicken() {
    Image(
        painter = painterResource(id = R.drawable.chicken),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 12.dp)
            .fillMaxWidth(0.4f),
        contentScale = ContentScale.Fit
    )
}
