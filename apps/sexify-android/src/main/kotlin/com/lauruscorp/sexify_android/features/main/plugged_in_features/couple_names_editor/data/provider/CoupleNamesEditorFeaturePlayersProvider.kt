package com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.data.provider

import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.features.couplenameseditordomain.dependencies.data.PlayersProvider
import com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.aliases.CoupleNamesEditorPlayer
import com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.data.mapping.asCoupleNamesEditorPlayer
import com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.data.mapping.asPlayer
import com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.data.mapping.asSexifySex
import com.lauruscorp.sexify_data.entities.Player
import com.lauruscorp.sexify_data.repositories.players.PlayersRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class CoupleNamesEditorFeaturePlayersProvider @Inject constructor(
    private val appPlayersRepository: PlayersRepository,
    private val coroutineDispatchers: CoroutineDispatchers
) : PlayersProvider {
    private val cachedAppPlayers = arrayOfNulls<Player>(2)

    override suspend fun getFirstPlayer(): CoupleNamesEditorPlayer? =
        withContext(coroutineDispatchers.default) {
            getFirstAppPlayer()?.asCoupleNamesEditorPlayer()
        }

    override suspend fun getSecondPlayer(): CoupleNamesEditorPlayer? =
        withContext(coroutineDispatchers.default) {
            getSecondAppPlayer()?.asCoupleNamesEditorPlayer()
        }

    override suspend fun saveFirstPlayer(player: CoupleNamesEditorPlayer) {
        saveCoupleNamesEditorPlayer(player, cachedPlayerIndex = 0)
    }

    override suspend fun saveSecondPlayer(player: CoupleNamesEditorPlayer) {
        saveCoupleNamesEditorPlayer(player, cachedPlayerIndex = 1)
    }

    private suspend fun saveCoupleNamesEditorPlayer(
        coupleNamesEditorPlayer: CoupleNamesEditorPlayer,
        cachedPlayerIndex: Int
    ) = withContext(coroutineDispatchers.default) {
        val cachedAppPlayer = getAppPlayer(cachedPlayerIndex)

        val newPlayer = if (cachedAppPlayer != null) {
            appPlayersRepository.update(coupleNamesEditorPlayer.asPlayer(id = cachedAppPlayer.id))
        } else {
            appPlayersRepository.create(
                name = coupleNamesEditorPlayer.name,
                sex = coupleNamesEditorPlayer.sex.asSexifySex()
            )
        }

        cachedAppPlayers[cachedPlayerIndex] = newPlayer
    }

    private suspend fun getFirstAppPlayer(): Player? = withContext(coroutineDispatchers.default) {
        getAppPlayer(0)
    }

    private suspend fun getSecondAppPlayer(): Player? = withContext(coroutineDispatchers.default) {
        getAppPlayer(1)
    }

    private suspend fun getAppPlayer(
        cachedPlayerIndex: Int
    ): Player? {
        return withContext(coroutineDispatchers.default) {
            cachedAppPlayers[cachedPlayerIndex]
                ?: appPlayersRepository.readAll()
                    .getOrNull(cachedPlayerIndex)
        }
    }

    private suspend fun saveAppPlayer(
        player: Player,
        cachedPlayerIndex: Int
    ) = withContext(coroutineDispatchers.default) {
        val cachedAppPlayer = cachedAppPlayers[cachedPlayerIndex]

        val newPlayer = if (cachedAppPlayer != null) {
            appPlayersRepository.update(player)
            player
        } else {
            appPlayersRepository.create(
                name = player.name,
                sex = player.sex
            )
        }

        cachedAppPlayers[cachedPlayerIndex] = newPlayer
    }

}