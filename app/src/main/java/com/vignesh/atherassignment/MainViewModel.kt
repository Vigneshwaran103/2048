package com.vignesh.atherassignment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vignesh.atherassignment.domain.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    var state by mutableStateOf(MainGameState())
        private set

    fun handleIntent(intent: GameIntent) {
        when (intent) {
            GameIntent.Restart -> restartPuzzle()
            is GameIntent.Swipe -> {
                processSwipe(direction = intent.direction)
            }
        }
    }

    init {
        state = state.copy(
            board = resetPuzzleBoard(),
        )
    }

    private fun processSwipe(direction: SwipeDirection) {
        viewModelScope.launch {
            val newBoard = when (direction) {
                SwipeDirection.LEFT -> swipeLeft(state.board)
                SwipeDirection.RIGHT -> swipeRight(state.board)
                SwipeDirection.UP -> swipeUp(state.board)
                SwipeDirection.DOWN -> swipeDown(state.board)
            }

            val boardChanged = newBoard != state.board

            val updatedBoard = if (boardChanged) {
                addNewTile(newBoard)
            } else {
                newBoard
            }

            state = state.copy(
                board = updatedBoard,
                isGameOver = repository.checkGameOver(board = updatedBoard),
                puzzleSolved = repository.puzzleSolved(board = updatedBoard)
            )
        }
    }

    private fun restartPuzzle() {
        state = state.copy(board = resetPuzzleBoard(), isGameOver = false, puzzleSolved = false)
    }

    private fun resetPuzzleBoard(): List<List<Int>> {
        var board = List(4) { List(4) { 0 } }
        board = addNewTile(board)
        board = addNewTile(board)
        return board
    }

    private fun addNewTile(board: List<List<Int>>): List<List<Int>> {
        return repository.addNewTile(board = board)
    }

    private fun swipeLeft(board: List<List<Int>>): List<List<Int>> {
        return repository.swipeLeft(board = board)
    }

    private fun swipeRight(board: List<List<Int>>): List<List<Int>> {
        return repository.swipeRight(board = board)
    }

    private fun swipeUp(board: List<List<Int>>): List<List<Int>> {
        return repository.swipeUp(board = board)
    }

    private fun swipeDown(board: List<List<Int>>): List<List<Int>> {
        return repository.swipeDown(board = board)
    }
}

