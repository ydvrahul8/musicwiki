package com.example.musicwiki

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T,R>(itemView: ViewDataBinding, listener: BaseClickListener? = null) :
    RecyclerView.ViewHolder(itemView.root) {
    abstract fun bind(item: T, listener: R?=null);
}