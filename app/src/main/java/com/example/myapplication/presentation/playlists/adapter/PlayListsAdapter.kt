package com.example.myapplication.presentation.playlists.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.PlaylistModel
import com.example.myapplication.databinding.ItemYouTubeBinding
import com.example.myapplication.presentation.playlistsItems.PlayListsItemsFragment

class PlayListsAdapter(private val onClickItem:(playlistsModelItem:PlaylistModel.Item)->Unit)
    : RecyclerView.Adapter<PlayListsAdapter.ViewHolder>() {

    private var list = mutableListOf<PlaylistModel.Item>()

    @SuppressLint("NotifyDataSetChanged")
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

    inner class ViewHolder(private val binding: ItemYouTubeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PlaylistModel.Item) {
            binding.titleTextView.text = item.snippet.title
            binding.tvVideoSeries.text=item.contentDetails.itemCount.toString()+"video series"
            Glide.with(binding.root)
                .load(item.snippet.thumbnails.high.url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivPlaylist)
            itemView.setOnClickListener{onClickItem(item)}

        }
    }
}
