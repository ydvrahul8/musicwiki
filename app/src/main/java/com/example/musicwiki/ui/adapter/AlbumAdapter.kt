package com.example.musicwiki.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.BaseViewHolder
import com.example.musicwiki.R
import com.example.musicwiki.databinding.ErrorLayoutBinding
import com.example.musicwiki.databinding.ItemAlbumBinding
import com.example.musicwiki.databinding.LoadingLayoutBinding
import com.example.musicwiki.models.DataModel
import com.example.musicwiki.models.album.Album
import com.example.musicwiki.utils.*

class AlbumAdapter : RecyclerView.Adapter<BaseViewHolder<*, *>>() {

    class MyAlbumViewHolder(private val binding: ItemAlbumBinding) :
        BaseViewHolder<Album, OnAlbumClickListener>(binding) {
        override fun bind(item: Album, listener: OnAlbumClickListener?) {
            val data = DataModel(item.name, item.image!![0].text)
            binding.data = data
            binding.layout.setOnClickListener {
                listener?.albumClickListener(item)
            }
        }
    }

    private val albums = ArrayList<Album>()
    private var listener: OnAlbumClickListener? = null
    private var size: Int? = null

    fun setData(albums: ArrayList<Album>, listener: OnAlbumClickListener? = null) {
        this.listener = listener
        this.albums.clear()
        this.albums.addAll(albums)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            albums[position].name.equals(LOADING, true) -> {
                LOADING_VIEW
            }
            albums[position].name.equals(ERROR, true) -> {
                ERROR_VIEW
            }
            else -> DATA_VIEW
        }

    }

    private fun isLoading(): Boolean {
        if (albums.size > 0) {
            if (albums[0].name.equals(LOADING, true))
                return true
        }
        return false
    }

    private fun isError(): Boolean {
        if (albums.size > 0) {
            if (albums[0].name.equals(ERROR, true))
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
        val tag = Album(null, null, "0", string, "0")
        val list = ArrayList<Album>()
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
                val binding = DataBindingUtil.inflate<ItemAlbumBinding>(
                    view,
                    R.layout.item_album,
                    parent,
                    false
                )
                return MyAlbumViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        val item = albums[position]
        when (holder) {
            is LoadingViewHolder -> holder.bind("item" as String)
            is ErrorViewHolder -> holder.bind(item.name as String)
            is MyAlbumViewHolder -> holder.bind(item, listener!!)
        }
    }
}