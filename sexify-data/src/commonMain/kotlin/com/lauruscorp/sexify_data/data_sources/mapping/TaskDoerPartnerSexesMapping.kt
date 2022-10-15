package com.lauruscorp.sexify_data.data_sources.mapping

import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.aliases.DbTaskDoerPartnerSex
import com.lauruscorp.sexify_data.databases.tasks.utils.getSex
import com.lauruscorp.sexify_data.entities.SexifySex

// TODO: make internal
fun DbTaskDoerPartnerSex.toSexifySex(
    database: TasksDatabase
): SexifySex? {
    return getSex(database)?.asSexifySex()
}