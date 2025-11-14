package com.manacode.chickenpop.ui.main.gamescreen.overlay

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.manacode.chickenpop.R

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
            .background(Color(0xFF0F2536).copy(alpha = 0.92f))
            .border(4.dp, Color(0xFF06121D), stageShape),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_menu),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.3f
        )

        Image(
            painter = painterResource(id = R.drawable.tile_bg),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(72.dp),
            contentScale = ContentScale.Crop,
            alpha = 0.85f
        )

        Image(
            painter = painterResource(id = R.drawable.chicken_happy),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.6f),
            contentScale = ContentScale.Fit
        )
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
