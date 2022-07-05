package com.lauruscorp.templategenerator.generators

import com.lauruscorp.templategenerator.Config
import com.lauruscorp.templategenerator.markup.MarkupProcessor
import com.lauruscorp.templategenerator.utils.GradleModulePlugIner
import com.lauruscorp.templategenerator.utils.PathBuilder
import com.lauruscorp.templategenerator.utils.toPackageCase
import net.pearx.kasechange.toKebabCase
import java.nio.file.Path

internal class FeatureWithDomainTemplateGenerator(
	private val markupProcessor: MarkupProcessor,
	private val gradleModulePlugIner: GradleModulePlugIner
) : TemplateGenerator {
	override fun generate() {
		val featureName = readFeatureName()
		
		val featureResultDirectory = createResultDirectoryPath(
			moduleName = featureName,
			moduleNameSuffix = Config.FEATURE_NAME_SUFFIX
		)
		
		val domainResultDirectory = createResultDirectoryPath(
			moduleName = featureName,
			moduleNameSuffix = Config.DOMAIN_NAME_SUFFIX
		)
		
		val featurePackagePath = createPackagePath(
			companyPackagePath = Config.COMPANY_PACKAGE,
			modulePath = featureResultDirectory
		)
		
		val domainPackagePath = createPackagePath(
			companyPackagePath = Config.COMPANY_PACKAGE,
			modulePath = domainResultDirectory
		)
		
		markupProcessor.processTree(
			rootDirectory = Config.DOMAIN_TEMPLATE_PATH,
			moduleName = featureName,
			resultDirectory = domainResultDirectory,
			overwrite = false,
			packagePaths = listOf(
				domainPackagePath
			)
		)
		
		markupProcessor.processTree(
			rootDirectory = Config.FEATURE_TEMPLATE_PATH,
			moduleName = featureName,
			resultDirectory = featureResultDirectory,
			overwrite = false,
			packagePaths = listOf(
				domainPackagePath,
				featurePackagePath
			),
			modulePaths = listOf(domainResultDirectory),
		)
		
		gradleModulePlugIner.plugIntoProject(
			modulePath = domainResultDirectory,
			settingGradlePath = Config.SETTINGS_GRADLE_PATH
		)
		
		gradleModulePlugIner.plugIntoProject(
			modulePath = featureResultDirectory,
			settingGradlePath = Config.SETTINGS_GRADLE_PATH
		)
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
	
	private fun readFeatureName(): String {
		print("Enter feature name: ")
		return readln()
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