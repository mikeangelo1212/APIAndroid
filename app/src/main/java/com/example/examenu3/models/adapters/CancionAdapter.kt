package com.example.examenu3.models.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.examenu3.R
import com.example.examenu3.Cancion


class CancionAdapter(val function: (Cancion) -> Unit): ListAdapter<Cancion, CancionAdapter.CancionViewHolder>(CancionComparator()) {
    class CancionViewHolder(cancion_item: View, val function: (Cancion) -> Unit): RecyclerView.ViewHolder(cancion_item){
        val tvIdCancion = cancion_item.findViewById<TextView>(R.id.tvIdCancion)
        val tvTitulo = cancion_item.findViewById<TextView>(R.id.tvTitulo)
        val tvArtista = cancion_item.findViewById<TextView>(R.id.tvArtista)

        fun bind(cancion: Cancion) {
            tvIdCancion.text = cancion.idCancion.toString()
            tvTitulo.text = cancion.Titulo
            tvArtista.text = cancion.Artista

            itemView.setOnClickListener {
                function(cancion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancionViewHolder {
        val cancion_item = LayoutInflater.from(parent.context).inflate(R.layout.item_cancion, parent, false)
        return CancionViewHolder(cancion_item, function)
    }

    override fun onBindViewHolder(holder: CancionViewHolder, position: Int) {
        val cancion = getItem(position)
        holder.bind(cancion)
    }

    class CancionComparator: DiffUtil.ItemCallback<Cancion>() {
        override fun areItemsTheSame(oldItem: Cancion, newItem: Cancion): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Cancion, newItem: Cancion): Boolean {
            return oldItem.idCancion == newItem.idCancion
        }
    }
}