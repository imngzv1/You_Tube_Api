package com.example.myapplication.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.model.PlaylistModel
import com.example.myapplication.core.network.YouTubeApiService
import com.example.myapplication.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val youTubeApiService: YouTubeApiService) {

    fun getPlaylists(): LiveData<Resource<PlaylistModel>> {
        val resourceData = MutableLiveData<Resource<PlaylistModel>>()
        youTubeApiService.getPlaylists(
            part = "contentDetails,snippet",
            channelId = "UCWOA1ZGywLbqmigxE4Qlvuw",
            apiKey = BuildConfig.YOU_TUBE_API_KEY,
            maxResult = 20
        ).enqueue(object : Callback<PlaylistModel> {
            override fun onResponse(call: Call<PlaylistModel>, response: Response<PlaylistModel>) {
                if (response.isSuccessful) {
                    resourceData.value = Resource.success(response.body())
                } else {
                    resourceData.value = Resource.error(
                        msg = response.message(),
                        data = null,
                        code = response.code()
                    )
                }
            }

            override fun onFailure(call: Call<PlaylistModel>, t: Throwable) {
                resourceData.value = Resource.error(
                    msg = t.message,
                    data = null,
                    code = 429
                )
            }
        })
        return resourceData
    }
}