package com.vignesh.atherassignment

import androidx.annotation.Keep

@Keep
data class MainGameState(
    val board: List<List<Int>> = emptyList()
)
