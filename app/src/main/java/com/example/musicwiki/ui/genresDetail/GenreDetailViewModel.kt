package com.example.musicwiki.ui.genresDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.musicwiki.network.GenreDetailAPIRequest
import com.example.musicwiki.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GenreDetailViewModel @Inject constructor(private val apiRequest: GenreDetailAPIRequest) :
    ViewModel() {

    fun observeTagDetail(tag: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRequest.getTagDetails(tag)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = "Something went wrong"))
        }
    }

    fun observeTopAlbums(tag: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRequest.getTopAlbumsList(tag)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = "Something went wrong"))
        }
    }

    fun observeTopArtists(tag: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRequest.getTopArtistsList(tag)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = "Something went wrong"))
        }
    }

    fun observerTopTracks(tag: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRequest.getTopTrackList(tag)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = "Something went wrong"))
        }
    }

}