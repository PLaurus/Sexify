package application.data.test

import com.example.sexify_domain_core.Gender
import com.example.sexify_domain_core.SexOrientation
import com.example.sexify_domain_core.Task

object TestTasksData {
    private val task0 = Task(
        id = 0,
        text = "Сделай это",
        stages = setOf(
            TestGameStages.Preparation
        ),
        orientations = setOf(
            SexOrientation.Hetero,
            SexOrientation.Gay,
            SexOrientation.Lesbian
        ),
        performerGender = setOf(
            Gender.Male,
            Gender.Female
        )
    )

    private val task1 = Task(
        id = 1,
        text = "Длинный текст был написан руками. Но этот короткий",
        stages = setOf(
            TestGameStages.Preparation
        ),
        orientations = setOf(
            SexOrientation.Hetero
        ),
        performerGender = setOf(
            Gender.Male,
            Gender.Female
        )
    )

    private val task2 = Task(
        id = 2,
        text = "Ультра омега короткий текст длинный текст текст",
        stages = setOf(
            TestGameStages.Preparation
        ),
        orientations = setOf(
            SexOrientation.Gay
        ),
        performerGender = setOf(
            Gender.Male
        )
    )

    private val task3 = Task(
        id = 3,
        text = """
            Действие 0!
            Стадии: Подготовка;
            Ориентации: Гетеро, Гей, Лезбиянка;
            Пол исполнителя: Мужской, Женский;
        """.trimIndent(),
        stages = setOf(
            TestGameStages.Preparation
        ),
        orientations = setOf(
            SexOrientation.Hetero,
            SexOrientation.Gay,
            SexOrientation.Lesbian
        ),
        performerGender = setOf(
            Gender.Male,
            Gender.Female
        )
    )

    private val task4 = Task(
        id = 4,
        text = """
            Действие 1!
            Стадии: Подготовка;
            Ориентации: Гетеро;
            Пол исполнителя: Мужской, женский;
        """.trimIndent(),
        stages = setOf(
            TestGameStages.Preparation
        ),
        orientations = setOf(
            SexOrientation.Hetero
        ),
        performerGender = setOf(
            Gender.Male,
            Gender.Female
        )
    )

    private val task5 = Task(
        id = 5,
        text = """
            Действие 2!
            Стадии: Подготовка;
            Ориентации: Гей;
            Пол исполнителя: Мужской;
        """.trimIndent(),
        stages = setOf(
            TestGameStages.Preparation
        ),
        orientations = setOf(
            SexOrientation.Gay
        ),
        performerGender = setOf(
            Gender.Male
        )
    )

    private val task6 = Task(
        id = 6,
        text = """
            Действие 3!
            Стадии: Подготовка;
            Ориентации: Лезбиянка;
            Пол исполнителя: Женский;
        """.trimIndent(),
        stages = setOf(
            TestGameStages.Preparation
        ),
        orientations = setOf(
            SexOrientation.Lesbian
        ),
        performerGender = setOf(
            Gender.Female
        )
    )

    private val task7 = Task(
        id = 7,
        text = """
            Действие 4!
            Стадии: Возбуждение;
            Ориентации: Гетеро, Гей, Лезбиянка;
            Пол исполнителя: Мужской, Женский;
        """.trimIndent(),
        stages = setOf(
            TestGameStages.Excitement
        ),
        orientations = setOf(
            SexOrientation.Hetero,
            SexOrientation.Gay,
            SexOrientation.Lesbian
        ),
        performerGender = setOf(
            Gender.Male,
            Gender.Female
        )
    )

    private val task8 = Task(
        id = 8,
        text = """
            Действие 5!
            Стадии: Предел;
            Ориентации: Гетеро, Гей, Лезбиянка;
            Пол исполнителя: Мужской, Женский;
        """.trimIndent(),
        stages = setOf(
            TestGameStages.Limit
        ),
        orientations = setOf(
            SexOrientation.Hetero,
            SexOrientation.Gay,
            SexOrientation.Lesbian
        ),
        performerGender = setOf(
            Gender.Male,
            Gender.Female
        )
    )

    private val task9 = Task(
        id = 9,
        text = """
            Действие 6!
            Стадии: Акт;
            Ориентации: Гетеро, Гей, Лезбиянка;
            Пол исполнителя: Мужской, Женский;
        """.trimIndent(),
        stages = setOf(
            TestGameStages.Act
        ),
        orientations = setOf(
            SexOrientation.Hetero,
            SexOrientation.Gay,
            SexOrientation.Lesbian
        ),
        performerGender = setOf(
            Gender.Male,
            Gender.Female
        )
    )

    private val task10 = Task(
        id = 7,
        text = """
            Действие 6!
            Стадии: Подготовка, Возбуждение, Предел, Акт;
            Ориентации: Гетеро, Гей, Лезбиянка;
            Пол исполнителя: Мужской, Женский;
        """.trimIndent(),
        stages = setOf(
            TestGameStages.Preparation,
            TestGameStages.Excitement,
            TestGameStages.Limit,
            TestGameStages.Act
        ),
        orientations = setOf(
            SexOrientation.Hetero,
            SexOrientation.Gay,
            SexOrientation.Lesbian
        ),
        performerGender = setOf(
            Gender.Male,
            Gender.Female
        )
    )

    val tasks = listOf(
        task0,
        task1,
        task2,
        task3,
        task4,
        task5,
        task6,
        task7,
        task8,
        task9,
        task10,
    )
}