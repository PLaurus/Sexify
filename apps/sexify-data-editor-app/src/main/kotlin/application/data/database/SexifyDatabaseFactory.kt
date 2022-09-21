package application.data.database

import application.di.modules.database.qualifiers.SexifyDatabaseFileNameQualifier
import application.di.modules.database.qualifiers.SexifyDatabasePathQualifier
import com.lauruscorp.sexify_data.database.DatabaseDriverFactory
import com.lauruscorp.sexify_data.database.SafeSexifyDatabase
import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.squareup.sqldelight.db.SqlDriver
import java.nio.file.Path
import javax.inject.Inject

class SexifyDatabaseFactory @Inject constructor(
	@SexifyDatabasePathQualifier private val dbFilePath: Path,
	@SexifyDatabaseFileNameQualifier private val dbFileName: String
) {
	fun create(): SexifyDatabase {
		val driver = createDriver()
		
		return SafeSexifyDatabase(driver)
	}
	
	private fun createDriver(): SqlDriver {
		return DatabaseDriverFactory(dbFilePath, dbFileName).create()
	}
}