package com.example.examenu3.utils

import android.util.Log
import com.example.examenu3.User

object TokenManager {
    private var _token: String = ""
    private var _usuario= User(null,"","",0)
    val usuario: User get() = _usuario

    fun guardarToken(token: String) {
        _token = token
    }

    fun guardarUsuario(user: User) {
        _usuario = user
    }

    fun leerToken(): String {
        return _token
    }

    fun borrarToken() {
        _token = ""
        _usuario=User(null,"","",0)
        Log.d("Test", "Token borrado")
    }
}