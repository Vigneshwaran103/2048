package com.vignesh.atherassignment.ui.composables

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.vignesh.atherassignment.SwipeDirection

@Composable
fun GameBoardWithSwipe(
    board: List<List<Int>>,
    onSwipe: (SwipeDirection) -> Unit
) {
    val swipeThreshold = 100f
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        when {
                            offsetX > swipeThreshold -> onSwipe(SwipeDirection.RIGHT)
                            offsetX < -swipeThreshold -> onSwipe(SwipeDirection.LEFT)
                            offsetY > swipeThreshold -> onSwipe(SwipeDirection.DOWN)
                            offsetY < -swipeThreshold -> onSwipe(SwipeDirection.UP)
                        }
                        offsetX = 0f
                        offsetY = 0f
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                )
            }
    ) {
        GameBoard(board)
    }
}