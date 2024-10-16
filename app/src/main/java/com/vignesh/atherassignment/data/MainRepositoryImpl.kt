package com.vignesh.atherassignment.data

import com.vignesh.atherassignment.domain.MainRepository

class MainRepositoryImpl : MainRepository {
    override fun swipeRight(board: List<List<Int>>): List<List<Int>> {
        return board.map { row ->
            val reversedRow = row.reversed()
            val compressedRow = compressTiles(reversedRow)
            val mergedRow = merge(compressedRow)
            compressTiles(mergedRow).reversed()
        }
    }

    override fun checkGameOver(board: List<List<Int>>): Boolean {
        if (board.any { row -> row.contains(0) }) {
            return false
        }

        for (i in 0 until 4) {
            for (j in 0 until 3) {
                if (board[i][j] == board[i][j + 1]) {
                    return false
                }
            }
        }

        for (j in 0 until 4) {
            for (i in 0 until 3) {
                if (board[i][j] == board[i + 1][j]) {
                    return false
                }
            }
        }

        return true
    }

    override fun puzzleSolved(board: List<List<Int>>): Boolean {
        return board.flatten().any { it == 2048 }
    }

    override fun swipeLeft(board: List<List<Int>>): List<List<Int>> {
        return board.map { row ->
            val compressedRow = compressTiles(row)
            val mergedRow = merge(compressedRow)
            compressTiles(mergedRow)
        }
    }

    override fun swipeUp(board: List<List<Int>>): List<List<Int>> {
        val newBoard = MutableList(4) { MutableList(4) { 0 } }
        for (col in 0 until 4) {
            val column = board.map { row -> row[col] }

            val compressedColumn = compressTiles(column)
            val mergedColumn = merge(compressedColumn)
            val finalColumn = compressTiles(mergedColumn)

            for (row in 0 until 4) {
                newBoard[row][col] = finalColumn[row]
            }
        }

        return newBoard
    }

    override fun swipeDown(board: List<List<Int>>): List<List<Int>> {
        val newBoard = MutableList(4) { MutableList(4) { 0 } }

        for (col in 0 until 4) {
            val column = board.map { row -> row[col] }

            val reversedColumn = column.reversed()
            val compressedColumn = compressTiles(reversedColumn)
            val mergedColumn = merge(compressedColumn)
            val finalColumn = compressTiles(mergedColumn).reversed()

            for (row in 0 until 4) {
                newBoard[row][col] = finalColumn[row]
            }
        }
        return newBoard
    }

    override fun compressTiles(row: List<Int>): List<Int> {
        val newRow = row.filter { it != 0 }.toMutableList()
        while (newRow.size < 4) {
            newRow.add(0)
        }
        return newRow
    }

    override fun merge(row: List<Int>): List<Int> {
        val newRow = row.toMutableList()
        for (i in 0 until newRow.size - 1) {
            if (newRow[i] != 0 && newRow[i] == newRow[i + 1]) {
                newRow[i] *= 2
                newRow[i + 1] = 0
            }
        }
        return newRow
    }

    override fun addNewTile(board: List<List<Int>>): List<List<Int>> {
        val emptyPositions = mutableListOf<Pair<Int, Int>>()

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == 0) {
                    emptyPositions.add(i to j)
                }
            }
        }

        if (emptyPositions.isEmpty()) return board

        val (row, col) = emptyPositions.random()

        val newTileValue = if ((1..10).random() > 9) 4 else 2

        return board.mapIndexed { i, rowList ->
            rowList.mapIndexed { j, value ->
                if (i == row && j == col) newTileValue else value
            }
        }
    }
}