package com.lauruscorp.templategenerator.generators

import com.lauruscorp.templategenerator.Config
import com.lauruscorp.templategenerator.markup.MarkupProcessor
import com.lauruscorp.templategenerator.utils.GradleModulePlugIner
import com.lauruscorp.templategenerator.utils.PathBuilder
import com.lauruscorp.templategenerator.utils.toPackageCase
import net.pearx.kasechange.toKebabCase
import java.nio.file.Path

internal class DomainTemplateGenerator(
	private val markupProcessor: MarkupProcessor,
	private val gradleModulePlugIner: GradleModulePlugIner
) : TemplateGenerator {
	override fun generate() {
		val domainName = readDomainName()
		val resultDirectory = createResultDirectoryPath(
			moduleName = domainName,
			moduleNameSuffix = Config.DOMAIN_NAME_SUFFIX
		)
		
		val domainPackagePath = createPackagePath(
			companyPackagePath = Config.COMPANY_PACKAGE,
			modulePath = resultDirectory
		)
		
		markupProcessor.processTree(
			rootDirectory = Config.DOMAIN_TEMPLATE_PATH,
			moduleName = domainName,
			resultDirectory = resultDirectory,
			overwrite = false,
			packagePaths = listOf(
				domainPackagePath
			)
		)
		
		gradleModulePlugIner.plugIntoProject(
			modulePath = resultDirectory,
			settingGradlePath = Config.SETTINGS_GRADLE_PATH
		)
	}
	
	private fun readDomainName(): String {
		print("Enter domain name: ")
		return readln()
	}
	
	// features example example-domain
	private fun createResultDirectoryPath(
		moduleName: String,
		moduleNameSuffix: String? = null
	): Path {
		return PathBuilder()
			.append(Config.RESULT_ROOT_PATH)
			.append(moduleName.replace("\\s".toRegex(), ""))
			.apply { moduleNameSuffix?.let { append("$moduleName-$it".toKebabCase()) } }
			.build()
	}
	
	private fun createPackagePath(
		companyPackagePath: Path,
		modulePath: Path? = null
	): Path {
		val modulePathInPackageCase = modulePath?.toPackageCase()
		return PathBuilder(companyPackagePath)
			.apply { modulePathInPackageCase?.let(::append) }
			.build()
	}
}