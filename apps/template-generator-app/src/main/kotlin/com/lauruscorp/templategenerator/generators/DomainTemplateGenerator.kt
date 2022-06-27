package com.lauruscorp.templategenerator.generators

import com.lauruscorp.templategenerator.Config
import com.lauruscorp.templategenerator.Markup
import com.lauruscorp.templategenerator.utils.PathBuilder
import net.pearx.kasechange.toKebabCase
import net.pearx.kasechange.toPascalCase
import java.nio.file.Files
import java.nio.file.Path
import java.util.function.Consumer
import kotlin.io.path.Path
import kotlin.io.path.appendLines
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.io.path.isRegularFile
import kotlin.io.path.readText
import kotlin.io.path.relativeTo
import kotlin.io.path.writeText

internal class DomainTemplateGenerator : TemplateGenerator {
	override fun generate() {
		val templatePath = getTemplatePath()
		val domainName = readDomainName()
		val domainPath = getDomainPath(domainName)
		
		Files.walk(templatePath)
			.forEach(Consumer { childPath ->
				val relativeChildPath = childPath.relativeTo(templatePath)
				val resultPath = PathBuilder(domainPath)
					.append(
						relativeChildPath.replaceMarkup(
							packagePath = getPackagePath(domainName),
							name = domainName
						)
					)
					.build()
				
				if (resultPath.exists()) {
					return@Consumer
				}
				
				if (childPath.isDirectory()) {
					resultPath.createDirectories()
					
					println("Created: $resultPath")
				} else if (childPath.isRegularFile()) {
					val text = childPath.readText()
					val processedText = text.replaceMarkup(
						packageName = getPackageName(domainName),
						name = domainName
					)
					resultPath.writeText(text = processedText)
					
					println("Created: $resultPath")
				}
			})
		
		plugInModule(modulePath = domainPath)
	}
	
	private fun readDomainName(): String {
		print("Enter domain name: ")
		return readln()
	}
	
	private fun getTemplatePath(): Path {
		return Path(Config.TEMPLATES_PATH, Config.DOMAIN_TEMPLATE_PATH)
	}
	
	private fun getPackageName(domainName: String): String {
		return "${Config.COMPANY_PACKAGE}.${Config.RESULT_ROOT_PATH}.${domainName.lowercase()}.${domainName.lowercase()}domain"
	}
	
	private fun getDomainPath(domainName: String): Path {
		return Path(Config.RESULT_ROOT_PATH, domainName, "${domainName.toKebabCase()}-domain")
	}
	
	private fun getPackagePath(domainName: String): Path {
		val companyPackagePieces = Config.COMPANY_PACKAGE.split(".")
			.toMutableList()
		
		val companyPackageBase = companyPackagePieces.firstOrNull() ?: ""
		
		val companyPackageSubPaths = companyPackagePieces
			.apply { removeFirstOrNull() }
			.toTypedArray()
		
		return Path(
			companyPackageBase,
			*companyPackageSubPaths,
			Config.RESULT_ROOT_PATH,
			domainName.lowercase(),
			"${domainName.lowercase()}domain"
		)
	}
	
	private fun Path.replaceMarkup(
		packagePath: Path,
		name: String
	): Path {
		return replacePackageDirectoryMark(packagePath)
			.replaceNamePascalMark(name)
	}
	
	private fun Path.replacePackageDirectoryMark(packagePath: Path): Path {
		val pathBuilder = PathBuilder()
		
		forEach { path ->
			val piece = if (path.toString() == Markup.PACKAGE_DIRECTORY) {
				packagePath
			} else path
			
			pathBuilder.append(piece)
		}
		
		return pathBuilder.build()
	}
	
	private fun Path.replaceNamePascalMark(name: String): Path {
		val pathBuilder = PathBuilder()
		
		forEach {
			val piece = it.toString()
				.replace(Markup.NAME_PASCAL, name.toPascalCase())
			pathBuilder.append(Path(piece))
		}
		
		return pathBuilder.build()
	}
	
	private fun String.replaceMarkup(
		packageName: String,
		name: String
	): String {
		return replacePackageCodeMark(packageName)
			.replaceNamePascalMark(name)
	}
	
	private fun String.replacePackageCodeMark(packageName: String): String {
		return replace(Markup.PACKAGE_CODE, packageName)
	}
	
	private fun String.replaceNamePascalMark(name: String): String {
		return replace(Markup.NAME_PASCAL, name.toPascalCase())
	}
	
	private fun plugInModule(modulePath: Path) {
		val settingsGradlePath = Path(Config.SETTINGS_GRADLE_PATH, "settings.gradle")
		if (settingsGradlePath.exists() && settingsGradlePath.isRegularFile()) {
			val moduleGradlePathBuilder = StringBuilder()
			modulePath.forEach { path ->
				moduleGradlePathBuilder.append(":")
				moduleGradlePathBuilder.append(path.toString())
			}
			settingsGradlePath.appendLines(listOf("include '$moduleGradlePathBuilder'"))
		}
	}
}