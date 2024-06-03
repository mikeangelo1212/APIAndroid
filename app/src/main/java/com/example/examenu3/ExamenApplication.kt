package com.example.examenu3

import android.app.Application
import com.example.examenu3.Album.AlbumDatabase
import com.example.examenu3.Album.AlbumRepository

class ExamenApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AlbumDatabase.getDatabase(this) }
    val repository by lazy { AlbumRepository(database.albumDao()) }
}