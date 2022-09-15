package application.data.database

import application.di.modules.database.qualifiers.SexifyDatabaseFileNameQualifier
import application.di.modules.database.qualifiers.SexifyDatabasePathQualifier
import com.lauruscorp.sexify_data.database.DatabaseDriverFactory
import com.lauruscorp.sexify_data.database.SafeSexifyDatabase
import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.squareup.sqldelight.db.SqlDriver
import java.nio.file.Path
import javax.inject.Inject
import kotlin.io.path.createDirectories
import kotlin.io.path.notExists

class SexifyDatabaseFactory @Inject constructor(
	@SexifyDatabasePathQualifier private val dbFilePath: Path,
	@SexifyDatabaseFileNameQualifier private val dbFileName: String
) {
	private val fullPath = dbFilePath.resolve(dbFileName)
	
	fun create(): SexifyDatabase {
		val driver = createDriver()
		
		if (fullPath.notExists()) {
			dbFilePath.createDirectories()
			SexifyDatabase.Schema.create(driver)
		}
		
		return SafeSexifyDatabase(driver)
	}
	
	private fun createDriver(): SqlDriver {
		return DatabaseDriverFactory(dbFilePath, dbFileName).create()
	}
}