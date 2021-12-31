package com.example.exchanger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exchanger.databinding.FragmentCurrencyCardBinding
import com.example.exchanger.util.CURRENCY_FRAGMENT_RENDER_VALUE

class CurrencyCardFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(CURRENCY_FRAGMENT_RENDER_VALUE) }?.let {
            binding.text = it.getString(CURRENCY_FRAGMENT_RENDER_VALUE)
        }
    }

}