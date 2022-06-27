package com.lauruscorp.templategenerator.generators

import com.lauruscorp.templategenerator.entities.Template

internal class TemplateGeneratorFactory {
	fun create(template: Template): TemplateGenerator {
		return when (template) {
			Template.App -> AppTemplateGenerator()
			Template.FeatureDomain -> FeatureDomainTemplateGenerator()
			Template.Feature -> FeatureTemplateGenerator()
			Template.Domain -> DomainTemplateGenerator()
		}
	}
}