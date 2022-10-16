package application.features.loading.initializers

import application.di.modules.databases.tasks.qualifiers.TasksDatabaseFileNameQualifier
import application.di.modules.databases.tasks.qualifiers.TasksDatabasePathQualifier
import application.di.modules.databases.tasks.qualifiers.TasksDatabaseSqlDriverQualifier
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.utils.*
import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.SexifySex
import com.squareup.sqldelight.db.SqlDriver
import features.loading.domain.initializers.Initializer
import kotlinx.coroutines.runInterruptible
import kotlinx.coroutines.withContext
import java.nio.file.Path
import javax.inject.Inject
import kotlin.io.path.createDirectories
import kotlin.io.path.exists

internal class TasksDatabaseInitializer @Inject constructor(
	private val coroutineDispatchers: CoroutineDispatchers,
	@TasksDatabasePathQualifier private val dbFilePath: Path,
	@TasksDatabaseFileNameQualifier private val dbFileName: String,
	private val tasksDatabase: TasksDatabase,
	@TasksDatabaseSqlDriverQualifier private val sqlDriver: SqlDriver
) : Initializer {
	override val dependencies: List<Class<out Initializer>>
		get() = emptyList()

	override suspend fun initialize() = withContext(coroutineDispatchers.io) {
		runInterruptible(coroutineDispatchers.default) {
			if (!checkDatabaseFileExists()) {
				createDatabaseFile()
			}
		}
		
		fillDatabaseWithInitialData()
	}
	
	private fun checkDatabaseFileExists(): Boolean {
		val fullPath = dbFilePath.resolve(dbFileName)
		return fullPath.exists()
	}
	
	private fun createDatabaseFile() {
		dbFilePath.createDirectories()
		TasksDatabase.Schema.create(sqlDriver)
	}
	
	private suspend fun fillDatabaseWithInitialData() = withContext(coroutineDispatchers.io) {
		tasksDatabase.transaction {
			updateLanguages()
			updateSexes()
		}
	}
	
	private fun updateLanguages() {
		SexifyLanguage.values()
			.forEach { sexifyLanguage ->
				val correspondingDbLanguage = tasksDatabase.selectLanguageById(sexifyLanguage.tag)
				
				if (correspondingDbLanguage == null) {
					tasksDatabase.insertLanguage(
						id = sexifyLanguage.tag,
						name = sexifyLanguage.name
					)
				} else if (correspondingDbLanguage.languageName != sexifyLanguage.name) {
					tasksDatabase.updateLanguageById(
						id = sexifyLanguage.tag,
						languageName = sexifyLanguage.name
					)
				}
			}
	}
	
	private fun updateSexes() {
		SexifySex.values()
			.forEach { sexifySex ->
				val correspondingDbSex = tasksDatabase.selectSexByEnumName(sexifySex.name)
				
				if (correspondingDbSex == null) {
					tasksDatabase.insertSex(enumName = sexifySex.name)
				}
			}
	}
}