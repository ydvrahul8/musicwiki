package com.example.musicwiki.ui.tags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.musicwiki.network.TagAPIRequests
import com.example.musicwiki.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class TagsViewModel @Inject constructor(private val apiRequests: TagAPIRequests) : ViewModel() {

    fun observeTags() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(data = apiRequests.getTopTags())
            )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = "Something went wrong...."))
        }
    }
}