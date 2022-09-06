package application.data.test

import com.example.sexify_domain_core.GameStage

object TestGameStages {
    val Preparation = GameStage(
        name = "Подготовка",
        description = "Это тестовое описание стадии \"Подготовка\""
    )

    val Excitement = GameStage(
        name = "Возбуждение",
        description = "Это тестовое описание стадии \"Возбуждение\""
    )

    val Limit = GameStage(
        name = "Предел",
        description = "Это тестовое описание стадии \"Предел\""
    )

    val Act = GameStage(
        name = "Акт",
        description = "Это тестовое описание стадии \"Акт\""
    )
}