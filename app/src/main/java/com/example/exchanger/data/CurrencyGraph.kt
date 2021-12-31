package com.example.exchanger.data

import com.example.exchanger.util.BaseGraph

class CurrencyGraph(val base: String) : BaseGraph<Double?>() {

    private fun getVerticesRelation(vertex1: String, vertex2: String) = getVertex(vertex1)?.getWeightRelation(vertex2)

    fun calcRelation(vertex1: String, vertex2: String): Double? {
        return when(base) {
            vertex1 -> getVerticesRelation(base, vertex2)
            vertex2 -> {
                val rel = getVerticesRelation(base, vertex1)
                if (rel !== null) 1.0 / rel else null
            }
            else -> {
                val rel1 = getVerticesRelation(base, vertex1)
                val rel2 = getVerticesRelation(base, vertex2)
                if (rel1 !== null && rel2 !== null) rel2 / rel1 else null
            }
        }
    }
}