package com.example.myapplication.core.network

import com.example.myapplication.data.model.PlaylistModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {
    @GET("playlists")
    fun getPlaylists(
        @Query("part")
        part:String,
        @Query("channelId")
        channelId:String,
        @Query("key")
        apiKey:String,
        @Query("maxResults")
        maxResult:Int
    ): Call<PlaylistModel>


    @GET("playlistItems")
    fun getPlaylistItem(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int = 20
    ): Call<PlaylistModel>
}