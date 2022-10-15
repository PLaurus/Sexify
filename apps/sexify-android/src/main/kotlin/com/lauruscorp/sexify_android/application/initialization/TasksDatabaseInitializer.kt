package com.lauruscorp.sexify_android.application.initialization

import android.content.Context
import com.lauruscorp.core_android.di.dagger.qualifiers.context.ApplicationContextQualifier
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_android.application.di.modules.databases.tasks.qualifiers.TasksDatabaseAssetPathQualifier
import com.lauruscorp.sexify_android.application.di.modules.databases.tasks.qualifiers.TasksDatabaseFileNameQualifier
import kotlinx.coroutines.runInterruptible
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Path
import javax.inject.Inject

internal class TasksDatabaseInitializer @Inject constructor(
    @ApplicationContextQualifier private val context: Context,
    private val coroutineDispatchers: CoroutineDispatchers,
    @TasksDatabaseAssetPathQualifier private val dbAssetPath: Path,
    @TasksDatabaseFileNameQualifier private val dbFileName: String,
) : Initializer {
    override val dependencies: List<Class<out Initializer>>
        get() = emptyList()

    override suspend fun initialize() = withContext(coroutineDispatchers.io) {
        runInterruptible(coroutineDispatchers.default) {
            val dbFile = context.getDatabasePath(dbFileName)
            createDatabaseFile(dbFile)
        }
    }

    private fun createDatabaseFile(dbFile: File) {
        val inputStream = context.assets.open(dbAssetPath.toString())
        val outputStream = FileOutputStream(dbFile.absolutePath)

        inputStream.use { safeInputStream ->
            outputStream.use { safeOutput: FileOutputStream ->
                safeInputStream.copyTo(safeOutput)
            }
        }
    }
}