package com.example.exchanger.util

class Vertex<T>(val name: String) {

    val edges = mutableSetOf<Edge<T>>()

    fun getNeighbors() = edges.filter { it.startVertex === this }.map { it.endVertex }

    fun getWeightRelation(name: String): T? = edges.find {
        it.startVertex === this && it.endVertex.name === name
    }?.weight
}