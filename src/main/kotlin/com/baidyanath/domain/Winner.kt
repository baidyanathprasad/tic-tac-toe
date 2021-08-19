package com.baidyanath.domain

interface Winner<T1, T2> {

    fun find(t1: T1, t2: T2): Pair<Player?, Result>
}