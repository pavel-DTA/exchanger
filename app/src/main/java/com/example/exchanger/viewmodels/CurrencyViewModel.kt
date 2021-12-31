package com.example.exchanger.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchanger.AbstractPagerFragment
import com.example.exchanger.ChangeFromPagerFragment
import com.example.exchanger.ChangeToPagerFragment
import com.example.exchanger.data.CurrencyGraph
import com.example.exchanger.data.CurrencyState
import com.example.exchanger.data.repositories.CurrencyStateRepository
import com.example.exchanger.data.repositories.ExchangeRepository
import com.example.exchanger.util.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val stateRepository: CurrencyStateRepository,
    private val exchangeRepository: ExchangeRepository,
    val acceptedCurrencies: ArrayList<Currency>
) : ViewModel() {

    private val _state = MutableStateFlow<CurrencyState?>(null)
//    private val _fromAmount = MutableStateFlow(1)
    private val _rates = MutableStateFlow<CurrencyGraph?>(null)
//    private val _toAmount = MutableStateFlow<Double?>(null)

    //Todo FIX
    private val _visible = MutableStateFlow(true)

    val state: StateFlow<CurrencyState?> = _state.asStateFlow()
    val rates: StateFlow<CurrencyGraph?> = _rates.asStateFlow()
    val visible: StateFlow<Boolean> = _visible.asStateFlow()

    init {
        viewModelScope.launch {
            rates.collect { _state.update { it } }
        }
        viewModelScope.launch {
            getRates()
            setInitState()
        }
    }

    /**
     * Set visibility mark to toggle view layouts.
     */
    private suspend fun setVisibility(visibility: Boolean) {
        delay(1000)
        _visible.update { visibility }
    }

    /**
     * Update position from view (callback)
     */
    suspend fun addFrom(fragment: AbstractPagerFragment, pos: Int) {
        when (fragment) {
            is ChangeFromPagerFragment -> state.value?.let { positionUpdate(pos, it.toPosition) }
            is ChangeToPagerFragment -> state.value?.let { positionUpdate(it.fromPosition, pos) }
            //Todo: FIX
            else -> throw Exception()
        }
    }

    suspend fun setAmount(value: Int) {
        _state.update { it?.copy(fromAmount = value) }
        state.value?.let { stateRepository.updateState(it) }
    }

    /**
     * Put selected rates in current state.
     */
    private suspend fun positionUpdate(from: Int, to: Int) {
        _state.update {
            it?.copy(
                fromPosition = from,
                toPosition = to
            )
        }
        state.value?.let { stateRepository.updateState(it) }
    }

    /**
     * Set initial state.
     */
    private suspend fun setInitState() {
        stateRepository.getLastCurrencyState().collect {
            val i = it
            if (i !== null) {
                _state.update { i }
            } else {
                val state = CurrencyState()
                _state.update { state }
                stateRepository.addState(state)
            }
            setVisibility(true)
        }
    }

//    /**
//     * Update relation value in current state.
//     */
//    private fun toAmountUpdate() {
//        val fromPosition = state.value?.fromPosition
//        val toPosition = state.value?.toPosition
//        if (fromPosition !== null && toPosition !== null) {
//            _toAmount.update {
//                calculate(
//                    acceptedCurrencies[fromPosition].toString(),
//                    acceptedCurrencies[toPosition].toString()
//                )
//            }
//        }
//    }

    /**
     * Evaluate relation between two rates.
     */
    fun calculate(vertex1: String, vertex2: String) =
        state.value?.let { state ->
            rates.value?.let { graph ->
                graph.calcRelation(vertex1, vertex2)?.times(state.fromAmount)
            }
        }


    /**
     * Fetching stored or actual rates.
     */
    private suspend fun getRates() {
        val ratesFromRepo = exchangeRepository.getRates()
        _rates.update { ratesFromRepo }
    }
}