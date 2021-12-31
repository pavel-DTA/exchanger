package com.example.exchanger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.exchanger.databinding.FragmentDisplayPanelBinding
import com.example.exchanger.viewmodels.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.TextView

import androidx.core.content.ContextCompat.getSystemService


@AndroidEntryPoint
class DisplayPanelFragment : Fragment() {

    lateinit var binding: FragmentDisplayPanelBinding
    private val viewModel: CurrencyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDisplayPanelBinding.inflate(inflater, container, false)

        binding.amountInput.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                setAmount(v)
                (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.toggleSoftInput(
                    InputMethodManager.HIDE_IMPLICIT_ONLY, 0
                )
            }
            return@setOnEditorActionListener true
        }

        observeValues()

        return binding.root
    }

    private fun observeValues() {
        lifecycleScope.launch {
            viewModel.state.collect {
                it?.let {
                    val updatedFromCurrencyValue =
                        viewModel.acceptedCurrencies[it.fromPosition].toString()
                    val updatedToCurrencyValue =
                        viewModel.acceptedCurrencies[it.toPosition].toString()
                    val calculatedAmountValue =
                        viewModel.calculate(updatedFromCurrencyValue, updatedToCurrencyValue)
                    binding.fromCurrencyValue = updatedFromCurrencyValue
                    binding.toCurrencyValue = updatedToCurrencyValue
                    binding.fromAmount = it.fromAmount.toString()
                    binding.toAmountValue = String.format("%.2f", calculatedAmountValue)
                }
            }
        }
    }

    private fun setAmount(v: TextView) {
        lifecycleScope.launch {
            viewModel.setAmount(Integer.valueOf(v.text.toString()))
        }
    }
}