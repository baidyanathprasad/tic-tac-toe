package com.baidyanath

import com.baidyanath.domain.MatchImpl
import com.baidyanath.domain.Player
import com.baidyanath.store.Board

fun main() {
    val size =  3
    val board = Board(size)

    val players = listOf(
        Player(name = "X", hasTurn = true),
        Player(name = "0", hasTurn = false)
    )

    MatchImpl.play(board = board, players = players)
}