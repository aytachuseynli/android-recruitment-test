package com.aytachuseynli.algoritmatask.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.aytachuseynli.algoritmatask.R


object ImageBindingAdapters {

    @JvmStatic
    @BindingAdapter("upDownImage")
    fun setUpDownImage(imageView: ImageView, upDown: String?) {
        upDown?.let {
            val imageResource = if (it.equals("increase", ignoreCase = true)) {
                R.drawable.ic_up
            } else {
                R.drawable.ic_down
            }
            imageView.setImageResource(imageResource)
        }
    }
}
