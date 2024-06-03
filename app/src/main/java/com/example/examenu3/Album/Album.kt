package com.example.examenu3.Album

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    var nombre: String,
    @PrimaryKey @ColumnInfo(name = "clave_album") var claveAlbum: String,
    var grupo: String,
    var añoLanzamiento: Int
)
