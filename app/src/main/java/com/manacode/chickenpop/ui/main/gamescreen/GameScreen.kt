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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
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
                    onPause = viewModel::openPause,
                    speed = state.speedLevel
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
    combo: Int,
    effects: List<CellEffect>,
    onTapSlot: (Int) -> Unit,
    onEffectConsumed: (CellEffect) -> Unit,
) {
    val chickenBySlot = remember(chickens) { chickens.associateBy { it.slotIndex } }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        val columns = 3
        val rows = 4
        val spacing = 12.dp
        val framePadding = 36.dp
        val innerWidth = remember(maxWidth) {
            val width = maxWidth - framePadding
            if (width < 0.dp) 0.dp else width
        }
        val cellSize = remember(innerWidth) {
            val totalSpacing = spacing * (columns - 1)
            val available = innerWidth - totalSpacing
            val safeWidth = if (available < 0.dp) 0.dp else available
            safeWidth / columns
        }
        val boardHeight = remember(cellSize) { cellSize * rows + spacing * (rows - 1) }

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF6BC54A), Color(0xFF3E8A28))
                    )
                )
                .border(4.dp, Color(0xFF215016), RoundedCornerShape(32.dp))
                .padding(18.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(boardHeight + 24.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(26.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFECFBD2), Color(0xFFC6EA8F))
                        )
                    )
                    .border(3.dp, Color(0xFF3F6E1E), RoundedCornerShape(26.dp))
                    .padding(12.dp),
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
                                combo = combo,
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
    combo: Int,
    effect: CellEffect?,
    onTap: () -> Unit,
    onEffectConsumed: (CellEffect) -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(22.dp))
            .shadow(12.dp, RoundedCornerShape(22.dp), clip = false, ambientColor = Color(0x66331F05), spotColor = Color(0x55331F05))
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFF3D7), Color(0xFFFFD7A3))
                )
            )
            .border(3.dp, Color(0xFF7C4815), RoundedCornerShape(22.dp))
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
        val borderColor = Color(0xFF4F2A0F)
        drawRoundRect(
            color = borderColor,
            size = size,
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(size.minDimension * 0.08f),
            style = Stroke(width = size.minDimension * 0.04f)
        )

        val roofPath = Path().apply {
            moveTo(size.width * 0.5f, size.height * 0.12f)
            lineTo(size.width * 0.1f, size.height * 0.36f)
            lineTo(size.width * 0.9f, size.height * 0.36f)
            close()
        }
        drawPath(
            path = roofPath,
            brush = Brush.verticalGradient(listOf(Color(0xFFD24731), Color(0xFF781B11)))
        )

        drawRect(
            color = Color(0xFFFCEFDA),
            topLeft = Offset(size.width * 0.18f, size.height * 0.36f),
            size = Size(size.width * 0.64f, size.height * 0.38f)
        )

        val plankColor = Color(0xFFE7B676)
        repeat(3) { index ->
            val y = size.height * (0.38f + index * 0.12f)
            drawRect(
                color = plankColor.copy(alpha = 0.7f),
                topLeft = Offset(size.width * 0.18f, y),
                size = Size(size.width * 0.64f, size.height * 0.04f)
            )
        }

        drawRect(
            color = Color(0xFF6F4215),
            topLeft = Offset(size.width * 0.44f, size.height * 0.54f),
            size = Size(size.width * 0.12f, size.height * 0.20f)
        )

        val windowColor = Color(0xFF87B6FF)
        drawRoundRect(
            color = windowColor,
            topLeft = Offset(size.width * 0.24f, size.height * 0.44f),
            size = Size(size.width * 0.14f, size.height * 0.12f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(size.minDimension * 0.02f)
        )
        drawRoundRect(
            color = windowColor,
            topLeft = Offset(size.width * 0.62f, size.height * 0.44f),
            size = Size(size.width * 0.14f, size.height * 0.12f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(size.minDimension * 0.02f)
        )

        drawRoundRect(
            color = Color(0xFF4CAF50),
            topLeft = Offset(size.width * 0.1f, size.height * 0.76f),
            size = Size(size.width * 0.8f, size.height * 0.16f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(size.height * 0.08f)
        )

        drawCircle(
            color = Color(0xFF3E8A28),
            radius = size.width * 0.08f,
            center = Offset(size.width * 0.28f, size.height * 0.86f)
        )
        drawCircle(
            color = Color(0xFF3E8A28),
            radius = size.width * 0.06f,
            center = Offset(size.width * 0.7f, size.height * 0.84f)
        )
    }
}

@Composable
fun ChickenSprite(speedLevel: Int, combo: Int) {
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
            .background(Color.White.copy(alpha = 0.9f)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize(0.88f)) {
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
        drawRect(
            Brush.verticalGradient(
                listOf(Color(0xFF6EC9FF), Color(0xFF9EE8FF), Color(0xFFF2FFB7))
            ),
            size = size
        )

        val sunCenter = Offset(size.width * 0.82f, size.height * 0.22f)
        drawCircle(Color(0xFFFFF59D), radius = size.minDimension * 0.1f, center = sunCenter)
        drawCircle(Color(0x80FFF59D), radius = size.minDimension * 0.18f, center = sunCenter)

        val distantHill = Path().apply {
            moveTo(-size.width * 0.2f, size.height * 0.65f)
            quadraticBezierTo(size.width * 0.3f, size.height * 0.45f, size.width * 0.65f, size.height * 0.62f)
            quadraticBezierTo(size.width * 1.05f, size.height * 0.52f, size.width * 1.2f, size.height * 0.68f)
            lineTo(size.width * 1.2f, size.height)
            lineTo(-size.width * 0.2f, size.height)
            close()
        }
        drawPath(distantHill, brush = Brush.verticalGradient(listOf(Color(0xFF70C46A), Color(0xFF3E8A28))))

        val foreground = Path().apply {
            moveTo(-size.width * 0.1f, size.height * 0.85f)
            quadraticBezierTo(size.width * 0.25f, size.height * 0.78f, size.width * 0.5f, size.height * 0.86f)
            quadraticBezierTo(size.width * 0.85f, size.height * 0.94f, size.width * 1.1f, size.height * 0.82f)
            lineTo(size.width * 1.1f, size.height)
            lineTo(-size.width * 0.1f, size.height)
            close()
        }
        drawPath(foreground, brush = Brush.verticalGradient(listOf(Color(0xFF8BC34A), Color(0xFF4CAF50))))

        repeat(5) { index ->
            val startX = size.width * (0.05f + index * 0.2f)
            drawLine(
                color = Color(0x33FFFFFF),
                start = Offset(startX, size.height * 0.18f + index * 6f),
                end = Offset(startX + size.width * 0.08f, size.height * 0.2f + index * 6f),
                strokeWidth = size.minDimension * 0.003f
            )
        }
    }
}
