package com.example.musicwiki.ui.genresDetail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.R
import com.example.musicwiki.ViewModelProviderFactory
import com.example.musicwiki.databinding.FragmentTrackBinding
import com.example.musicwiki.models.tracks.Track
import com.example.musicwiki.ui.adapter.TrackAdapter
import com.example.musicwiki.ui.genresDetail.GenreDetailViewModel
import com.example.musicwiki.utils.OnTrackClickListener
import com.example.musicwiki.utils.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"

class TrackFragment : DaggerFragment(), OnTrackClickListener {
    private var tagName: String? = null

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: TrackAdapter
    private lateinit var binding: FragmentTrackBinding
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
        return inflater.inflate(R.layout.fragment_track, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTrackBinding.bind(view)
        viewModel = ViewModelProvider(this, factory).get(GenreDetailViewModel::class.java)
        binding.recyclerView.adapter = adapter
        observeTrackList()
    }

    private fun observeTrackList() {
        viewModel.observerTopTracks(tagName!!).removeObservers(viewLifecycleOwner)
        viewModel.observerTopTracks(tagName!!).observe(this, Observer {
            if (it != null)
                when (it.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> adapter.setData(
                        it.data?.tracks?.track as ArrayList<Track>,
                        this
                    )
                    Status.ERROR -> adapter.displayError(it.message!!)
                }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TrackFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun trackClickListener(track: Track) {

    }
}