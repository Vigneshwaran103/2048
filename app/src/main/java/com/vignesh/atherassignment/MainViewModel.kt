package com.vignesh.atherassignment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vignesh.atherassignment.core.Util
import com.vignesh.atherassignment.domain.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    fun handleIntent(action: MainAction) {
        when (action) {
            MainAction.Restart -> restartPuzzle()
            is MainAction.Swipe -> {
                processSwipe(direction = action.direction)
            }
        }
    }

    init {
        state = state.copy(
            board = resetPuzzleBoard(),
        )
    }

    private fun processSwipe(direction: Util.SwipeDirection) {
        viewModelScope.launch {
            val newBoard = when (direction) {
                Util.SwipeDirection.LEFT -> swipeLeft(state.board)
                Util.SwipeDirection.RIGHT -> swipeRight(state.board)
                Util.SwipeDirection.UP -> swipeUp(state.board)
                Util.SwipeDirection.DOWN -> swipeDown(state.board)
            }

            val boardChanged = newBoard != state.board

            val updatedBoard = if (boardChanged) {
                addNewTile(newBoard)
            } else {
                newBoard
            }

            state = state.copy(
                board = updatedBoard,
                isGameOver = repository.checkGameOver(puzzleBoard = updatedBoard),
                puzzleSolved = repository.isPuzzleSolved(puzzleBoard = updatedBoard)
            )
        }
    }

    private fun restartPuzzle() {
        state = state.copy(
            board = resetPuzzleBoard(),
            isGameOver = false,
            puzzleSolved = false
        )
    }

    private fun resetPuzzleBoard(): List<List<Int>> {
        var board = List(4) { List(4) { 0 } }
        board = addNewTile(board)
        board = addNewTile(board)
        return board
    }

    private fun addNewTile(board: List<List<Int>>): List<List<Int>> {
        return repository.addNewTile(puzzleBoard = board)
    }

    private fun swipeLeft(board: List<List<Int>>): List<List<Int>> {
        return repository.swipeLeft(puzzleBoard = board)
    }

    private fun swipeRight(board: List<List<Int>>): List<List<Int>> {
        return repository.swipeRight(puzzleBoard = board)
    }

    private fun swipeUp(board: List<List<Int>>): List<List<Int>> {
        return repository.swipeUp(puzzleBoard = board)
    }

    private fun swipeDown(board: List<List<Int>>): List<List<Int>> {
        return repository.swipeDown(puzzleBoard = board)
    }
}

