package com.example.musicwiki.utils

import com.example.musicwiki.BaseClickListener
import com.example.musicwiki.models.album.Album
import com.example.musicwiki.models.artists.Artist
import com.example.musicwiki.models.tracks.Track

interface OnClickListener : BaseClickListener{
    public fun tagClickListener(tag: com.example.musicwiki.models.tags.Tag)
}

interface OnAlbumClickListener : BaseClickListener{
    public fun albumClickListener(album: Album)
}

interface OnArtistClickListener : BaseClickListener{
    public fun artistClickListener(artist: Artist)
}

interface OnTrackClickListener : BaseClickListener{
    public fun trackClickListener(track: Track)
}