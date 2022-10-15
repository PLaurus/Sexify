package com.lauruscorp.core_jvm.similarity

@Deprecated(message = "use same interface from :core module")
interface StringsSimilarityAnalyzer {
    /**
     * @return the similarity between two strings in the range [0, 1]
     */
    fun analyze(first: String, second: String): Float
}