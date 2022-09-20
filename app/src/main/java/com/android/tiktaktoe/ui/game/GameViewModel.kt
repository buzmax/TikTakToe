package com.android.tiktaktoe.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.android.tiktaktoe.domain.model.Field
import com.android.tiktaktoe.domain.model.Player
import com.android.tiktaktoe.utils.FieldUtils
import com.android.tiktaktoe.utils.FieldUtils.fromListIndex
import com.android.tiktaktoe.utils.FieldUtils.toList

class GameViewModel : ViewModel() {

    private val _field: MutableLiveData<Field> = MutableLiveData()
    private val _currentTurn: MutableLiveData<Player> = MutableLiveData()
    private val _winner: MutableLiveData<Player> = MutableLiveData()
    private val _draw: MutableLiveData<Unit> = MutableLiveData()

    init {
        _field.postValue(FieldUtils.createField())
    }

    fun setupStartPlayer(player: Player) {
        _currentTurn.postValue(player)
    }

    fun getCurrentTurnData(): LiveData<Player> = _currentTurn

    fun getWinnerData(): LiveData<Player> = _winner

    fun getIsDrawData(): LiveData<Unit> = _draw

    fun getFieldData(): LiveData<List<Player?>> = Transformations.map(_field) { it.toList() }

    fun onMoveDone(move: Int) {
        val field = _field.value ?: return
        val currentPlayer = _currentTurn.value ?: return

        val coordinates = move.fromListIndex()
        if (!field.updateWithMove(coordinates.first, coordinates.second, currentPlayer)) return

        when (currentPlayer) {
            Player.X -> _currentTurn.postValue(Player.O)
            Player.O -> _currentTurn.postValue(Player.X)
        }

        _field.value = field

        FieldUtils.getWinnerData(field)?.let {
            _winner.postValue(it)
            return
        }

        if (FieldUtils.isDraw(field)) _draw.postValue(Unit)
    }
}