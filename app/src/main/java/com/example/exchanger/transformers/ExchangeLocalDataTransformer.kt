package com.example.exchanger.transformers

import com.example.exchanger.data.CurrencyGraph
import com.example.exchanger.data.ExchangesAndRates
import javax.inject.Inject

class ExchangeLocalDataTransformer @Inject constructor(
    graph: CurrencyGraph
) : ExchangeDataTransformer(graph) {
    override fun transformData(data: Any?) =
        (data as ExchangesAndRates).rates?.let { fillGraph(graph, it) }

    override fun canTransform(data: Any?) = data is ExchangesAndRates
}