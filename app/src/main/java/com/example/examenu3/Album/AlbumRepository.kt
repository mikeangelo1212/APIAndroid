package com.example.examenu3.Album

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class AlbumRepository(private val albumDao: AlbumDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val albumes: Flow<List<Album>> = albumDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(album: Album) {
        albumDao.insertAll(album)
    }


    @WorkerThread
    suspend fun getItem(clave_album:String): Album?{
        return albumDao.getItem(clave_album)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(clave_album:String){
        albumDao.delete(clave_album)
    }
}