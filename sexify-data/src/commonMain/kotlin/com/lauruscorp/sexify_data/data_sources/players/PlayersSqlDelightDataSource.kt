package com.lauruscorp.sexify_data.data_sources.players

import com.lauruscorp.sexify_data.data_sources.mapping.asPlayer
import com.lauruscorp.sexify_data.entities.Player
import com.lauruscorp.sexify_data.entities.SexifySex
import com.lauruscorp.sexifydata.databases.cache.tables.PlayerQueries
import com.lauruscorp.sexifydata.databases.cache.utils.UtilsQueries
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PlayersSqlDelightDataSource(
    private val playerQueries: PlayerQueries,
    private val utilsQueries: UtilsQueries,
    private val ioDispatcher: CoroutineDispatcher
) : PlayersLocalDataSource {
    override suspend fun insertPlayer(
        name: String,
        sex: SexifySex
    ): Long? = withContext(ioDispatcher) {
        playerQueries.transactionWithResult {
            playerQueries.insert(name = name, sex = sex.name)
            utilsQueries.lastInsertRowId()
                .executeAsOneOrNull()
                ?: rollback(null)
        }
    }

    override suspend fun updatePlayer(
        player: Player
    ): Player = withContext(ioDispatcher) {
        playerQueries.update(id = player.id, name = player.name, sex = player.sex.name)
        player
    }

    override suspend fun getPlayer(
        id: Long
    ): Player? = withContext(ioDispatcher) {
        playerQueries.get(id)
            .executeAsOneOrNull()
            ?.asPlayer()
    }

    override suspend fun getAllPlayers(): List<Player> = withContext(ioDispatcher) {
        playerQueries.getAll()
            .executeAsList()
            .mapNotNull { it.asPlayer() }
    }

    override suspend fun deletePlayer(id: Long) {
        playerQueries.delete(id)
    }

    override suspend fun deleteAllPlayers() {
        playerQueries.deleteAll()
    }
}