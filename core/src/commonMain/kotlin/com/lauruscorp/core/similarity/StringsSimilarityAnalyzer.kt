package com.lauruscorp.core.similarity

interface StringsSimilarityAnalyzer {
    /**
     * @return the similarity between two strings in the range [0, 1]
     */
    fun analyze(first: String, second: String): Float
}