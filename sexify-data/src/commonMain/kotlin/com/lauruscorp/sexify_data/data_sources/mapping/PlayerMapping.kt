package com.lauruscorp.sexify_data.data_sources.mapping

import com.lauruscorp.sexify_data.databases.cache.aliases.DbPlayer
import com.lauruscorp.sexify_data.entities.Player

internal fun DbPlayer.asPlayer(): Player? {
    return Player(
        id = this.id,
        name = this.name,
        sex = sexEnumName.asSexifySex() ?: return null
    )
}