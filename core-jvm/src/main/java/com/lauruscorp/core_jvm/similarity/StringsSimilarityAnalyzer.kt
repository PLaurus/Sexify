package com.lauruscorp.core_jvm.similarity

interface StringsSimilarityAnalyzer {
    /**
     * @return the similarity between two strings in the range [0, 1]
     */
    fun analyze(first: String, second: String): Float
}