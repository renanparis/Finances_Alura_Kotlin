package com.renanparis.finances.extensions


fun String.limitsStringOnUntil(characters: Int): String {

    if (this.length > characters) {
        return "${this.substring(0, characters)}..."
    }
    return this
}