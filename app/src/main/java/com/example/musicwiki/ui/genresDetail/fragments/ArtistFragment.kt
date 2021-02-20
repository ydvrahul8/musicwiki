package com.example.musicwiki.ui.genresDetail.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.R
import com.example.musicwiki.ViewModelProviderFactory
import com.example.musicwiki.databinding.FragmentAlbumBinding
import com.example.musicwiki.databinding.FragmentArtistBinding
import com.example.musicwiki.models.album.Album
import com.example.musicwiki.models.artists.Artist
import com.example.musicwiki.ui.adapter.AlbumAdapter
import com.example.musicwiki.ui.adapter.ArtistAdapter
import com.example.musicwiki.ui.genresDetail.GenreDetailViewModel
import com.example.musicwiki.utils.OnArtistClickListener
import com.example.musicwiki.utils.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ArtistFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArtistFragment : DaggerFragment(),OnArtistClickListener {
    // TODO: Rename and change types of parameters
    private var tagName: String? = null

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: ArtistAdapter

    private lateinit var binding: FragmentArtistBinding
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
        return inflater.inflate(R.layout.fragment_artist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtistBinding.bind(view)
        viewModel = ViewModelProvider(this, factory).get(GenreDetailViewModel::class.java)
        binding.recyclerView.adapter = adapter
        observeArtistList()
    }

    private fun observeArtistList() {
        viewModel.observeTopArtists(tagName!!).removeObservers(viewLifecycleOwner)
        viewModel.observeTopArtists(tagName!!).observe(this, Observer {
            if (it != null)
                when (it.status) {
                    Status.LOADING -> {}
                    Status.SUCCESS -> adapter.setData(
                        it.data?.topartists?.artist as ArrayList<Artist>,
                        this
                    )
                    Status.ERROR -> adapter.displayError(it.message!!)
                }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ArtistFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun artistClickListener(artist: Artist) {
    }
}