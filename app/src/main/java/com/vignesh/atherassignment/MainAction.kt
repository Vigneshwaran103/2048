package com.vignesh.atherassignment

import com.vignesh.atherassignment.core.Util

sealed class MainAction {
    data class Swipe(val direction: Util.SwipeDirection) : MainAction()
    data object Restart : MainAction()
}


