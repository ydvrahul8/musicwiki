package com.example.musicwiki.ui.adapter

import androidx.databinding.ViewDataBinding
import com.example.musicwiki.BaseClickListener
import com.example.musicwiki.BaseViewHolder
import com.example.musicwiki.databinding.ErrorLayoutBinding

class ErrorViewHolder(private val view: ViewDataBinding) : BaseViewHolder<String,BaseClickListener>(view) {
    override fun bind(data: String, listener: BaseClickListener?) {
        val binding = view as ErrorLayoutBinding
        binding.string = data
    }
}