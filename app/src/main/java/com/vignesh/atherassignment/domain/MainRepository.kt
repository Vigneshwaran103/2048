package com.vignesh.atherassignment.domain

interface MainRepository {

    fun swipeRight(puzzleBoard: List<List<Int>>): List<List<Int>>
    fun swipeLeft(puzzleBoard: List<List<Int>>): List<List<Int>>
    fun swipeUp(puzzleBoard: List<List<Int>>): List<List<Int>>
    fun swipeDown(puzzleBoard: List<List<Int>>): List<List<Int>>
    fun compressTiles(row: List<Int>): List<Int>
    fun tileMerger(row: List<Int>): List<Int>
    fun addNewTile(puzzleBoard: List<List<Int>>): List<List<Int>>
    fun isPuzzleSolved(puzzleBoard: List<List<Int>>): Boolean
    fun checkGameOver(puzzleBoard: List<List<Int>>): Boolean
}