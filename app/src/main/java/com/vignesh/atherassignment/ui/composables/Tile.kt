package com.vignesh.atherassignment.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vignesh.atherassignment.ui.theme.getTileColor

@Composable
fun Tile(
    tileValue: Int,
    modifier: Modifier = Modifier
) {
    val backgroundColor = getTileColor(value = tileValue)
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(80.dp)
            .padding(4.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
    ) {
        Text(
            text = if (tileValue > 0) tileValue.toString() else "",
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        )
    }
}
