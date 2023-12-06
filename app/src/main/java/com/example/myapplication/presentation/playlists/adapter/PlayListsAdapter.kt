package com.example.myapplication.presentation.playlists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.PlaylistModel
import com.example.myapplication.databinding.ItemYouTubeBinding

class PlayListsAdapter() : RecyclerView.Adapter<PlayListsAdapter.ViewHolder>() {

    private var list = mutableListOf<PlaylistModel.Item>()

    fun addData(playlistModelItem: List<PlaylistModel.Item>?){
        list.clear()
        if (playlistModelItem != null) {
            list.addAll(playlistModelItem)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemYouTubeBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ItemYouTubeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(playlistModel: PlaylistModel.Item) {
            binding.titleTextView.text = playlistModel.snippet.title

            Glide.with(binding.root)
                .load(playlistModel.snippet.thumbnails.high.url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivPlaylist)
        }
    }
}
