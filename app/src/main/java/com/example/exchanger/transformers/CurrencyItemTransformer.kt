package com.example.exchanger.transformers

import android.content.Context
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.exchanger.R

private const val MIN_SCALE = 0.07f
private const val ALPHA = 0.25f

class CurrencyItemTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val context: Context = view.context
        val nextItemVisiblePx = context.resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = context.resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

        view.translationX = -pageTranslationX * position
        view.scaleY = 1 - (MIN_SCALE * kotlin.math.abs(position))
        view.alpha = ALPHA + (1 - kotlin.math.abs(position))
    }

}
