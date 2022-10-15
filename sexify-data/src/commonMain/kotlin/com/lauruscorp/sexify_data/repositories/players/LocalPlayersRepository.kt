package com.lauruscorp.sexify_data.repositories.players

import com.lauruscorp.sexify_data.data_sources.players.PlayersLocalDataSource
import com.lauruscorp.sexify_data.entities.Player
import com.lauruscorp.sexify_data.entities.SexifySex

class LocalPlayersRepository(
    private val localDataSource: PlayersLocalDataSource
) : PlayersRepository {
    override suspend fun create(
        name: String,
        sex: SexifySex
    ): Player? {
        val id = localDataSource.insertPlayer(name, sex)
        return Player(
            id = id ?: return null,
            name = name,
            sex = sex
        )
    }

    override suspend fun readAll(): List<Player> {
        return localDataSource.getAllPlayers()
    }

    override suspend fun read(id: Long): Player? {
        return localDataSource.getPlayer(id)
    }

    override suspend fun update(player: Player) {
        localDataSource.updatePlayer(player)
    }

    override suspend fun delete(player: Player) {
        localDataSource.deletePlayer(id = player.id)
    }
}

