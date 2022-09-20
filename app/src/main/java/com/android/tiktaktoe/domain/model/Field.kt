package com.android.tiktaktoe.domain.model

data class Field(
    val size: Int = FIELD_SIZE,
    val values: Array<Array<Player?>>,
) {

    fun updateWithMove(row: Int, column: Int, player: Player): Boolean {
        if (values[row][column] != null) return false
        values[row][column] = player
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Field

        if (!values.contentDeepEquals(other.values)) return false

        return true
    }

    override fun hashCode(): Int {
        return values.contentDeepHashCode()
    }

    companion object {
        const val FIELD_SIZE = 3
    }
}
