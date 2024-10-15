package com.vignesh.atherassignment

sealed class GameIntent {
    data class Swipe(val direction: SwipeDirection) : GameIntent()
    data object Restart : GameIntent()
}

enum class SwipeDirection {
    LEFT, RIGHT, UP, DOWN
}
