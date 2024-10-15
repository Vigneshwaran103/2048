package com.vignesh.atherassignment.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GameBoard(
    board: List<List<Int>>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        for (row in board) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (tile in row) {
                    Tile(tileValue = tile)
                }
            }
        }
    }
}
