package com.example.exchanger

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.example.exchanger.adapters.CurrencyCollectionAdapter
import com.example.exchanger.databinding.FragmentChangeToPagerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangeToPagerFragment : AbstractPagerFragment() {

    private lateinit var binding: FragmentChangeToPagerBinding

    override fun getPager() = binding.changeToPager

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding {
        binding = FragmentChangeToPagerBinding.inflate(inflater, container, false)
        return binding
    }

    override fun getAdapter() = CurrencyCollectionAdapter(this, viewModel.acceptedCurrencies)

    override suspend fun getCurrentPosition() = viewModel.state.value?.toPosition

    override fun setPosition() {
        lifecycleScope.launch {
            viewModel.state.collect {
                if (it !== null) {
                    binding.changeToPager.currentItem = it.toPosition
                }
            }
        }
    }

}