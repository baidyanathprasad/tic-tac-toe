package com.baidyanath.domain

interface Match<T1, T2> {

    fun play(board: T1, players: T2)
}