package com.example.examenu3.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.examenu3.Album.AlbumRepository
import com.example.examenu3.Cancion
import com.example.examenu3.network.RetrofitObject
import com.example.examenu3.utils.TokenManager
import kotlinx.coroutines.launch

class SecondFragmentViewModel(private val repository: AlbumRepository) : ViewModel() {

    private val _cancion = MutableLiveData<List<Cancion>>()
    val cancion: LiveData<List<Cancion>> get() = _cancion

    fun insertCancion(cancion: Cancion) = viewModelScope.launch {
        //repository.insert(cancion)
        try {
            RetrofitObject.cancionesApi.addCancion(TokenManager.leerToken(), cancion)
        }
        catch (e: Exception) {
            Log.d("Test", "Trono el insert: ${e.message}")
        }
    }

    fun updateCancion(cancion: Cancion) {
        viewModelScope.launch {
            try {
                RetrofitObject.cancionesApi.modificarCancion(TokenManager.leerToken(), cancion.id.toString(), cancion)
            }
            catch (e: Exception) {
                Log.d("Test", "No se actualizó aaaaaaaaa: ${e.message}")
            }
        }
    }

    fun deleteCancion(ID: String) {
        viewModelScope.launch {
            try {
                RetrofitObject.cancionesApi.eliminarCancion(TokenManager.leerToken(), ID)
            }
            catch (e: Exception) {
                Log.d("Test", "No se puele borrar: ${e.message}")
            }
        }
    }

//    fun getCancion(ID: String) {
//        viewModelScope.launch {
//            try {
//                val result = RetrofitObject.cancionesApi.getEmployee(ID)
//                _empleado.postValue(result)
//            }
//            catch (e: Exception) {
//                Log.d("ERROR", "Algo tronó: ${e.message.toString()}")
//            }
//        }
////        return liveData {
////            emit(repository.getItem(ID))
////        }
//    }

}

class SecondFragmentViewModelFactory(private val repository: AlbumRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SecondFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}