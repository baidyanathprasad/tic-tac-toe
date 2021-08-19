package com.baidyanath

import com.baidyanath.domain.Board
import com.baidyanath.utils.DisplayBoard

fun main() {

    val size =  3
    val board = Board(3)

    DisplayBoard.run(board)
}