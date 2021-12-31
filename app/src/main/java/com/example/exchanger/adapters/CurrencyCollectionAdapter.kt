package com.example.exchanger.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exchanger.CurrencyCardFragment
import com.example.exchanger.util.CURRENCY_FRAGMENT_RENDER_VALUE
import com.example.exchanger.util.Currency

class CurrencyCollectionAdapter(
    fragment: Fragment,
    private val items: ArrayList<Currency>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = items.size

    override fun createFragment(position: Int): Fragment {
        val fragment = CurrencyCardFragment()
        fragment.arguments = Bundle().apply {
            putString(CURRENCY_FRAGMENT_RENDER_VALUE, items[position].name)
        }
        return fragment
    }
}