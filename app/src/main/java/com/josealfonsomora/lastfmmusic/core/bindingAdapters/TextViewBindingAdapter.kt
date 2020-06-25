package com.josealfonsomora.lastfmmusic.core.bindingAdapters

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter

object TextViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("htmlText")
    fun setHtmlTextFromString(view: TextView?, html: String?) {
        view?.let {
            html?.let {
                view.text = Html.fromHtml(html)
            }
        }
    }
}
