package com.baidyanath.domain

import com.baidyanath.store.Board
import com.baidyanath.utils.DisplayBoard
import java.lang.IllegalStateException
import java.util.*

object MatchImpl : Match<Board, List<Player>> {

    override fun play(board: Board, players: List<Player>) {
        println("Welcome! let's begin the Tic Tac Toe(3 X 3).")
        val isComputerMode = isComputerMode()
        if (isComputerMode) {
            println("Got it, Computer has name: 0, and you're X")
        }

        DisplayBoard.run(board)
        println("X will play first. Enter a slot number to place X in:")
        var winner: Pair<Player?, Result> = null to Result.ON

        while (winner.first == null && winner.second == Result.ON) {
            val turn = board.findTurn()

            val input: Int = if (isComputerMode && turn == "0") {
                availableRandomSlot(board)
            } else {
                try {
                    readLine()?.toInt()!!
                }
                catch (e: NumberFormatException) {
                    continue
                }
            }
            val validInput = validateInput(input = input, board = board)
            if (validInput.not()) {
                continue
            }

            if (isSlotAvailable(input, board)) {
                board.board[input - 1] = turn
                DisplayBoard.run(board)

                exchangeTurn(turn, players)
                winner = WinnerImpl().find(board, players)
            }
            else {
                println("Slot already taken; re-enter slot number:")
            }
        }

        if (winner.second.name == "DRAW") {
            println("It's a draw! Thanks for playing.");
        }
        else {
            println("Congratulations! ${winner.first?.name} have won! Thanks for playing.")
        }
    }

    private fun validateInput(input: Int, board: Board): Boolean {
        try {
            if (input < 1 || input > board.board.size) {
                throw InputMismatchException("Slot is not valid!")
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

    private fun isComputerMode() : Boolean {
        println("Do you wish to play with computer(Yes/No): ")
        var computer = readLine()!!

        while(true) {
            if (computer.contentEquals("yes", true)
                || computer.contentEquals("no", true)
            ) {
                break
            }
            println("Incorrect Input; please type yes or no: ")
            computer = readLine()!!
        }
        return computer.contentEquals("Yes", true)
    }

    private fun isSlotAvailable(input: Int, board: Board): Boolean {
        return try {
            board.board[input - 1] == " "
        } catch (e: IllegalStateException) {
            println("Wrong Input; enter slot again.")
            false
        }
    }

    private fun availableRandomSlot(board: Board) : Int {
        while (true) {
            val randomSlot = (1..9).random()
            if (isSlotAvailable(randomSlot, board)) {
                return randomSlot
            }
        }
    }
}