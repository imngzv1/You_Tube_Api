package com.example.myapplication.presentation.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.network.Retrofit
import com.example.myapplication.data.model.PlaylistModel
import com.example.myapplication.databinding.FragmentPlaylistBinding
import com.example.myapplication.domain.repository.Repository
import com.example.myapplication.presentation.playlists.adapter.PlayListsAdapter
import com.example.myapplication.utils.Status

class PlaylistFragment : Fragment() {
    private lateinit var binding: FragmentPlaylistBinding
    private val viewModel = PlaylistViewModel(Repository(Retrofit().createApiService()))
    private val adapter = PlayListsAdapter { playlistItem ->
        onClickItem(playlistItem)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        viewModel.getPlaylist().observe(viewLifecycleOwner) { resource->
            when (resource.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility=View.GONE
                    resource.data?.let {
                        binding.recyclerView.adapter = adapter
                        adapter.addData(it.items)
                    }
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), "Error ${resource.code}", Toast.LENGTH_SHORT)
                        .show()
                }

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "${resource.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initData() {
        viewModel.getPlaylist()
    }

    private fun onClickItem(playlistItem: PlaylistModel.Item) {
        setFragmentResult(
            Key.KEY_TO_VIDEOS, bundleOf(Key.KEY_SET_VIDEO_TO_FRAGMENT_ITEMS to playlistItem)
        )
        findNavController().navigate(R.id.playListsItemsFragment)
    }
}

