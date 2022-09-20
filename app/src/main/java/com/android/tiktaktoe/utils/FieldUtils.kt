package com.android.tiktaktoe.utils

import com.android.tiktaktoe.domain.model.Field
import com.android.tiktaktoe.domain.model.Player

object FieldUtils {
    fun createField() = Field(values = Array(Field.FIELD_SIZE) { Array(Field.FIELD_SIZE) { null } })

    fun Field.toList(): List<Player?> {
        val list = mutableListOf<Player?>()
        values.forEach { arrayOfPlayers ->
            arrayOfPlayers.forEach { player ->
                list.add(player)
            }
        }
        return list
    }

    fun getWinnerData(field: Field): Player? {
        val values = field.values
        values.forEach { array ->
            array[0]?.let {
                //lines check
                array.reduce { acc, player -> if (acc == player) player else null }
                    ?.let { return it }
            }
        }

        values[0].forEachIndexed { index, player ->
            // columns check
            player?.takeIf { it == values[1][index] && it == values[2][index] }
                ?.let { return it }

        }

        values[0][0]
            ?.takeIf { it == values[1][1] && it == values[2][2] }
            ?.let { return it }

        return values[0][2]?.takeIf { it == values[1][1] && it == values[2][0] }
    }

    fun isDraw(field: Field) = field.toList().filterNotNull().size == field.size * field.size

    fun Int.fromListIndex(): Pair<Int, Int> = this / Field.FIELD_SIZE to this % Field.FIELD_SIZE
}