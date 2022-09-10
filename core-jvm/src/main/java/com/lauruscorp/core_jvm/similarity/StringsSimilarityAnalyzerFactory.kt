package com.lauruscorp.core_jvm.similarity

class StringsSimilarityAnalyzerFactory {
    fun create(
        algorithm: StringsSimilarityAlgorithm
    ): StringsSimilarityAnalyzer {
        return when (algorithm) {
            StringsSimilarityAlgorithm.LevenshteinDistance -> LevenshteinStringsSimilarityAnalyzer()
        }
    }
}