package com.manacode.chickenpop.ui.main.menuscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manacode.chickenpop.R
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

            MenuClouds()

            MenuForeground()

            SecondaryIconButton(
                onClick = onOpenSettings,
                modifier = Modifier
                    .align(Alignment.TopStart)
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
                    .padding(top = 96.dp, bottom = 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TitleBanner()

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    MenuChickenHero()

                    StartPrimaryButton(
                        onClick = onStartGame,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(60.dp)
                    )

                    if (lastScore != null) {
                        Text(
                            text = "Last score: $lastScore",
                            color = Color(0xFF4B2A0D),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                FarmRow()
            }
        }
    }
}

@Composable
private fun MenuBackground() {
    Image(
        painter = painterResource(id = R.drawable.bg_menu),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun BoxScope.MenuClouds() {
    Image(
        painter = painterResource(id = R.drawable.cloud),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(top = 64.dp, end = 32.dp)
            .fillMaxWidth(0.4f),
        contentScale = ContentScale.Fit,
        alpha = 0.85f
    )

    Image(
        painter = painterResource(id = R.drawable.cloud),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.TopStart)
            .padding(top = 72.dp, start = 28.dp)
            .fillMaxWidth(0.35f),
        contentScale = ContentScale.Fit,
        alpha = 0.75f
    )
}

@Composable
private fun BoxScope.MenuForeground() {
    Image(
        painter = painterResource(id = R.drawable.tile_bg),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .height(96.dp),
        contentScale = ContentScale.Crop,
        alpha = 0.7f
    )
}

@Composable
private fun TitleBanner() {
    Image(
        painter = painterResource(id = R.drawable.title),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(0.8f),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun MenuChickenHero() {
    Image(
        painter = painterResource(id = R.drawable.chicken),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(0.7f),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun FarmRow() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(3) {
                Image(
                    painter = painterResource(id = R.drawable.tile_house),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .height(110.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}
