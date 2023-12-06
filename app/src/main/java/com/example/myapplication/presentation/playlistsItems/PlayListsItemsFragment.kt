package com.example.myapplication.presentation.playlistsItems

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.presentation.playlists.PlaylistViewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.playlistsItems.adapter.PlayListsItemsAdapter


class PlayListsItemsFragment : Fragment() {

    private val viewModel by viewModels<PlaylistViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_play_lists_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val buttonNavigate = view.findViewById<Button>(R.id.d)
        val recycler_view=view.findViewById<RecyclerView>(R.id.recyclerView_items)

        //Уведомление c LiveData из ViewModel


            buttonNavigate.setOnClickListener {
                findNavController().navigate(R.id.action_playListsItemsFragment_to_playVideoFragment)
            }
        }
}

