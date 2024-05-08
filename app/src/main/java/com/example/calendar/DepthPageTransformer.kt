package com.example.calendar

import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2


class DepthPageTransformer : ViewPager2.PageTransformer {
    companion object {
        private const val MIN_SCALE = 0.7f
        private const val MAX_SCALE = 0.8f
        private const val MIN_FADE = 0.8f
    }

    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width

        when {
            position < -1 -> {
                view.alpha = MIN_FADE
            }
            position < 0 -> {
                view.alpha = 1 + position * (1 - MIN_FADE)
                view.translationX = -pageWidth * MAX_SCALE * position
                ViewCompat.setTranslationZ(view, position)
                val scaleFactor = MIN_SCALE + (MAX_SCALE - MIN_SCALE) * (1 - Math.abs(position))
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
            }
            position == 0f -> {
                view.alpha = 1f
                view.translationX = 0f
                view.scaleX = MAX_SCALE
                ViewCompat.setTranslationZ(view, 0f)
                view.scaleY = MAX_SCALE
            }
            position <= 1 -> {
                ViewCompat.setTranslationZ(view, -position)
                view.alpha = 1 - position * (1 - MIN_FADE)
                view.translationX = pageWidth * MAX_SCALE * -position
                val scaleFactor = MIN_SCALE + (MAX_SCALE - MIN_SCALE) * (1 - Math.abs(position))
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
            }
            else -> {
                view.alpha = MIN_FADE
            }
        }
    }
}