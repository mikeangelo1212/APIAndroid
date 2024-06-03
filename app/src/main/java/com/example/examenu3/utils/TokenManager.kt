package com.example.examenu3.utils

object TokenManager {
    private var _token: String = ""
    private var _usuario: String = ""
    val usuario: String get() = _usuario

    fun guardarToken(token: String) {
        _token = token
    }

    fun guardarUsuario(user: String) {
        _usuario = user
    }

    fun leerToken(): String {
        return _token
    }

    fun borrarToken() {
        _token = ""
        _usuario = ""
    }
}