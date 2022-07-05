package com.lauruscorp.templategenerator

import com.lauruscorp.templategenerator.entities.Template
import com.lauruscorp.templategenerator.generators.TemplateGeneratorFactory
import com.lauruscorp.templategenerator.markup.MarkupProcessor
import com.lauruscorp.templategenerator.utils.GradleModulePlugIner

object Main {
	@JvmStatic
	fun main(args: Array<String>) {
		
		val templateReader = TemplateReader()
		val markupProcessor = MarkupProcessor()
		val gradleModulePlugIner = GradleModulePlugIner()
		val templateGeneratorFactory = TemplateGeneratorFactory(markupProcessor, gradleModulePlugIner)
		
		templateReader.printAvailableTemplates()
		
		val selectedTemplate = templateReader.readTemplate()
		val templateGenerator = templateGeneratorFactory.create(selectedTemplate)
		
		templateGenerator.generate()
	}
	
	private class TemplateReader {
		private val templates = Template.values()
		
		fun printAvailableTemplates() {
			templates.forEach { template ->
				println("${template.ordinal}. ${template.name}")
			}
		}
		
		fun readTemplate(): Template {
			print("Enter template id: ")
			
			val ordinal: Int? = readln().toIntOrNull()
			
			return if (ordinal == null || ordinal !in templates.indices) {
				readTemplate()
			} else {
				templates[ordinal]
			}
		}
	}
}
