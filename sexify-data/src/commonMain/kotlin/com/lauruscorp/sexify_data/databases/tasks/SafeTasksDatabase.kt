package com.lauruscorp.sexify_data.databases.tasks

import com.lauruscorp.sexify_data.databases.tasks.queries.SafeTaskQueries
import com.lauruscorp.sexify_data.databases.tasks.queries.SafeTextContentQueries
import com.lauruscorp.sexifydata.databases.tasks.tables.TaskQueries
import com.lauruscorp.sexifydata.databases.tasks.tables.TextContentQueries
import com.squareup.sqldelight.db.SqlDriver

class SafeTasksDatabase private constructor(
    tasksDatabase: TasksDatabase
) : TasksDatabase by tasksDatabase {

    override val textContentQueries: TextContentQueries = SafeTextContentQueries(
        textContentQueries = tasksDatabase.textContentQueries,
        translationQueries = translationQueries
    )

    override val taskQueries: TaskQueries = SafeTaskQueries(
        taskQueries = tasksDatabase.taskQueries,
        textContentQueries = textContentQueries
    )

    companion object {
        operator fun invoke(
            driver: SqlDriver
        ): TasksDatabase {
            return TasksDatabase(driver)
        }
    }
}