package com.lauruscorp.features.couplenameseditordomain.dependencies.data

import com.lauruscorp.features.couplenameseditordomain.entities.Player

interface PlayersProvider {
    suspend fun getFirstPlayer(): Player?
    suspend fun getSecondPlayer(): Player?
    suspend fun saveFirstPlayer(player: Player)
    suspend fun saveSecondPlayer(player: Player)
}