package com.lauruscorp.sexify_data.database

class SexifyDatabase(driverFactory: DatabaseDriverFactory) {
	private val database = GeneratedDatabaseProxy(
		driver = driverFactory.create()
	)
	
	private val languagesQueries = database.languagesQueriesQueries
	private val sexQueries = database.sexQueriesQueries
	private val taskDoerPartnerSexQueries = database.taskDoerPartnerSexQueriesQueries
	private val taskDoerSexQueries = database.taskDoerSexQueriesQueries
	private val tasksQueries = database.tasksQueriesQueries
	private val taskStagesQueries = database.taskStagesQueriesQueries
	private val textContentQueries = database.textContentQueriesQueries
	private val translationsQueries = database.translationsQueriesQueries
}