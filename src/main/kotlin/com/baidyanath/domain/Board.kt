package com.baidyanath.domain

/**
 * Assuming board is always considered in square size
 */
class Board (size: Int, initialBoardVal: String = " ") {

    val board = Array(size * size ) { _ -> initialBoardVal }

}
