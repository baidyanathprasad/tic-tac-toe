package com.baidyanath.domain

import com.baidyanath.store.Board
import com.baidyanath.utils.DisplayBoard
import java.util.*

object MatchImpl : Match<Board, List<Player>> {

    override fun play(board: Board, players: List<Player>) {
        println("Welcome! let's begin the Tic Tac Toe(3 X 3).")
        DisplayBoard.run(board)

        println("X will play first. Enter a slot number to place X in:")
        var winner: Pair<Player?, Result> = null to Result.ON

        while (winner.first == null && winner.second == Result.ON){
            val input = readLine()?.toInt()!!

            val validInput = validateInput(input = input, board = board)
            if (validInput.not()) {
                continue
            }

            val turn = board.findTurn()
            if (board.board[input - 1] == " ") {
                board.board[input - 1] = turn
                DisplayBoard.run(board)

                exchangeTurn(turn, players)
                winner = WinnerImpl().findWinner(board, players)
            }
            else {
                println("Slot already taken; re-enter slot number:")
            }
        }

        if (winner.second.name == "DRAW") {
            println("It's a draw! Thanks for playing.");
        }
        else {
            println("Congratulations! ${winner.first?.name}'s have won! Thanks for playing.")
        }
    }

    private fun validateInput(input: Int, board: Board): Boolean {
        try {
            val size = board.board.size
            if (input < 1 || input > size * size) {
                println("Invalid input; re-enter slot number:")
            }
        }
        catch (e: InputMismatchException) {
            println("Invalid input; re-enter slot number:")
            return false
        }
        return true
    }

    private fun exchangeTurn(currentTurn: String, players: List<Player>): List<Player> {
        when (currentTurn) {
            "X" -> {
                players[0].hasTurn = false
                players[1].hasTurn = true
            }
            else -> {
                players[0].hasTurn = true
                players[1].hasTurn = false
            }
        }

        return players
    }
}