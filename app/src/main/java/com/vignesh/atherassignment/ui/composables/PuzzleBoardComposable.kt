package com.vignesh.atherassignment.ui.composables

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.vignesh.atherassignment.core.Util

@Composable
fun PuzzleBoardComposable(
    modifier: Modifier = Modifier,
    board: List<List<Int>>,
    onSwipe: (Util.SwipeDirection) -> Unit
) {
    val swipeThreshold = 100f
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        when {
                            offsetX > swipeThreshold -> onSwipe(Util.SwipeDirection.RIGHT)
                            offsetX < -swipeThreshold -> onSwipe(Util.SwipeDirection.LEFT)
                            offsetY > swipeThreshold -> onSwipe(Util.SwipeDirection.DOWN)
                            offsetY < -swipeThreshold -> onSwipe(Util.SwipeDirection.UP)
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
        GameBoardPuzzleBoard(
            board = board
        )
    }
}