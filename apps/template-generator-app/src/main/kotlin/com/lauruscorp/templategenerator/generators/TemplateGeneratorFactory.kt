package com.lauruscorp.templategenerator.generators

import com.lauruscorp.templategenerator.entities.Template
import com.lauruscorp.templategenerator.markup.MarkupProcessor
import com.lauruscorp.templategenerator.utils.GradleModulePlugIner

internal class TemplateGeneratorFactory(
	private val markupProcessor: MarkupProcessor,
	private val gradleModulePlugIner: GradleModulePlugIner
) {
	fun create(template: Template): TemplateGenerator {
		return when (template) {
			Template.Domain -> DomainTemplateGenerator(markupProcessor, gradleModulePlugIner)
			Template.FeatureWithDomain -> FeatureWithDomainTemplateGenerator(markupProcessor, gradleModulePlugIner)
		}
	}
}