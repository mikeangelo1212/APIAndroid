package com.example.api

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.network.ApiClient
import com.example.api.network.ApiService
import com.example.api.network.Videojuego
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VideojuegoAdapter
    private lateinit var videojuegos: MutableList<Videojuego>
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var nombreEditText: EditText
    private lateinit var formatoEditText: EditText
    private lateinit var plataformaEditText: EditText
    private lateinit var costoEditText: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = ApiClient.retrofit.create(ApiService::class.java)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        videojuegos = mutableListOf()
        adapter = VideojuegoAdapter(videojuegos, { nombre ->
            deleteVideojuego(nombre)
        }, { videojuego ->
            showUpdateDialog(videojuego)
        })
        recyclerView.adapter = adapter

        nombreEditText = findViewById(R.id.nombreEditText)
        formatoEditText = findViewById(R.id.formatoEditText)
        plataformaEditText = findViewById(R.id.plataformaEditText)
        costoEditText = findViewById(R.id.costoEditText)
        addButton = findViewById(R.id.addButton)

        loadVideojuegos()  // Llamada al método para cargar los videojuegos al iniciar
        

        addButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val formato = formatoEditText.text.toString()
            val plataforma = plataformaEditText.text.toString()
            val costo = costoEditText.text.toString().toDoubleOrNull()

            if (nombre.isNotEmpty() && formato.isNotEmpty() && plataforma.isNotEmpty() && costo != null) {
                val nuevoVideojuego = Videojuego(
                    id = null,
                    nombre = nombre,
                    formato = formato,
                    plataforma = plataforma,
                    costo = costo
                )
                addVideojuego(nuevoVideojuego)
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadVideojuegos() {
        apiService.getVideojuegos().enqueue(object : Callback<List<Videojuego>> {
            override fun onResponse(
                call: Call<List<Videojuego>>,
                response: Response<List<Videojuego>>
            ) {
                if (response.isSuccessful) {
                    videojuegos.clear()
                    response.body()?.let { videojuegos.addAll(it) }
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error al cargar videojuegos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Videojuego>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun searchVideojuegoPorNombre(nombre: String) {
        apiService.getVideojuegoPorNombre(nombre).enqueue(object : Callback<List<Videojuego>> {
            override fun onResponse(
                call: Call<List<Videojuego>>,
                response: Response<List<Videojuego>>
            ) {
                if (response.isSuccessful) {
                    videojuegos.clear()
                    response.body()?.let { videojuegos.addAll(it) }
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Videojuego no encontrado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Videojuego>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun addVideojuego(videojuego: Videojuego) {
        apiService.addVideojuego(videojuego).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@MainActivity,
                        "Videojuego agregado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    loadVideojuegos()
                    clearForm()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error al agregar videojuego",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deleteVideojuego(nombre: String) {
        apiService.deleteVideojuego(nombre).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@MainActivity,
                        "Videojuego eliminado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    loadVideojuegos()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error al eliminar videojuego",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showUpdateDialog(videojuego: Videojuego) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Actualizar Videojuego")

        val view = layoutInflater.inflate(R.layout.dialog_update_videojuego, null)
        val nombreEditText = view.findViewById<EditText>(R.id.nombreEditText)
        val formatoEditText = view.findViewById<EditText>(R.id.formatoEditText)
        val plataformaEditText = view.findViewById<EditText>(R.id.plataformaEditText)
        val costoEditText = view.findViewById<EditText>(R.id.costoEditText)

        nombreEditText.setText(videojuego.nombre)
        formatoEditText.setText(videojuego.formato)
        plataformaEditText.setText(videojuego.plataforma)
        costoEditText.setText(videojuego.costo.toString())

        builder.setView(view)
        builder.setPositiveButton("Actualizar") { _, _ ->
            val nombre = nombreEditText.text.toString()
            val formato = formatoEditText.text.toString()
            val plataforma = plataformaEditText.text.toString()
            val costo = costoEditText.text.toString().toDoubleOrNull()

            if (nombre.isNotEmpty() && formato.isNotEmpty() && plataforma.isNotEmpty() && costo != null) {
                val updatedVideojuego = Videojuego(
                    id = null,
                    nombre = nombre,
                    formato = formato,
                    plataforma = plataforma,
                    costo = costo
                )
                updateVideojuego(videojuego.nombre, updatedVideojuego)
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun updateVideojuego(originalNombre: String, updatedVideojuego: Videojuego) {
        apiService.updateVideojuego(originalNombre, updatedVideojuego)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@MainActivity,
                            "Videojuego actualizado exitosamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        loadVideojuegos()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Error al actualizar videojuego",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
    private fun clearForm() {
        nombreEditText.text.clear()
        formatoEditText.text.clear()
        plataformaEditText.text.clear()
        costoEditText.text.clear()
    }
}