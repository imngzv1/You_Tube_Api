package com.example.myapplication.presentation.playvideo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.PlaylistModel
import com.example.myapplication.databinding.ItemYouTubeBinding
import com.example.myapplication.presentation.playlistsItems.adapter.PlayListsItemsAdapter

class PlayVideoAdapter(
    var arraylist: List<PlaylistModel>
): RecyclerView.Adapter<PlayVideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemYouTubeBinding.inflate(LayoutInflater.from(parent.context))
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(arraylist[position])
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    class ViewHolder(private val binding: ItemYouTubeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(playlist: PlaylistModel) {
            binding.titleTextView.text = playlist.etag
        }
    }
}