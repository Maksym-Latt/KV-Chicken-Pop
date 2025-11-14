package com.manacode.chickenpop.ui.main.gamescreen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.manacode.chickenpop.audio.rememberAudioController
import com.manacode.chickenpop.R
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
    val state by viewModel.state.collectAsState()
    val audio = rememberAudioController()
    LaunchedEffect(Unit) {
        audio.playGameMusic()
        viewModel.startGame()
    }

    LaunchedEffect(state.showGameOver) {
        if (state.showGameOver) {
            audio.playGameWin()
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

    BackHandler(enabled = !state.showPause && !state.showGameOver) {
        viewModel.openPause()
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
                    timeSeconds = state.remainingSeconds,
                    score = state.score,
                    combo = state.combo,
                    onPause = viewModel::openPause,
                    speed = state.speedLevel
                )

                Spacer(modifier = Modifier.height(12.dp))

                GameBoard(
                    chickens = state.chickens,
                    effects = effects,
                    onTapSlot = { slot ->
                        when (val outcome = viewModel.tap(slot)) {
                            is TapOutcome.Hit -> {
                                recentHitIds += outcome.chickenId
                                triggerEffect(slot, EffectType.Hit)
                            }
                            TapOutcome.Miss -> triggerEffect(slot, EffectType.Miss)
                        }
                    },
                    onEffectConsumed = { effect -> effects.remove(effect) }
                )

                Spacer(modifier = Modifier.height(96.dp))
            }

            GameForegroundChicken()

            if (state.showPause) {
                GameSettingsOverlay(
                    onResume = viewModel::resume,
                    onRetry = viewModel::retry,
                    onHome = {
                        viewModel.finishEarly()
                        onExitToMenu(state.score)
                    }
                )
            }

            if (state.showGameOver) {
                WinOverlay(
                    score = state.score,
                    combo = state.bestCombo,
                    onRetry = viewModel::retry,
                    onHome = { onExitToMenu(state.score) }
                )
            }
        }
    }
}

private data class CellEffect(val id: Long, val slot: Int, val type: EffectType)

private enum class EffectType { Hit, Miss, Escape }

@Composable
private fun TopHud(
    timeSeconds: Int,
    score: Int,
    combo: Int,
    onPause: () -> Unit,
    speed: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ScoreBoard(
                score = score,
                timeSeconds = timeSeconds,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            MenuButton(onPause = onPause)
        }

        val showBottomRow = combo >= 2 || speed > 0
        if (showBottomRow) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(visible = combo >= 2, enter = fadeIn(), exit = fadeOut()) {
                    ComboPlank(combo = combo)
                }

                AnimatedVisibility(visible = speed > 0, enter = fadeIn(), exit = fadeOut()) {
                    SpeedBadge(level = speed)
                }
            }
        }
    }
}

@Composable
private fun ScoreBoard(score: Int, timeSeconds: Int, modifier: Modifier = Modifier) {
    val timeFormatted = run {
        val safeSeconds = timeSeconds.coerceAtLeast(0)
        val minutes = safeSeconds / 60
        val seconds = safeSeconds % 60
        String.format("%d:%02d", minutes, seconds)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(26.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFAA652D), Color(0xFF6B3C16))
                )
            )
            .border(2.dp, Color(0xFF3E1D0A), RoundedCornerShape(26.dp))
            .padding(horizontal = 20.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Score: ${score.toString().padStart(4, '0')}",
                color = Color(0xFFFFF3D9),
                fontSize = 26.sp,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Time: $timeFormatted",
                color = Color(0xFFFFF3D9),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun MenuButton(onPause: () -> Unit) {
    val shape = RoundedCornerShape(18.dp)
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val gradient = if (pressed) {
        Brush.verticalGradient(listOf(Color(0xFFE8952D), Color(0xFF8C3C08)))
    } else {
        Brush.verticalGradient(listOf(Color(0xFFFFD27F), Color(0xFFDA7F21)))
    }

    Box(
        modifier = Modifier
            .shadow(18.dp, shape, spotColor = Color(0x8043210C), clip = false)
            .clip(shape)
            .background(gradient)
            .border(2.dp, Color(0xFF5A2A09), shape)
            .clickable(
                interactionSource = interaction,
                indication = null,
                onClick = onPause
            )
            .padding(horizontal = 22.dp, vertical = 12.dp)
            .widthIn(min = 96.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "MENU",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
private fun ComboPlank(combo: Int) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFC56C), Color(0xFFB45B1F))
                )
            )
            .border(2.dp, Color(0xFF4F250C), RoundedCornerShape(18.dp))
            .padding(horizontal = 18.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Combo x$combo",
            color = Color(0xFF3E1F0A),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun SpeedBadge(level: Int) {
    val oscillate by animateFloatAsState(
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = { it }),
            repeatMode = RepeatMode.Reverse
        ),
        label = "speed_badge"
    )
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(Brush.verticalGradient(listOf(Color(0xFFBFFFA8), Color(0xFF5FB93A))))
            .graphicsLayer { scaleX = 0.94f + oscillate * 0.06f; scaleY = 0.94f + oscillate * 0.06f }
            .border(2.dp, Color(0xFF2D5E1A), RoundedCornerShape(18.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Speed ${level + 1}",
            color = Color(0xFF14440F),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
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
    Box(
        modifier = modifier
            .shadow(12.dp, shape, clip = false, ambientColor = Color(0x33231105), spotColor = Color(0x33231105))
            .clip(shape)
            .background(Color.Transparent)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onTap() })
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.tile_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        val stateRes = remember(chicken) {
            when (chicken?.phase) {
                GameViewModel.ChickenPhase.Peeking -> R.drawable.tile_house_with_chicken
                GameViewModel.ChickenPhase.Outside -> R.drawable.tile_house_outside_chicken
                else -> R.drawable.tile_house
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
