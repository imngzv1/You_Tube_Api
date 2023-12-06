package com.example.myapplication.presentation.playlists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.core.network.Retrofit
import com.example.myapplication.databinding.FragmentPlaylistBinding
import com.example.myapplication.domain.repository.Repository
import com.example.myapplication.presentation.playlists.adapter.PlayListsAdapter
import com.example.myapplication.utils.Status

class PlaylistFragment : Fragment() {
    private lateinit var binding:FragmentPlaylistBinding
    private val viewModel = PlaylistViewModel(Repository(Retrofit().createApiService()))
    private val adapter= PlayListsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPlaylist().observe(viewLifecycleOwner){resource->
            when(resource.status){
                Status.SUCCESS -> {
                    resource.data?.let {
                        binding.recyclerView.adapter = adapter
                        adapter.addData(it.items) }
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

