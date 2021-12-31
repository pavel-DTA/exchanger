package com.example.exchanger.transformers

import com.example.exchanger.data.CurrencyGraph
import com.example.exchanger.data.RemoteExchange
import javax.inject.Inject

class ExchangeNetworkDataTransformer @Inject constructor(
    graph: CurrencyGraph
) : ExchangeDataTransformer(graph) {
    override fun transformData(data: Any?) =
        (data as RemoteExchange).rates?.let { fillGraph(graph, it) }

    override fun canTransform(data: Any?) = data is RemoteExchange


}