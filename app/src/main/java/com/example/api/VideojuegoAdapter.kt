package com.example.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api.network.Videojuego

class VideojuegoAdapter(
    private val videojuegos: MutableList<Videojuego>,
    private val onDelete: (String) -> Unit,
    private val onUpdate: (Videojuego) -> Unit
) : RecyclerView.Adapter<VideojuegoAdapter.VideojuegoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideojuegoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_videojuego, parent, false)
        return VideojuegoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideojuegoViewHolder, position: Int) {
        val videojuego = videojuegos[position]
        holder.nombreTextView.text = videojuego.nombre
        holder.formatoTextView.text = videojuego.formato
        holder.plataformaTextView.text = videojuego.plataforma
        holder.costoTextView.text = videojuego.costo.toString()

        holder.deleteButton.setOnClickListener {
            onDelete(videojuego.nombre)
        }

        holder.updateButton.setOnClickListener {
            onUpdate(videojuego)
        }
    }

    override fun getItemCount(): Int {
        return videojuegos.size
    }

    class VideojuegoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val formatoTextView: TextView = itemView.findViewById(R.id.formatoTextView)
        val plataformaTextView: TextView = itemView.findViewById(R.id.plataformaTextView)
        val costoTextView: TextView = itemView.findViewById(R.id.costoTextView)
        val updateButton: Button = itemView.findViewById(R.id.updateButton)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }
}
