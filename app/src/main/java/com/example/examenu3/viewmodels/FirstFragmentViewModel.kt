package com.example.examenu3.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.examenu3.models.Test
import com.example.examenu3.network.RetrofitObject
import com.example.examenu3.utils.TokenManager
import com.example.examenu3.Album.AlbumRepository
import com.example.examenu3.Cancion
import kotlinx.coroutines.launch

class FirstFragmentViewModel(private val repository: AlbumRepository) : ViewModel() {
    //val allAlbumes : LiveData<List<Album>> = repository.albumes.asLiveData()
    private val _canciones = MutableLiveData<List<Cancion>>()
    val canciones: LiveData<List<Cancion>> get() = _canciones


    fun getCanciones() {
        viewModelScope.launch {
            try {
                Log.d("Test", "Va a llamar a todos los canciones")
                val result = RetrofitObject.cancionesApi.getCanciones(TokenManager.leerToken())
                _canciones.postValue(result)
            }
            catch (e: Exception) {
                Log.d("Test", "Valio goma: ${e.message}")
            }
        }
    }
}



class FirstFragmentViewModelFactory(private val repository: AlbumRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FirstFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}