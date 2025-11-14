package com.manacode.chickenpop.ui.main.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.min

@Composable
fun SecondaryBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = SecondaryIconButton(
    onClick = onClick,
    modifier = modifier
) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(0.8f)
    )
}

@Composable
fun SecondaryIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 40.dp,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    icon: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Диаметр кнопки: иконка + паддинги + немножко места под ободок/юбку
    val buttonDiameter = iconSize +
            contentPadding.calculateLeftPadding(LayoutDirection.Ltr) +
            contentPadding.calculateRightPadding(LayoutDirection.Ltr) +
            16.dp

    val iconColor = if (!isPressed) Color(0xFF000000) else Color(0xFF5A3417)
    val iconAlpha = if (!isPressed) 1f else 0.8f

    Box(
        modifier = modifier
            .size(buttonDiameter)
            .clipToBounds()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            draw3DGoldButton(size, isPressed)
        }

        CompositionLocalProvider(LocalContentColor provides iconColor) {
            Box(
                modifier = Modifier
                    .size(iconSize)
                    .graphicsLayer { alpha = iconAlpha },
                contentAlignment = Alignment.Center
            ) {
                icon()
            }
        }
    }
}

private fun DrawScope.draw3DGoldButton(
    canvasSize: Size,
    isPressed: Boolean
) {
    val scale = min(canvasSize.width, canvasSize.height) / 100f

    // --- 1. Основа (нижний эллипс-юбка + тёмный обод) ---
    val baseWidth = 85f * scale
    val baseHeight = 85f * scale
    val baseCenter = Offset(canvasSize.width / 2, canvasSize.height / 2 + 5f * scale)

    // Тёмный внешний контур
    drawOval(
        color = Color(0xFF2F1B05),
        topLeft = Offset(
            baseCenter.x - baseWidth / 2,
            baseCenter.y - baseHeight / 2
        ),
        size = Size(baseWidth, baseHeight),
        style = Stroke(width = 5f * scale)
    )

    // --- 2. Средняя «бронзовая» часть (как у тебя в RoundBtn) ---
    val middleGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF93311C),
            Color(0xFFB47234),
            Color(0xFF93311C)
        ),
        start = Offset(0f, canvasSize.height / 2),
        end = Offset(canvasSize.width, canvasSize.height / 2)
    )

    drawOval(
        brush = middleGradient,
        topLeft = Offset(
            baseCenter.x - baseWidth / 2,
            baseCenter.y - baseHeight / 2 - 2f * scale
        ),
        size = Size(baseWidth, baseHeight)
    )

    // Тёмный обвод средней части
    drawOval(
        color = Color(0xFF630F00),
        topLeft = Offset(
            baseCenter.x - baseWidth / 2,
            baseCenter.y - baseHeight / 2 - 2f * scale
        ),
        size = Size(baseWidth, baseHeight),
        style = Stroke(width = 1f * scale)
    )

    // --- 3. Верхняя золотая «шапка» ---
    val topRadius = 38f * scale
    val topCenter = Offset(canvasSize.width / 2, canvasSize.height / 2 - 2f * scale)

    val topGradient = if (isPressed) {
        // при нажатии — бронзовый (как у твоей тёмной кнопки)
        Brush.linearGradient(
            colors = listOf(
                Color(0xFF98321D),
                Color(0xFFBC7634),
                Color(0xFF98321D)
            ),
            start = Offset(topCenter.x - topRadius, topCenter.y),
            end = Offset(topCenter.x + topRadius, topCenter.y)
        )
    } else {
        // нормальное состояние — золотой сверху вниз
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFFFDD577),
                Color(0xFFD37C38)
            ),
            startY = topCenter.y - topRadius,
            endY = topCenter.y + topRadius
        )
    }

    drawCircle(
        brush = topGradient,
        radius = topRadius,
        center = topCenter
    )

    // Обводка верхнего диска
    drawCircle(
        color = Color(0xFFDFA74F),
        radius = topRadius,
        center = topCenter,
        style = Stroke(width = if (isPressed) 3f * scale else 4f * scale)
    )
}



@Composable
fun SecondaryIconButton(
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 40.dp,
    contentPadding: PaddingValues = PaddingValues(8.dp)
) {
    SecondaryIconButton(
        onClick = onClick,
        modifier = modifier,
        iconSize = iconSize,
        contentPadding = contentPadding
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = LocalContentColor.current,
            modifier = Modifier.fillMaxSize()
        )
    }
}
