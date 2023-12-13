package com.example.myapplication.presentation.playlistsItems.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.PlaylistModel
import com.example.myapplication.databinding.FragmentPlayVideoBinding
import com.example.myapplication.databinding.ItemPlayListsVideoBinding
import com.example.myapplication.databinding.ItemYouTubeBinding

class PlayListsItemsAdapter (): RecyclerView.Adapter<PlayListsItemsAdapter.ViewHolder>() {

    private var videolist = mutableListOf<PlaylistModel.Item>()

    fun addVideoData(PlayListsVideoData:List<PlaylistModel.Item>?){
        videolist.clear()
        if(PlayListsVideoData!=null){
            videolist.addAll(PlayListsVideoData)
        }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPlayListsVideoBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(videolist[position])
    }

    override fun getItemCount(): Int {
        return videolist.size
    }

    class ViewHolder(private val binding: ItemPlayListsVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(playlistVideoModel: PlaylistModel.Item) {
            binding.titleTVPlayListsItems.text = playlistVideoModel.snippet.title
            Glide.with(binding.root)
                .load(playlistVideoModel.snippet.thumbnails.high.url)
                .placeholder(R.drawable.ic_launcher_background) // замените на свой ресурс
                .error(R.drawable.ic_launcher_background) // замените на свой ресурс
                .into(binding.ivPlaylistItems)

        }
    }
}