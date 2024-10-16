package com.vignesh.atherassignment

import androidx.annotation.Keep

@Keep
data class MainState(
    val board: List<List<Int>> = emptyList(),
    val puzzleSolved: Boolean = false,
    val isGameOver: Boolean = false
)
