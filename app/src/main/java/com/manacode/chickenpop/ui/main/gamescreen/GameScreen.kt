package com.manacode.chickenpop.ui.main.gamescreen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manacode.chickenpop.audio.rememberAudioController
import com.manacode.chickenpop.ui.main.component.GradientOutlinedText
import com.manacode.chickenpop.ui.main.component.SecondaryIconButton
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                TopHud(
                    timeSeconds = state.remainingSeconds,
                    score = state.score,
                    combo = state.combo,
                    speed = state.speedLevel,
                    onPause = viewModel::openPause
                )

                Spacer(modifier = Modifier.height(12.dp))

                GameBoard(
                    chickens = state.chickens,
                    combo = state.combo,
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
            }

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
    speed: Int,
    onPause: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfoBadge(
            title = "Time",
            value = String.format("%02d", timeSeconds.coerceAtLeast(0)),
            colors = listOf(Color(0xFF6CD4FF), Color(0xFF2E9CC9))
        )

        Spacer(modifier = Modifier.width(12.dp))

        AnimatedVisibility(visible = combo >= 2, enter = fadeIn(), exit = fadeOut()) {
            ComboBadge(combo = combo)
        }

        Spacer(modifier = Modifier.weight(1f))

        if (speed > 0) {
            AnimatedVisibility(visible = true) {
                SpeedBadge(level = speed)
            }
            Spacer(modifier = Modifier.width(12.dp))
        }

        InfoBadge(
            title = "Score",
            value = score.toString(),
            colors = listOf(Color(0xFFFFE68D), Color(0xFFF5A623))
        )

        Spacer(modifier = Modifier.width(12.dp))

        SecondaryIconButton(
            onClick = onPause,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Pause,
                contentDescription = "Pause",
                tint = Color.White,
                modifier = Modifier.fillMaxSize(0.7f)
            )
        }
    }
}

@Composable
private fun InfoBadge(title: String, value: String, colors: List<Color>) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.verticalGradient(colors))
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = Color.White.copy(alpha = 0.85f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
private fun ComboBadge(combo: Int) {
    val glow by animateFloatAsState(
        targetValue = if (combo > 0) 1.15f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 420, easing = { it }),
            repeatMode = RepeatMode.Reverse
        ),
        label = "combo_glow"
    )
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(Brush.horizontalGradient(listOf(Color(0xFFFF9A9E), Color(0xFFFECF4F))))
            .graphicsLayer { scaleX = glow; scaleY = glow }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(12.dp, RoundedCornerShape(18.dp), clip = false),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Combo x$combo",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.align(Alignment.Center)
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
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.horizontalGradient(listOf(Color(0xFFB3FFAB), Color(0xFF65C764))))
            .graphicsLayer { scaleX = 0.95f + oscillate * 0.05f; scaleY = 0.95f + oscillate * 0.05f }
            .padding(horizontal = 14.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Speed ${level + 1}",
            color = Color(0xFF12451F),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun GameBoard(
    chickens: List<GameViewModel.Chicken>,
    combo: Int,
    effects: List<CellEffect>,
    onTapSlot: (Int) -> Unit,
    onEffectConsumed: (CellEffect) -> Unit,
) {
    val chickenBySlot = remember(chickens) { chickens.associateBy { it.slotIndex } }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(4) { rowIndex ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(3) { columnIndex ->
                    val slot = rowIndex * 3 + columnIndex
                    val chicken = chickenBySlot[slot]
                    val effect = effects.firstOrNull { it.slot == slot }
                    ChickenCell(
                        chicken = chicken,
                        combo = combo,
                        effect = effect,
                        onTap = { onTapSlot(slot) },
                        onEffectConsumed = onEffectConsumed
                    )
                }
            }
        }
    }
}

@Composable
private fun ChickenCell(
    chicken: GameViewModel.Chicken?,
    combo: Int,
    effect: CellEffect?,
    onTap: () -> Unit,
    onEffectConsumed: (CellEffect) -> Unit,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .aspectRatio(0.9f)
            .clip(RoundedCornerShape(22.dp))
            .background(Color(0xFFFFECD1))
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onTap() })
            }
            .padding(8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        FarmSlotDecor()

        AnimatedVisibility(
            visible = chicken != null,
            enter = scaleIn(animationSpec = tween(160)) + fadeIn(tween(160)),
            exit = fadeOut(tween(120))
        ) {
            ChickenSprite(speedLevel = chicken?.speedLevel ?: 0, combo = combo)
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
private fun FarmSlotDecor() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val roofPath = Path().apply {
            moveTo(size.width * 0.5f, size.height * 0.08f)
            lineTo(size.width * 0.1f, size.height * 0.32f)
            lineTo(size.width * 0.9f, size.height * 0.32f)
            close()
        }
        drawPath(roofPath, color = Color(0xFFCA4536))

        drawRect(
            color = Color(0xFFFFF6E8),
            topLeft = Offset(size.width * 0.12f, size.height * 0.32f),
            size = Size(size.width * 0.76f, size.height * 0.48f)
        )

        drawRect(
            color = Color(0xFF8D5C2E),
            topLeft = Offset(size.width * 0.42f, size.height * 0.52f),
            size = Size(size.width * 0.16f, size.height * 0.28f)
        )

        drawRoundRect(
            color = Color(0xFF4CAF50),
            topLeft = Offset(size.width * 0.08f, size.height * 0.78f),
            size = Size(size.width * 0.84f, size.height * 0.16f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(size.height * 0.08f)
        )
    }
}

@Composable
private fun ChickenSprite(speedLevel: Int, combo: Int) {
    val pulse by animateFloatAsState(
        targetValue = if (combo >= 3) 1.08f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(420, easing = { it }),
            repeatMode = RepeatMode.Reverse
        ),
        label = "chicken_pulse"
    )
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .aspectRatio(1f)
            .shadow(10.dp, CircleShape, clip = false)
            .graphicsLayer { scaleX = pulse; scaleY = pulse }
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.85f)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize(0.85f)) {
            val baseColor = Color(0xFFFFF7E0)
            val outline = Color(0xFF6D3A16)
            val comb = Color(0xFFE53935)
            val beak = Color(0xFFFFB74D)

            val bodyScale = if (combo >= 3) 1.05f else 1f

            drawOval(
                color = baseColor,
                topLeft = Offset(size.width * 0.22f, size.height * 0.32f),
                size = Size(size.width * 0.56f, size.height * 0.58f * bodyScale)
            )

            drawCircle(color = baseColor, radius = size.width * 0.22f, center = Offset(size.width / 2f, size.height * 0.38f))
            drawCircle(color = outline.copy(alpha = 0.25f), radius = size.width * 0.22f, center = Offset(size.width / 2f, size.height * 0.38f), style = Stroke(width = size.minDimension * 0.045f))

            drawCircle(color = Color.Black, radius = size.width * 0.04f, center = Offset(size.width * 0.44f, size.height * 0.36f))
            drawCircle(color = Color.White, radius = size.width * 0.015f, center = Offset(size.width * 0.42f, size.height * 0.35f))
            drawCircle(color = Color.Black, radius = size.width * 0.04f, center = Offset(size.width * 0.56f, size.height * 0.36f))
            drawCircle(color = Color.White, radius = size.width * 0.015f, center = Offset(size.width * 0.58f, size.height * 0.35f))

            val beakPath = Path().apply {
                moveTo(size.width * 0.48f, size.height * 0.42f)
                lineTo(size.width / 2f, size.height * 0.47f)
                lineTo(size.width * 0.52f, size.height * 0.42f)
                close()
            }
            drawPath(beakPath, color = beak)

            val combPath = Path().apply {
                moveTo(size.width * 0.45f, size.height * 0.25f)
                cubicTo(size.width * 0.44f, size.height * 0.20f, size.width * 0.49f, size.height * 0.17f, size.width * 0.5f, size.height * 0.21f)
                cubicTo(size.width * 0.51f, size.height * 0.17f, size.width * 0.56f, size.height * 0.2f, size.width * 0.55f, size.height * 0.25f)
            }
            drawPath(combPath, color = comb, style = Stroke(width = size.minDimension * 0.05f, cap = androidx.compose.ui.graphics.StrokeCap.Round))

            drawOval(
                color = outline,
                topLeft = Offset(size.width * 0.44f, size.height * 0.63f),
                size = Size(size.width * 0.12f, size.height * 0.18f)
            )
        }
    }
}

@Composable
private fun GameBackground() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(Brush.verticalGradient(listOf(Color(0xFF87CEEB), Color(0xFF87CEEB), Color(0xFFBDF29A))), size = size)

        val hills = Path().apply {
            moveTo(-size.width * 0.2f, size.height * 0.65f)
            quadraticBezierTo(size.width * 0.2f, size.height * 0.48f, size.width * 0.5f, size.height * 0.58f)
            quadraticBezierTo(size.width * 0.85f, size.height * 0.75f, size.width * 1.2f, size.height * 0.6f)
            lineTo(size.width * 1.2f, size.height)
            lineTo(-size.width * 0.2f, size.height)
            close()
        }
        drawPath(hills, brush = Brush.verticalGradient(listOf(Color(0xFF8BC34A), Color(0xFF558B2F))))

        val ground = Path().apply {
            moveTo(-size.width * 0.1f, size.height * 0.82f)
            quadraticBezierTo(size.width * 0.3f, size.height * 0.88f, size.width * 0.6f, size.height * 0.8f)
            quadraticBezierTo(size.width * 0.9f, size.height * 0.74f, size.width * 1.1f, size.height * 0.86f)
            lineTo(size.width * 1.1f, size.height)
            lineTo(-size.width * 0.1f, size.height)
            close()
        }
        drawPath(ground, brush = Brush.verticalGradient(listOf(Color(0xFFFFD180), Color(0xFFFFAB40))))
    }
}
