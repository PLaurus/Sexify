package com.lauruscorp.core.similarity

/**
 * @return the similarity between two strings in the range [0, 1]
 */
fun String.analyzeSimilarity(
    another: String,
    algorithm: StringsSimilarityAlgorithm = StringsSimilarityAlgorithm.LevenshteinDistance
): Float {
    val analyzerFactory = StringsSimilarityAnalyzerFactory()
    val analyzer = analyzerFactory.create(algorithm)

    return analyzer.analyze(this, another)
}