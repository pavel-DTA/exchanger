package com.example.exchanger.transformers

import com.example.exchanger.data.CurrencyGraph
import com.example.exchanger.data.Rate
import com.example.exchanger.util.Currency
import com.example.exchanger.util.Edge
import com.example.exchanger.util.Vertex

abstract class ExchangeDataTransformer(val graph: CurrencyGraph) {

    var next: ExchangeDataTransformer? = null

    protected abstract fun transformData(data: Any?): CurrencyGraph?

    protected abstract fun canTransform(data: Any?): Boolean

    fun transform(data: Any?): CurrencyGraph? {
        return if (canTransform(data)) transformData(data) else next?.transformData(data)
    }

    protected fun fillGraph(graph: CurrencyGraph, rates: Rate): CurrencyGraph {

            val baseVertex = Vertex<Double?>(graph.base)

            //Todo: refactor this shit
            graph.addEdge(Edge(baseVertex, baseVertex, rates.eur))
            graph.addEdge(Edge(baseVertex, Vertex(Currency.USD.toString()), rates.usd))
            graph.addEdge(Edge(baseVertex, Vertex(Currency.GBP.toString()), rates.gbp))
            graph.addEdge(Edge(baseVertex, Vertex(Currency.RUB.toString()), rates.rub))
            graph.addEdge(Edge(baseVertex, Vertex(Currency.CHF.toString()), rates.chf))
            graph.addEdge(Edge(baseVertex, Vertex(Currency.CNY.toString()), rates.cny))

            return graph

    }
}