package com.example.musicwiki.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicwiki.ui.genresDetail.fragments.AlbumFragment
import com.example.musicwiki.ui.genresDetail.fragments.ArtistFragment
import com.example.musicwiki.ui.genresDetail.fragments.TrackFragment


class ViewPagerFragmentAdapter(
    fragmentActivity: FragmentActivity,
    private val tagName: String?
) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AlbumFragment.newInstance(tagName!!, "")
            1 -> ArtistFragment.newInstance(tagName!!, "")
            2 -> TrackFragment.newInstance(tagName!!, "")
            else -> throw Exception("Array out of bound")
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}