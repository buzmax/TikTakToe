package com.android.tiktaktoe

import com.android.tiktaktoe.domain.model.Field
import com.android.tiktaktoe.domain.model.Player
import com.android.tiktaktoe.utils.FieldUtils
import org.junit.Test

import org.junit.Assert.*

class FieldUtilsTest {

    @Test
    fun testAllWinCases() {
        var field = Field(
            values = arrayOf(
                arrayOf(Player.X, Player.X, Player.X),
                Array(Field.FIELD_SIZE) { null },
                Array(Field.FIELD_SIZE) { null }
            )
        )
        assertEquals(FieldUtils.getWinnerData(field), Player.X)

        field = Field(
            values = arrayOf(
                Array(Field.FIELD_SIZE) { null },
                arrayOf(Player.X, Player.X, Player.X),
                Array(Field.FIELD_SIZE) { null }
            )
        )
        assertEquals(FieldUtils.getWinnerData(field), Player.X)

        field = Field(
            values = arrayOf(
                Array(Field.FIELD_SIZE) { null },
                Array(Field.FIELD_SIZE) { null },
                arrayOf(Player.X, Player.X, Player.X)
            )
        )
        assertEquals(FieldUtils.getWinnerData(field), Player.X)

        field = Field(
            values = arrayOf(
                arrayOf(Player.X, null, null),
                arrayOf(Player.X, null, null),
                arrayOf(Player.X, null, null)
            )
        )
        assertEquals(FieldUtils.getWinnerData(field), Player.X)

        field = Field(
            values = arrayOf(
                arrayOf(null, Player.X, null),
                arrayOf(null, Player.X, null),
                arrayOf(null, Player.X, null)
            )
        )
        assertEquals(FieldUtils.getWinnerData(field), Player.X)

        field = Field(
            values = arrayOf(
                arrayOf(null, null, Player.X),
                arrayOf(null, null, Player.X),
                arrayOf(null, null, Player.X)
            )
        )
        assertEquals(FieldUtils.getWinnerData(field), Player.X)
        field = Field(
            values = arrayOf(
                arrayOf(null, null, Player.X),
                arrayOf(null, Player.X, null),
                arrayOf(Player.X, null, null)
            )
        )
        assertEquals(FieldUtils.getWinnerData(field), Player.X)
        field = Field(
            values = arrayOf(
                arrayOf(Player.X, null, null),
                arrayOf(null, Player.X, null),
                arrayOf(null, null, Player.X),
            )
        )
        assertEquals(FieldUtils.getWinnerData(field), Player.X)
    }

    @Test
    fun testDraw() {
        val field = Field(
            values = arrayOf(
                arrayOf(Player.X, Player.X, Player.X),
                arrayOf(Player.X, Player.X, Player.X),
                arrayOf(Player.X, Player.X, Player.X)
            )
        )
        assertTrue(FieldUtils.isDraw(field))
    }

    @Test
    fun testNoDraw() {
        val field = Field(
            values = arrayOf(
                arrayOf(Player.X, Player.X, Player.X),
                arrayOf(Player.X, null, Player.X),
                arrayOf(Player.X, Player.X, Player.X)
            )
        )
        assertFalse(FieldUtils.isDraw(field))
    }
}