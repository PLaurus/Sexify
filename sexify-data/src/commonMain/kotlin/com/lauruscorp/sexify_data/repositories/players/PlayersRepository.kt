package com.lauruscorp.sexify_data.repositories.players

import com.lauruscorp.sexify_data.entities.Player
import com.lauruscorp.sexify_data.entities.SexifySex

interface PlayersRepository {
    suspend fun create(name: String, sex: SexifySex): Player?
    suspend fun readAll(): List<Player>
    suspend fun read(id: Long): Player?
    suspend fun update(player: Player)
    suspend fun delete(player: Player)
}