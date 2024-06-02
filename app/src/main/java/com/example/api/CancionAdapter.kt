package com.example.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.webservice.R
import com.example.webservice.models.cancion.Cancion


class CancionAdapter(val xyz: (Cancion) -> Unit) : ListAdapter<Cancion, CancionAdapter.CancionViewHolder>(
    CancionComparator()
) {

    class CancionViewHolder(album_item: View, val xyz: (Cancion) -> Unit) : RecyclerView.ViewHolder(album_item){
        val tvIdCancion = album_item.findViewById<TextView>(R.id.tvIdCancion)
        val tvTitulo = album_item.findViewById<TextView>(R.id.tvTitulo)
        val tvArtista = album_item.findViewById<TextView>(R.id.tvArtista)
        val tvIdAlbum= album_item.findViewById<TextView>(R.id.tvIdAlbum)

        fun bind(cancion: Cancion){

            tvIdCancion.text=cancion.id.toString()
            tvTitulo.text=cancion.Titulo
            tvArtista.text=cancion.Artista
            tvIdAlbum.text=cancion.Albumid.toString()

            itemView.setOnClickListener {
                xyz(cancion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancionViewHolder {
        val album_item =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cancion, parent, false)
        return CancionViewHolder(album_item, xyz)
    }

    override fun onBindViewHolder(holder: CancionViewHolder, position: Int) {
        val cancion = getItem(position)
        holder.bind(cancion)
    }

    class CancionComparator : DiffUtil.ItemCallback<Cancion>() {
        override fun areItemsTheSame(oldItem: Cancion, newItem: Cancion): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Cancion, newItem: Cancion): Boolean {
            return oldItem.id == newItem.id
        }
    }
}