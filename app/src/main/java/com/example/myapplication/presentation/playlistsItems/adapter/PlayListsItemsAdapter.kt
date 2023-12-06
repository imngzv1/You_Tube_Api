package com.example.myapplication.presentation.playlistsItems.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.PlaylistModel
import com.example.myapplication.databinding.ItemYouTubeBinding

class PlayListsItemsAdapter (
    var arraylist: MutableList<PlaylistModel.Item>
): RecyclerView.Adapter<PlayListsItemsAdapter.ViewHolder>() {
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
        fun onBind(playlist: PlaylistModel.Item) {
            binding.titleTextView.text = playlist.snippet.title
        }
    }
}