package com.renanparis.finances.extensions


fun String.limitsStringOnUntil(characters: Int): String {
    val startCharacters = 0

    if (this.length > characters) {
        return "${this.substring(startCharacters, characters)}..."
    }
    return this
}