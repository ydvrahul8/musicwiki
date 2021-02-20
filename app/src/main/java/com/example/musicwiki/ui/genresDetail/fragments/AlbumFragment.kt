package com.example.musicwiki.ui.genresDetail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.R
import com.example.musicwiki.ViewModelProviderFactory
import com.example.musicwiki.databinding.FragmentAlbumBinding
import com.example.musicwiki.models.album.Album
import com.example.musicwiki.ui.adapter.AlbumAdapter
import com.example.musicwiki.ui.genresDetail.GenreDetailViewModel
import com.example.musicwiki.utils.OnAlbumClickListener
import com.example.musicwiki.utils.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"

class AlbumFragment : DaggerFragment(), OnAlbumClickListener {
    private var tagName: String? = null

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: AlbumAdapter

    private lateinit var binding:FragmentAlbumBinding
    private lateinit var viewModel: GenreDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tagName = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumBinding.bind(view)
        viewModel = ViewModelProvider(this, factory).get(GenreDetailViewModel::class.java)
        binding.recyclerView.adapter = adapter
        observeAlbumList()
    }

    private fun observeAlbumList() {
        viewModel.observeTopAlbums(tagName!!).removeObservers(viewLifecycleOwner)
        viewModel.observeTopAlbums(tagName!!).observe(this, Observer {
            if (it != null)
                when (it.status) {
                    Status.LOADING -> adapter.displayLoading()
                    Status.SUCCESS -> adapter.setData(
                        it.data?.albums?.album as ArrayList<Album>,
                        this
                    )
                    Status.ERROR -> adapter.displayError(it.message!!)
                }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlbumFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun albumClickListener(album: Album) {

    }
}