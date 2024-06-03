package com.example.examenu3.Album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examenu3.R

class AlbumAdapter(val xyz: (Album) -> Unit) : ListAdapter<Album, AlbumAdapter.AlbumViewHolder>
    (
    AlbumComparator()
) {

    class AlbumViewHolder(album_item: View, val xyz: (Album) -> Unit) : RecyclerView.ViewHolder(album_item){
        val tvNombre = album_item.findViewById<TextView>(R.id.tvNombre)
        val tvLanzamiento = album_item.findViewById<TextView>(R.id.tvLanzamiento)
        val tvGrupo = album_item.findViewById<TextView>(R.id.tvGrupo)

        fun bind(album: Album){
            tvNombre.text = album.nombre
            tvLanzamiento.text = album.añoLanzamiento.toString()
            tvGrupo.text = album.grupo

            itemView.setOnClickListener {
                xyz(album)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val album_item =
            LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return AlbumViewHolder(album_item, xyz)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album)
    }

    class AlbumComparator : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.claveAlbum == newItem.claveAlbum
        }
    }
}