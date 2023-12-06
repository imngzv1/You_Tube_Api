package com.example.myapplication.presentation.playvideo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.presentation.playlists.PlaylistViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.playvideo.adapter.PlayVideoAdapter

class PlayVideoFragment : Fragment() {
    private val viewModel by viewModels<PlaylistViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler_view=view.findViewById<RecyclerView>(R.id.recyclerView_play_video)

        //Уведомление c LiveData из ViewModel

        }
    }


