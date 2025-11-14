package com.manacode.chickenpop.ui.main.gamescreen.overlay

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manacode.chickenpop.ui.main.component.GradientOutlinedText
import com.manacode.chickenpop.ui.main.component.OrangePrimaryButton
import com.manacode.chickenpop.ui.main.component.SecondaryIconButton
import com.manacode.chickenpop.ui.main.component.StartPrimaryButton

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
            .background(Color(0xCC000000)),
        contentAlignment = Alignment.Center
    ) {
        val cardShape = RoundedCornerShape(28.dp)
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clip(cardShape)
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFFFF4D0), Color(0xFFFFCF83))
                    )
                )
                .padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            GradientOutlinedText(
                text = "Game Over",
                fontSize = 42.sp,
                gradientColors = listOf(Color(0xFFFFF9C4), Color(0xFFFF9800))
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Score",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF6A3E0B)
                )
                Text(
                    text = score.toString(),
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF402410)
                )
            }

            AnimatedVisibility(visible = combo > 1, enter = fadeIn(), exit = fadeOut()) {
                Text(
                    text = "Best combo: x$combo",
                    color = Color(0xFF845204),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SecondaryIconButton(
                    onClick = onRetry,
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Retry",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize(0.7f)
                    )
                }

                SecondaryIconButton(
                    onClick = onHome,
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Menu",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize(0.7f)
                    )
                }
            }

            StartPrimaryButton(
                text = "Play again",
                onClick = onRetry,
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            OrangePrimaryButton(
                text = "Menu",
                onClick = onHome,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }
    }
}
