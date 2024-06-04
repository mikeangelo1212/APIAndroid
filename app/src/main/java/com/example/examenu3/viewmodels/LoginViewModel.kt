package com.example.examenu3.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.examenu3.Album.AlbumRepository
import com.example.examenu3.Token
import com.example.examenu3.User
import com.example.examenu3.network.RetrofitObject
import com.example.examenu3.utils.TokenManager
import kotlinx.coroutines.launch


class LoginViewModel(private val repository: AlbumRepository) : ViewModel() {
    private val _logginIn = MutableLiveData<Token>()
    val logginIn: LiveData<Token> get() = _logginIn

    fun login(user: User) {

        Log.d("Test", "Usuario capturado: ${user}")
        viewModelScope.launch{
            try {
                val result = RetrofitObject.cancionesApi.login(user)
                Log.d("Test", "Token obtenido de Retrofit: ${result}")
                if (result.token != ""&&result.token!="err") {
                    Log.d("Test", "Token recibido: ${result.token}")
                    TokenManager.guardarToken(result.token)
                    TokenManager.guardarUsuario(user)
                    _logginIn.postValue(result)
                }
                else {
                    Log.d("Test", "Token fallido: ${result.token}")
                }
            }
            catch (e: Exception) {
                Log.d("Test", "Error ${e.message}")
                TokenManager.guardarToken("err")
                _logginIn.postValue(Token("err"))
                Log.d("Test", "Error: ${TokenManager.leerToken()}")
            }
        }
    }
}
class LoginViewModelFactory(private val repository: AlbumRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}