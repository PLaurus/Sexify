package com.lauruscorp.sexify_data.data_sources.players

import com.lauruscorp.sexify_data.entities.Player
import com.lauruscorp.sexify_data.entities.SexifySex

interface PlayersLocalDataSource {
    /**
     * @param name player's name
     * @param sex player's sex constrained by [SexifySex] enum.
     * @return inserted player's id
     */
    suspend fun insertPlayer(
        name: String,
        sex: SexifySex
    ): Long?

    suspend fun updatePlayer(player: Player)
    suspend fun getPlayer(id: Long): Player?
    suspend fun getAllPlayers(): List<Player>
    suspend fun deletePlayer(id: Long)
    suspend fun deleteAllPlayers()
}