package application.di.modules.database

import application.data.database.SexifyDatabaseFactory
import application.di.component.scope.ApplicationScope
import application.di.modules.database.qualifiers.SexifyDatabaseFileNameQualifier
import application.di.modules.database.qualifiers.SexifyDatabasePathQualifier
import com.lauruscorp.sexify_data.database.SexifyDatabase
import dagger.Module
import dagger.Provides
import java.nio.file.Path
import kotlin.io.path.Path

@Module
internal class DatabaseModule {
	@ApplicationScope
	@Provides
	fun provideSexifyDatabase(
		sexifyDatabaseFactory: SexifyDatabaseFactory
	): SexifyDatabase {
		return sexifyDatabaseFactory.create()
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