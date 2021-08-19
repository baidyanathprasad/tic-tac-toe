package com.baidyanath

import com.baidyanath.domain.Player
import com.baidyanath.domain.Result
import com.baidyanath.domain.Result.ON
import com.baidyanath.domain.Winner
import com.baidyanath.store.Board
import com.baidyanath.utils.DisplayBoard
import java.util.*

fun main() {
    val size =  3
    val board = Board(3)

    val players = listOf(
        Player(name = "X", hasTurn = true),
        Player(name = "0", hasTurn = false)
    )

    println("Welcome to 3x3 Tic Tac Toe.")
    DisplayBoard.run(board)

    println("X will play first. Enter a slot number to place X in:")

    var winner: Pair<Player?, Result> = null to ON
    while (winner.first == null && winner.second == ON){
        val input = readLine()?.toInt()!!

        // validate input
        try {
            if (input < 1 || input > size * size) {
                println("Invalid input; re-enter slot number:")
                continue
            }
        }
        catch (e: InputMismatchException) {
            println("Invalid input; re-enter slot number:");
            continue
        }

        // This game has two player x and O.
        // Here is the logic to decide the turn.

        var turn = board.findTurn()
        if (board.board[input - 1] == " ") {
            board.board[input - 1] = turn

            turn = if (turn == "X") {
                "0"
            } else {
                "X";
            }

            DisplayBoard.run(board)
            winner = Winner().findWinner(board, players)

            if (turn == "X") {
                players[0].hasTurn = false
                players[1].hasTurn = true
            } else {
                players[1].hasTurn = false
                players[0].hasTurn = true
            }
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