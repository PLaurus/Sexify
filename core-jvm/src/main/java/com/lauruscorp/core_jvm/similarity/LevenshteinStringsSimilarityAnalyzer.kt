package com.lauruscorp.core_jvm.similarity

import org.apache.commons.text.similarity.LevenshteinDistance

@Deprecated(message = "use same class from :core module")
class LevenshteinStringsSimilarityAnalyzer : StringsSimilarityAnalyzer {
    private val levenshteinDistance = LevenshteinDistance()

    override fun analyze(first: String, second: String): Float {
        val maxLength = Integer.max(first.length, second.length)

        if (maxLength > 0) {
            return (maxLength - levenshteinDistance.apply(first, second)).toFloat() / maxLength
        }

        return 1f
    }
}