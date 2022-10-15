package com.lauruscorp.core.similarity

class StringsSimilarityAnalyzerFactory {
    fun create(
        algorithm: StringsSimilarityAlgorithm
    ): StringsSimilarityAnalyzer {
        return when (algorithm) {
            StringsSimilarityAlgorithm.LevenshteinDistance -> LevenshteinStringsSimilarityAnalyzer()
        }
    }
}