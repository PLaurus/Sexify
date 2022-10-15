package com.lauruscorp.core_jvm.similarity

@Deprecated(message = "use same class from :core module")
class StringsSimilarityAnalyzerFactory {
    fun create(
        algorithm: StringsSimilarityAlgorithm
    ): StringsSimilarityAnalyzer {
        return when (algorithm) {
            StringsSimilarityAlgorithm.LevenshteinDistance -> LevenshteinStringsSimilarityAnalyzer()
        }
    }
}