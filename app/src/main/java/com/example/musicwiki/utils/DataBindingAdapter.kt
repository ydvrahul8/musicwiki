package com.example.musicwiki.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.musicwiki.R

@BindingAdapter("imageResource")
fun setImageResource(view: ImageView, imageUrl: String?) {
    val context = view.context
    val option: RequestOptions = RequestOptions()
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(imageUrl)
        .into(view)
}
