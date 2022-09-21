package application.features.loading.initializers

import application.di.modules.database.qualifiers.SexifyDatabaseFileNameQualifier
import application.di.modules.database.qualifiers.SexifyDatabasePathQualifier
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexify_data.database.utils.insertLanguage
import com.lauruscorp.sexify_data.database.utils.insertSex
import com.lauruscorp.sexify_data.database.utils.selectLanguageById
import com.lauruscorp.sexify_data.database.utils.selectSexByEnumName
import com.lauruscorp.sexify_data.database.utils.updateLanguageById
import com.lauruscorp.sexify_data.languages.SexifyLanguage
import com.lauruscorp.sexify_data.sex.SexifySex
import com.squareup.sqldelight.db.SqlDriver
import features.loading.domain.initializers.Initializer
import kotlinx.coroutines.runInterruptible
import kotlinx.coroutines.withContext
import java.nio.file.Path
import javax.inject.Inject
import kotlin.io.path.createDirectories
import kotlin.io.path.exists

internal class SexifyDatabaseInitializer @Inject constructor(
	private val coroutineDispatchers: CoroutineDispatchers,
	@SexifyDatabasePathQualifier private val dbFilePath: Path,
	@SexifyDatabaseFileNameQualifier private val dbFileName: String,
	private val sexifyDatabase: SexifyDatabase,
	private val sqlDriver: SqlDriver
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
		SexifyDatabase.Schema.create(sqlDriver)
	}
	
	private suspend fun fillDatabaseWithInitialData() = withContext(coroutineDispatchers.io) {
		sexifyDatabase.transaction {
			updateLanguages()
			updateSexes()
		}
	}
	
	private fun updateLanguages() {
		SexifyLanguage.values()
			.forEach { sexifyLanguage ->
				val correspondingDbLanguage = sexifyDatabase.selectLanguageById(sexifyLanguage.tag)
				
				if (correspondingDbLanguage == null) {
					sexifyDatabase.insertLanguage(
						id = sexifyLanguage.tag,
						name = sexifyLanguage.name
					)
				} else if (correspondingDbLanguage.languageName != sexifyLanguage.name) {
					sexifyDatabase.updateLanguageById(
						id = sexifyLanguage.tag,
						languageName = sexifyLanguage.name
					)
				}
			}
	}
	
	private fun updateSexes() {
		SexifySex.values()
			.forEach { sexifySex ->
				val correspondingDbSex = sexifyDatabase.selectSexByEnumName(sexifySex.name)
				
				if (correspondingDbSex == null) {
					sexifyDatabase.insertSex(enumName = sexifySex.name)
				}
			}
	}
}