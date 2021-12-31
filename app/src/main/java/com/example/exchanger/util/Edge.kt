package com.example.exchanger.util

data class Edge<T>(
    val startVertex: Vertex<T>,
    val endVertex: Vertex<T>,
    val weight: T
)