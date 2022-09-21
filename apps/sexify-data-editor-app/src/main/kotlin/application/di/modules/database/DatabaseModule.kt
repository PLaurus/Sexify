package application.di.modules.database

import application.di.component.scope.ApplicationScope
import application.di.modules.database.qualifiers.SexifyDatabaseFileNameQualifier
import application.di.modules.database.qualifiers.SexifyDatabasePathQualifier
import com.lauruscorp.sexify_data.database.DatabaseDriverFactory
import com.lauruscorp.sexify_data.database.SafeSexifyDatabase
import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import java.nio.file.Path
import kotlin.io.path.Path

@Module
internal class DatabaseModule {
	@ApplicationScope
	@Provides
	fun provideSexifyDatabase(
		sqlDriver: SqlDriver
	): SexifyDatabase {
		return SafeSexifyDatabase(sqlDriver)
	}
	
	@Provides
	fun provideSqlDriver(
		databaseDriverFactory: DatabaseDriverFactory
	): SqlDriver {
		return databaseDriverFactory.create()
	}
	
	@Provides
	fun provideDatabaseDriverFactory(
		@SexifyDatabasePathQualifier dbFilePath: Path,
		@SexifyDatabaseFileNameQualifier dbFileName: String
	): DatabaseDriverFactory {
		return DatabaseDriverFactory(
			path = dbFilePath,
			databaseName = dbFileName
		)
	}
	
	@Provides
	@SexifyDatabasePathQualifier
	fun provideSexifyDatabasePath(): Path {
		return Path("database")
	}
	
	@Provides
	@SexifyDatabaseFileNameQualifier
	fun provideSexifyDatabaseFileName(): String {
		return "sexify.db"
	}
}