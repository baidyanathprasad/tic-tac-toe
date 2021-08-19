package com.baidyanath.domain

data class Player (
    val name: String,
    var hasTurn: Boolean,
    val isComputer: Boolean = false
)