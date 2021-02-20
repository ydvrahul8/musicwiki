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
import com.example.musicwiki.models.artists.Artist
import com.example.musicwiki.utils.*

class ArtistAdapter : RecyclerView.Adapter<BaseViewHolder<*, *>>() {

    class MyAlbumViewHolder(private val binding: ItemAlbumBinding) :
        BaseViewHolder<Artist, OnArtistClickListener>(binding) {
        override fun bind(item: Artist, listener: OnArtistClickListener?) {
            val data = DataModel(item.name, item.image!![0].text)
            binding.data = data
            binding.layout.setOnClickListener {
                listener?.artistClickListener(item)
            }
        }
    }

    private val albums = ArrayList<Artist>()
    private var listener: OnArtistClickListener? = null

    fun setData(albums: ArrayList<Artist>, listener: OnArtistClickListener? = null) {
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

     fun displayLoading() {
        if (!isLoading()) {
            setTag(LOADING)
        }
    }

     fun displayError(string: String) {
        if (!isError()) {
            setTag(ERROR)
        }
    }

    private fun setTag(string: String) {
        val tag = Artist(null, "", "0", string, "0")
        val list = ArrayList<Artist>()
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
            is MyAlbumViewHolder -> holder.bind(item, listener)
        }
    }
}