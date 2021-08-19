package com.baidyanath.store

import java.lang.IllegalStateException

/**
 * Assuming board is always considered in square size
 */
class Board (size: Int, initialBoardVal: String = " ") {

    val board = Array(size * size ) { initialBoardVal }

    /**
     *  A method to find the next turn in the board!
     */
    fun findTurn(): String {
        var playerXCount = 0
        var player0Count = 0
        board.forEach {
            when (it) {
                "X" -> {
                    playerXCount++
                }
                "0" -> {
                    player0Count++
                }
                " " -> {
                    // Do nothing!
                }
                else -> throw IllegalStateException("Only three values possible in board: X, 0 and empty string")
            }
        }

        return if (playerXCount <= player0Count) {
            "X"
        } else {
            "0"
        }
    }
}
