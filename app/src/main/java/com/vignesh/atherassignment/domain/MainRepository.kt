package com.vignesh.atherassignment.domain

interface MainRepository {

    fun swipeRight(board: List<List<Int>>): List<List<Int>>
    fun swipeLeft(board: List<List<Int>>): List<List<Int>>
    fun swipeUp(board: List<List<Int>>): List<List<Int>>
    fun swipeDown(board: List<List<Int>>): List<List<Int>>
    fun compressTiles(row: List<Int>): List<Int>
    fun merge(row: List<Int>): List<Int>
    fun addNewTile(board: List<List<Int>>): List<List<Int>>
}