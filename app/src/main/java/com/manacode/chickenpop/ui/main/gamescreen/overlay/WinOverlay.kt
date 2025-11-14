package com.manacode.chickenpop.ui.main.gamescreen.overlay

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manacode.chickenpop.ui.main.gamescreen.ChickenSprite
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WinOverlay(
    score: Int,
    combo: Int,
    onRetry: () -> Unit,
    onHome: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xCC0B1B28), Color(0xE6000000))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        val cardShape = RoundedCornerShape(36.dp)
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clip(cardShape)
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF244C6A), Color(0xFF162E44))
                    )
                )
                .border(4.dp, Color(0xFF0A1A27), cardShape)
                .padding(horizontal = 28.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            Text(
                text = "SCORE",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFEE9A)
            )
            Text(
                text = score.toString(),
                fontSize = 56.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFFFFC857)
            )

            AnimatedVisibility(visible = combo > 1, enter = fadeIn(), exit = fadeOut()) {
                Text(
                    text = "Best combo x$combo",
                    color = Color(0xFFB3E5FC),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            GameOverChicken()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                GameOverButton(
                    text = "Retry",
                    colors = listOf(Color(0xFF9CE86D), Color(0xFF2F8D2D)),
                    onClick = onRetry,
                    modifier = Modifier.weight(1f)
                )

                GameOverButton(
                    text = "Menu",
                    colors = listOf(Color(0xFFFFD27F), Color(0xFFDA7F21)),
                    onClick = onHome,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun GameOverChicken() {
    val stageShape = RoundedCornerShape(28.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(stageShape)
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF78C850), Color(0xFF3F8A28))
                )
            )
            .border(4.dp, Color(0xFF1F4F17), stageShape)
            .padding(horizontal = 18.dp, vertical = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val podiumHeight = size.height * 0.28f
            val podiumTop = size.height - podiumHeight
            val podiumPath = Path().apply {
                moveTo(size.width * 0.1f, podiumTop)
                quadraticBezierTo(size.width * 0.32f, podiumTop - podiumHeight * 0.35f, size.width * 0.5f, podiumTop)
                quadraticBezierTo(size.width * 0.7f, podiumTop + podiumHeight * 0.25f, size.width * 0.9f, podiumTop)
                lineTo(size.width * 0.9f, size.height)
                lineTo(size.width * 0.1f, size.height)
                close()
            }
            drawPath(
                path = podiumPath,
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFFFF0B2), Color(0xFFCD8A2D))
                )
            )
            drawPath(
                path = podiumPath,
                color = Color(0xFF5C3B16),
                style = Stroke(width = size.minDimension * 0.012f)
            )

            drawCircle(
                color = Color.White,
                radius = size.width * 0.04f,
                center = Offset(size.width * 0.24f, podiumTop - size.height * 0.12f)
            )
            drawCircle(
                color = Color.White,
                radius = size.width * 0.035f,
                center = Offset(size.width * 0.68f, podiumTop - size.height * 0.09f)
            )

            repeat(3) { index ->
                val centerX = size.width * (0.25f + index * 0.18f)
                val centerY = size.height * 0.88f
                repeat(6) { petal ->
                    val angle = (PI * 2 / 6) * petal
                    val offsetX = (size.width * 0.03f * cos(angle)).toFloat()
                    val offsetY = (size.width * 0.03f * sin(angle)).toFloat()
                    drawCircle(
                        color = Color.White,
                        radius = size.width * 0.012f,
                        center = Offset(centerX + offsetX, centerY + offsetY)
                    )
                }
                drawCircle(
                    color = Color(0xFFFFD54F),
                    radius = size.width * 0.01f,
                    center = Offset(centerX, centerY)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .graphicsLayer { translationY = (-20).dp.toPx() }
        ) {
            ChickenSprite(speedLevel = 0, combo = 0)
        }
    }
}

@Composable
private fun GameOverButton(
    text: String,
    colors: List<Color>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(22.dp)
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val gradient = if (pressed) {
        Brush.verticalGradient(colors.map { it.copy(alpha = 0.8f) })
    } else {
        Brush.verticalGradient(colors)
    }

    Box(
        modifier = modifier
            .shadow(20.dp, shape, clip = false, spotColor = Color(0x80210F04))
            .clip(shape)
            .background(gradient)
            .border(3.dp, Color(0xFF3A1E08), shape)
            .clickable(
                interactionSource = interaction,
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}
