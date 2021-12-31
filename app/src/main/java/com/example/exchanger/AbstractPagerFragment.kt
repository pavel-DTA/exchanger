package com.example.exchanger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.exchanger.decorators.HorizontalMarginItemDecoration
import com.example.exchanger.transformers.CurrencyItemTransformer
import com.example.exchanger.viewmodels.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
abstract class AbstractPagerFragment : Fragment() {

    protected val viewModel: CurrencyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bind(inflater, container)
    }

    private fun bind(inflater: LayoutInflater, container: ViewGroup?): View {

        val binding = setBinding(inflater, container)

        setPosition()

        val pager = getPager()

        val callBack = object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                lifecycleScope.launch {
                    viewModel.addFrom(this@AbstractPagerFragment, position)
                }
            }
        }

        pager.registerOnPageChangeCallback(callBack)
        pager.offscreenPageLimit = 2
        pager.adapter = getAdapter()
        pager.setPageTransformer(CurrencyItemTransformer())
        val itemDecoration = context?.let {
            HorizontalMarginItemDecoration(
                it,
                R.dimen.viewpager_current_item_horizontal_margin
            )
        }
        if (itemDecoration != null) {
            pager.addItemDecoration(itemDecoration)
        }

        return binding.root
    }

    protected abstract fun getPager(): ViewPager2
    protected abstract fun setBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding
    protected abstract fun getAdapter(): FragmentStateAdapter
    protected abstract suspend fun getCurrentPosition(): Int?
    protected abstract fun setPosition()
}