package com.manacode.chickenpop.ui.main.menuscreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manacode.chickenpop.ui.main.component.GradientOutlinedText
import com.manacode.chickenpop.ui.main.component.SecondaryIconButton
import com.manacode.chickenpop.ui.main.component.StartPrimaryButton

@Composable
fun MenuScreen(
    onStartGame: () -> Unit,
    lastScore: Int?,
    onOpenSettings: () -> Unit,
) {
    Surface(color = Color(0xFFFFF4D9)) {
        Box(modifier = Modifier.fillMaxSize()) {
            MenuBackground()

            SecondaryIconButton(
                onClick = onOpenSettings,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Open settings",
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize(0.8f)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .padding(top = 80.dp, bottom = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                GradientOutlinedText(
                    text = "Chicken Pop",
                    fontSize = 48.sp,
                    gradientColors = listOf(Color(0xFFFFF7C8), Color(0xFFFFD75D))
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    ChickenHero()

                    StartPrimaryButton(
                        text = "Play",
                        onClick = onStartGame,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(60.dp)
                    )

                    if (lastScore != null) {
                        Text(
                            text = "Last score: $lastScore",
                            color = Color(0xFF5A360D),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                FarmHorizon()
            }
        }
    }
}

@Composable
private fun MenuBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF7AD8FF),
                            Color(0xFF7AD8FF),
                            Color(0xFFB2F28A)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            val hillPaint = Brush.linearGradient(
                colors = listOf(Color(0xFF7BC769), Color(0xFF4D9B3C)),
                start = Offset.Zero,
                end = Offset(size.width, size.height / 2f)
            )
            val hill = Path().apply {
                moveTo(-size.width * 0.1f, size.height * 0.65f)
                quadraticBezierTo(
                    size.width * 0.2f,
                    size.height * 0.45f,
                    size.width * 0.5f,
                    size.height * 0.6f
                )
                quadraticBezierTo(
                    size.width * 0.85f,
                    size.height * 0.8f,
                    size.width * 1.1f,
                    size.height * 0.65f
                )
                lineTo(size.width * 1.1f, size.height)
                lineTo(-size.width * 0.1f, size.height)
                close()
            }
            drawPath(path = hill, brush = hillPaint, style = Fill)
        }
    }
}

@Composable
private fun ChickenHero() {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .aspectRatio(1f)
            .shadow(18.dp, shape = CircleShape, clip = false, ambientColor = Color(0x33231105))
            .clip(CircleShape)
            .background(Color(0xFFFFF1C1)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize(0.85f)) {
            val bodyColor = Color(0xFFFFF7E0)
            val outline = Color(0xFF704117)
            val beak = Color(0xFFFFB74D)
            val comb = Color(0xFFE53935)

            val bodyCenter = Offset(size.width / 2f, size.height * 0.58f)
            drawOval(
                color = bodyColor,
                topLeft = Offset(size.width * 0.2f, size.height * 0.25f),
                size = Size(size.width * 0.6f, size.height * 0.6f)
            )
            drawOval(
                color = outline.copy(alpha = 0.3f),
                topLeft = Offset(size.width * 0.2f, size.height * 0.25f),
                size = Size(size.width * 0.6f, size.height * 0.6f),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = size.minDimension * 0.04f)
            )

            drawCircle(color = bodyColor, radius = size.width * 0.23f, center = Offset(size.width / 2f, size.height * 0.35f))
            drawCircle(color = outline.copy(alpha = 0.3f), radius = size.width * 0.23f, center = Offset(size.width / 2f, size.height * 0.35f), style = androidx.compose.ui.graphics.drawscope.Stroke(width = size.minDimension * 0.04f))

            drawCircle(color = Color.Black, radius = size.width * 0.04f, center = Offset(size.width * 0.43f, size.height * 0.33f))
            drawCircle(color = Color.White, radius = size.width * 0.015f, center = Offset(size.width * 0.41f, size.height * 0.32f))
            drawCircle(color = Color.Black, radius = size.width * 0.04f, center = Offset(size.width * 0.57f, size.height * 0.33f))
            drawCircle(color = Color.White, radius = size.width * 0.015f, center = Offset(size.width * 0.59f, size.height * 0.32f))

            val beakPath = Path().apply {
                moveTo(size.width * 0.48f, size.height * 0.38f)
                lineTo(size.width / 2f, size.height * 0.43f)
                lineTo(size.width * 0.52f, size.height * 0.38f)
                close()
            }
            drawPath(beakPath, color = beak)

            val combPath = Path().apply {
                moveTo(size.width * 0.45f, size.height * 0.22f)
                cubicTo(size.width * 0.44f, size.height * 0.16f, size.width * 0.48f, size.height * 0.14f, size.width * 0.5f, size.height * 0.18f)
                cubicTo(size.width * 0.52f, size.height * 0.14f, size.width * 0.56f, size.height * 0.16f, size.width * 0.55f, size.height * 0.22f)
            }
            drawPath(combPath, color = comb, style = androidx.compose.ui.graphics.drawscope.Stroke(width = size.minDimension * 0.05f, cap = androidx.compose.ui.graphics.StrokeCap.Round))

            drawOval(
                color = outline,
                topLeft = Offset(bodyCenter.x - size.width * 0.1f, bodyCenter.y + size.height * 0.1f),
                size = Size(size.width * 0.2f, size.height * 0.25f)
            )
        }
    }
}

@Composable
private fun FarmHorizon() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .offset(y = (-12).dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(3) { FarmHousePlaceholder() }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun FarmHousePlaceholder() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Canvas(modifier = Modifier
            .fillMaxWidth(0.25f)
            .aspectRatio(0.8f)) {
            val roofPath = Path().apply {
                moveTo(size.width / 2f, 0f)
                lineTo(0f, size.height * 0.35f)
                lineTo(size.width, size.height * 0.35f)
                close()
            }
            drawPath(roofPath, color = Color(0xFFB83A32))
            drawRect(
                color = Color(0xFFFFF1D6),
                topLeft = Offset(0f, size.height * 0.35f),
                size = Size(size.width, size.height * 0.45f)
            )
            drawRect(
                color = Color(0xFF8D5A33),
                topLeft = Offset(size.width * 0.35f, size.height * 0.55f),
                size = Size(size.width * 0.3f, size.height * 0.25f)
            )
        }
    }
}
