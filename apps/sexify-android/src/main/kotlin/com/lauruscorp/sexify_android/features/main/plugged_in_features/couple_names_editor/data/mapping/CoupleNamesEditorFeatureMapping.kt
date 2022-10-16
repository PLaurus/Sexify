package com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.data.mapping

import com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.aliases.CoupleNamesEditorPlayer
import com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.aliases.CoupleNamesEditorSex
import com.lauruscorp.sexify_data.entities.Player
import com.lauruscorp.sexify_data.entities.SexifySex

internal fun Player.asCoupleNamesEditorPlayer(): CoupleNamesEditorPlayer {
    return CoupleNamesEditorPlayer(
        name = name,
        sex = sex.asCoupleNamesEditorSex()
    )
}

internal fun CoupleNamesEditorPlayer.asPlayer(id: Long): Player {
    return Player(
        id = id,
        name = name,
        sex = sex.asSexifySex()
    )
}

internal fun SexifySex.asCoupleNamesEditorSex(): CoupleNamesEditorSex {
    return when(this) {
        SexifySex.Man -> CoupleNamesEditorSex.Man
        SexifySex.Woman -> CoupleNamesEditorSex.Woman
    }
}

internal fun CoupleNamesEditorSex.asSexifySex() : SexifySex {
    return when(this) {
        CoupleNamesEditorSex.Man -> SexifySex.Man
        CoupleNamesEditorSex.Woman -> SexifySex.Woman
    }
}