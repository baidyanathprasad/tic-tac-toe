package com.baidyanath.utils

import com.baidyanath.store.Board

object DisplayBoard : Display<Board> {

    override fun run(t: Board) {
        val board = t.board

        println("|---|---|---|")
        println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |")

        println("|-----------|")
        println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |")

        println("|-----------|")
        println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |")

        println("|---|---|---|")
    }
}