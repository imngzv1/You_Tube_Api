package com.example.myapplication.presentation.playlistsItems

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.presentation.playlists.PlaylistViewModel
import com.example.myapplication.R
import com.example.myapplication.core.network.Retrofit
import com.example.myapplication.data.model.PlaylistModel
import com.example.myapplication.databinding.FragmentPlayListsItemsBinding
import com.example.myapplication.databinding.FragmentPlayVideoBinding
import com.example.myapplication.domain.repository.Repository
import com.example.myapplication.presentation.playlists.Key
import com.example.myapplication.presentation.playlists.adapter.PlayListsAdapter
import com.example.myapplication.presentation.playlistsItems.adapter.PlayListsItemsAdapter
import com.example.myapplication.utils.Status


class PlayListsItemsFragment : Fragment() {
    private lateinit var binding:FragmentPlayListsItemsBinding
    private val viewModel =VideoOfPlayListsViewModel(Repository(Retrofit().createApiService()))
    private val adapter= PlayListsItemsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayListsItemsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initResultListener()
        /*val playlistId = arguments?.getString("playlistId")
        if (playlistId != null) {
            viewModel.getVideoOfPlayLists(playlistId).observe(viewLifecycleOwner){resource->
                when(resource.status){
                    Status.SUCCESS ->{
                        resource.data?.let {
                            binding.recyclerView.adapter=adapter
                            adapter.addVideoData(it.items)}
                        Toast.makeText(requireContext(), "Успешно", Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), "Error ${resource.code}", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }*/
    }

    private fun initResultListener() {
        setFragmentResultListener(Key.KEY_TO_VIDEOS){_,bundle ->
            bundle.getSerializable(Key.KEY_SET_VIDEO_TO_FRAGMENT_ITEMS)?.let {item->
                val playlistItem = item as PlaylistModel.Item
                Toast.makeText(requireContext(), "Успешно", Toast.LENGTH_SHORT).show()
                viewModel.getVideoOfPlayLists(playlistItem.id).observe(viewLifecycleOwner){resource->
                    when(resource.status){
                        Status.SUCCESS ->{
                            resource.data?.let {
                                binding.recyclerView.adapter=adapter
                                adapter.addVideoData(it.items)}
                            Toast.makeText(requireContext(), "Успешно", Toast.LENGTH_SHORT).show()
                        }
                        Status.ERROR -> {
                            Toast.makeText(requireContext(), "Error ${resource.code}", Toast.LENGTH_SHORT).show()
                        }
                        Status.LOADING -> {
                            Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

