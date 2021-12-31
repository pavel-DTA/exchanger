package com.example.exchanger.util

open class BaseGraph<T> {

    private val vertices = mutableMapOf<String, Vertex<T>>()
    private val edges = mutableSetOf<Edge<T>>()

    private fun addVertex(v: Vertex<T>) {
        if (vertices[v.name] === null) vertices[v.name] = v
    }

    protected fun getVertex(name: String) = vertices[name]

    fun addEdge(edge: Edge<T>) {

        if (edges.contains(edge)) return

        var startVertex = getVertex(edge.startVertex.name)
        val endVertex = getVertex(edge.endVertex.name)

        if (startVertex === null) {
            addVertex(edge.startVertex)
            startVertex = getVertex(edge.startVertex.name)
        }

        if (endVertex === null) {
            addVertex(edge.endVertex)
        }

        startVertex?.edges?.add(edge)

        edges.add(edge)
    }

    fun getNeighbors(name: String) = getVertex(name)?.getNeighbors()
}