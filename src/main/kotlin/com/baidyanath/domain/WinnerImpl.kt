package com.baidyanath.domain

import com.baidyanath.domain.Result.*
import com.baidyanath.store.Board


class WinnerImpl: Winner<Board, List<Player>> {

    override fun findWinner(t1: Board, t2: List<Player>): Pair<Player?, Result> {
        val board = t1.board
        val playerX = t2[0]
        val player0 = t2[1]

        for (i in 0..7) {
            var line: String? = null
            when (i) {
                0 -> line = board[0] + board[1] + board[2]
                1 -> line = board[3] + board[4] + board[5]
                2 -> line = board[6] + board[7] + board[8]
                3 -> line = board[0] + board[3] + board[6]
                4 -> line = board[1] + board[4] + board[7]
                5 -> line = board[2] + board[5] + board[8]
                6 -> line = board[0] + board[4] + board[8]
                7 -> line = board[2] + board[4] + board[6]
            }

            if (line == "XXX") {
                return playerX to WIN
            } else if (line == "000") {
                return player0 to WIN
            }
        }

        var remainingMoves = 0
        board.forEach {
            if (it == " ") {
                remainingMoves++
            }
        }

        if (remainingMoves == 0 ){
            return null to DRAW
        }

        val playerWithTurn = when {
            playerX.hasTurn -> playerX
            else -> player0
        }

        println("${playerWithTurn.name}'s turn; enter a slot number to place ${playerWithTurn.name} in:")
        return null to ON
    }
}