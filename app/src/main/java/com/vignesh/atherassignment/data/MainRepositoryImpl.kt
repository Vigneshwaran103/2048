package com.vignesh.atherassignment.data

import com.vignesh.atherassignment.domain.MainRepository

class MainRepositoryImpl : MainRepository {
    override fun swipeRight(puzzleBoard: List<List<Int>>): List<List<Int>> {
        return puzzleBoard.map { row ->
            val reversedRow = row.reversed()
            val compressedRow = compressTiles(reversedRow)
            val mergedRow = tileMerger(compressedRow)
            compressTiles(mergedRow).reversed()
        }
    }

    override fun checkGameOver(puzzleBoard: List<List<Int>>): Boolean {
        if (puzzleBoard.any { row -> row.contains(0) }) {
            return false
        }

        for (i in 0 until 4) {
            for (j in 0 until 3) {
                if (puzzleBoard[i][j] == puzzleBoard[i][j + 1]) {
                    return false
                }
            }
        }

        for (j in 0 until 4) {
            for (i in 0 until 3) {
                if (puzzleBoard[i][j] == puzzleBoard[i + 1][j]) {
                    return false
                }
            }
        }

        return true
    }

    override fun isPuzzleSolved(puzzleBoard: List<List<Int>>): Boolean {
        return puzzleBoard.flatten().any { it == 2048 }
    }

    override fun swipeLeft(puzzleBoard: List<List<Int>>): List<List<Int>> {
        return puzzleBoard.map { row ->
            val compressedRow = compressTiles(row)
            val mergedRow = tileMerger(compressedRow)
            compressTiles(mergedRow)
        }
    }

    override fun swipeUp(puzzleBoard: List<List<Int>>): List<List<Int>> {
        val newBoard = MutableList(4) { MutableList(4) { 0 } }
        for (col in 0 until 4) {
            val column = puzzleBoard.map { row -> row[col] }

            val compressedColumn = compressTiles(column)
            val mergedColumn = tileMerger(compressedColumn)
            val finalColumn = compressTiles(mergedColumn)

            for (row in 0 until 4) {
                newBoard[row][col] = finalColumn[row]
            }
        }

        return newBoard
    }

    override fun swipeDown(puzzleBoard: List<List<Int>>): List<List<Int>> {
        val newBoard = MutableList(4) { MutableList(4) { 0 } }

        for (col in 0 until 4) {
            val column = puzzleBoard.map { row -> row[col] }

            val reversedColumn = column.reversed()
            val compressedColumn = compressTiles(reversedColumn)
            val mergedColumn = tileMerger(compressedColumn)
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

    override fun tileMerger(row: List<Int>): List<Int> {
        val newRow = row.toMutableList()
        for (i in 0 until newRow.size - 1) {
            if (newRow[i] != 0 && newRow[i] == newRow[i + 1]) {
                newRow[i] *= 2
                newRow[i + 1] = 0
            }
        }
        return newRow
    }

    override fun addNewTile(puzzleBoard: List<List<Int>>): List<List<Int>> {
        val emptyPositions = mutableListOf<Pair<Int, Int>>()

        for (i in puzzleBoard.indices) {
            for (j in puzzleBoard[i].indices) {
                if (puzzleBoard[i][j] == 0) {
                    emptyPositions.add(i to j)
                }
            }
        }

        if (emptyPositions.isEmpty()) return puzzleBoard

        val (row, col) = emptyPositions.random()

        val newTileValue = if ((1..10).random() > 9) 4 else 2

        return puzzleBoard.mapIndexed { i, rowList ->
            rowList.mapIndexed { j, value ->
                if (i == row && j == col) newTileValue else value
            }
        }
    }
}