package com.lauruscorp.templategenerator.markup

import com.lauruscorp.templategenerator.Config
import com.lauruscorp.templategenerator.utils.PathBuilder
import com.lauruscorp.templategenerator.utils.createTempDirectory
import com.lauruscorp.templategenerator.utils.delete
import com.lauruscorp.templategenerator.utils.deleteLineIfContains
import com.lauruscorp.templategenerator.utils.replace
import com.lauruscorp.templategenerator.utils.replaceLineIfContains
import com.lauruscorp.templategenerator.utils.replaceTextInParts
import com.lauruscorp.templategenerator.utils.toGradleModuleString
import com.lauruscorp.templategenerator.utils.toPackageString
import net.pearx.kasechange.toCamelCase
import net.pearx.kasechange.toPascalCase
import net.pearx.kasechange.toSnakeCase
import java.nio.file.Files
import java.nio.file.Path
import java.util.function.Consumer
import kotlin.io.path.Path
import kotlin.io.path.copyTo
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.io.path.isRegularFile
import kotlin.io.path.readText
import kotlin.io.path.relativeTo
import kotlin.io.path.writeText

internal class MarkupProcessor {
	/**
	 * @param rootDirectory Tree root
	 * @param moduleName Module that will be used in new module
	 * @param resultDirectory directory for processed tree
	 * @param overwrite if True then result tree will overwrite existing tree at [resultDirectory]
	 */
	fun processTree(
		rootDirectory: Path,
		moduleName: String,
		packagePaths: List<Path> = emptyList(),
		modulePaths: List<Path> = emptyList(),
		resultDirectory: Path = Config.RESULT_ROOT_PATH,
		overwrite: Boolean = true
	) {
		if (!rootDirectory.exists()) {
			return
		}
		
		val isOverwritingOriginal = resultDirectory == rootDirectory
		
		if (isOverwritingOriginal && !overwrite) {
			return
		}
		
		val tempRootDirectory = if (isOverwritingOriginal) {
			resultDirectory.createDirectories()
			val tempRootDirectory = resultDirectory.createTempDirectory("tempRoot")
			rootDirectory.copyTo(tempRootDirectory, overwrite = overwrite)
		} else {
			rootDirectory
		}
		
		Files.walk(tempRootDirectory)
			.forEach(Consumer { childPath ->
				val relativeChildPath = childPath.relativeTo(tempRootDirectory)
				val resultChildPath = PathBuilder(resultDirectory)
					.append(
						relativeChildPath.processMarkup(
							moduleName = moduleName,
							modulePaths = modulePaths,
							packagePaths = packagePaths
						)
					)
					.build()
				
				
				if (resultChildPath.exists() && !overwrite) {
					return@Consumer
				}
				
				if (childPath.isDirectory()) {
					resultChildPath.createDirectories()
					
					println("Created directory: $resultChildPath")
				} else if (childPath.isRegularFile()) {
					val text = childPath.readText()
					val processedText = text.processMarkup(
						moduleName = moduleName,
						modulePaths = modulePaths,
						packagePaths = packagePaths
					)
					resultChildPath.writeText(text = processedText)
					
					println("Created file: $resultChildPath")
				}
				
			})
		
		if (isOverwritingOriginal && tempRootDirectory != rootDirectory) {
			tempRootDirectory.delete()
		}
	}
	
	private fun Path.processMarkup(
		moduleName: String,
		modulePaths: List<Path>,
		packagePaths: List<Path>
	): Path {
		return replacePackageMarks(packagePaths)
			.replaceTextInParts { partText -> partText.processMarkup(moduleName, modulePaths, packagePaths) }
	}
	
	private fun String.processMarkup(
		moduleName: String,
		modulePaths: List<Path>,
		packagePaths: List<Path>
	): String {
		val namePascalCase = moduleName.toPascalCase()
		val nameLowerCase = namePascalCase.lowercase()
		val nameCamelCase = moduleName.toCamelCase()
		val nameSnakeCase = moduleName.toSnakeCase()
		
		return this
			.replacePackageMarks(packagePaths)
			.replace(Markup.NameOriginal.mark, moduleName)
			.replaceNamePascalMark(namePascalCase)
			.replace(Markup.NameLowercase.mark, nameLowerCase)
			.replace(Markup.NameCamel.mark, nameCamelCase)
			.replace(Markup.NameSnake.mark, nameSnakeCase)
			.deleteLineIfContains(Markup.RemoveLine.mark)
			.replaceGradlePlugInModuleMark(modulePaths)
	}
	
	private fun Path.replacePackageMarks(
		packagePaths: List<Path>
	): Path {
		var processedPath = this
		
		Markup.Package.marks.forEachIndexed { index, packageMark ->
			val realPackage = packagePaths.getOrNull(index)
			
			if (realPackage != null) {
				processedPath = processedPath.replace(Path(packageMark), realPackage)
			}
		}
		
		return processedPath
	}
	
	private fun String.replacePackageMarks(
		packagePaths: List<Path>
	): String {
		var result = this
		
		Markup.Package.marks.forEachIndexed { index, packageMark ->
			val realPackage = packagePaths.getOrNull(index)
				?.toPackageString()
			
			if (realPackage != null) {
				result = result.replace(packageMark, realPackage)
			}
		}
		
		return result
	}
	
	private fun String.replaceNamePascalMark(name: String): String {
		val namePascalCase = name.toPascalCase()
		var result = this
		
		Markup.NamePascal.marks.forEach { mark ->
			result = result.replace(mark, namePascalCase)
		}
		
		return result
	}
	
	private fun String.replaceGradlePlugInModuleMark(
		modulePaths: List<Path>
	): String {
		var result = this
		
		Markup.GradlePlugInModule.marks.forEachIndexed { index, mark ->
			val gradleModuleString = modulePaths.getOrNull(index)
				?.toGradleModuleString()
			
			if (gradleModuleString != null) {
				result = result.replaceLineIfContains(
					other = mark,
					replacement = "implementation project('$gradleModuleString')",
					preserveIndent = true
				)
			}
		}
		
		return result
	}
}