package com.example.musicwiki.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.BaseViewHolder
import com.example.musicwiki.R
import com.example.musicwiki.databinding.ErrorLayoutBinding
import com.example.musicwiki.databinding.ItemTagBinding
import com.example.musicwiki.databinding.LoadingLayoutBinding
import com.example.musicwiki.models.tags.Tag
import com.example.musicwiki.utils.*

class TagsAdapter : RecyclerView.Adapter<BaseViewHolder<*, *>>() {

    class MyTagViewHolder(private val binding: ItemTagBinding) :
        BaseViewHolder<Tag, OnClickListener>(binding) {
        override fun bind(item: Tag, listener: OnClickListener?) {
            binding.tag = item
            binding.textViewTagName.setOnClickListener {
                listener?.tagClickListener(item)
            }
        }
    }

    private val tags = ArrayList<Tag>()
    private var listener: OnClickListener? = null
    private var size: Int? = null

    public fun setData(tags: ArrayList<Tag>, listener: OnClickListener?=null) {
        this.listener = listener
        this.tags.clear()
        this.tags.addAll(tags)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            tags[position].name.equals(LOADING, true) -> {
                LOADING_VIEW
            }
            tags[position].name.equals(ERROR, true) -> {
                ERROR_VIEW
            }
            else -> DATA_VIEW
        }

    }

    private fun isLoading(): Boolean {
        if (tags.size > 0) {
            if (tags[0].name.equals(LOADING, true))
                return true
        }
        return false
    }

    private fun isError(): Boolean {
        if (tags.size > 0) {
            if (tags[0].name.equals(ERROR, true))
                return true
        }
        return false
    }

    public fun displayLoading() {
        if (!isLoading()) {
            setTag(LOADING)
        }
    }

    public fun displayError(string: String) {
        if (!isError()) {
            setTag(ERROR)
        }
    }

    private fun setTag(string: String) {
        val tag = Tag(0, string, 0)
        val list = ArrayList<Tag>()
        list.add(tag)
        setData(list, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> {
        return when (viewType) {
            LOADING_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<LoadingLayoutBinding>(
                    view,
                    R.layout.loading_layout,
                    parent,
                    false
                )
                return LoadingViewHolder(binding)
            }
            ERROR_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ErrorLayoutBinding>(
                    view,
                    R.layout.error_layout,
                    parent,
                    false
                )
                return ErrorViewHolder(binding)
            }
            DATA_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ItemTagBinding>(
                    view,
                    R.layout.item_tag,
                    parent,
                    false
                )
                return MyTagViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    fun expandList(size: Int) {
        this.size = size
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        val item = tags[position]
        when (holder) {
            is LoadingViewHolder -> holder.bind("item" as String)
            is ErrorViewHolder -> holder.bind(item.name as String)
            is MyTagViewHolder -> holder.bind(item, listener!!)
        }
    }
}