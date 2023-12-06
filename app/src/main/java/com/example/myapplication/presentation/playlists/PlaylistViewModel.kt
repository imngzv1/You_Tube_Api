package com.example.myapplication.presentation.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.PlaylistModel
import com.example.myapplication.domain.repository.Repository
import com.example.myapplication.utils.Resource

class PlaylistViewModel(private val repository: Repository) : ViewModel() {
    fun getPlaylist(): LiveData<Resource<PlaylistModel>>{
        return repository.getPlaylists()
    }
}