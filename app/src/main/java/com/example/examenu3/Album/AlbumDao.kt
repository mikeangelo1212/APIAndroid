package com.example.examenu3.Album

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg m: Album)

    @Update
    fun update(m: Album)

    @Query("SELECT * FROM Album")
    fun getAll(): Flow<List<Album>>

    //editar, saca el objeto
    @Query("SELECT * FROM Album WHERE clave_album=:clave_album")
    suspend fun getItem(clave_album: String): Album?

    //delete
    @Query("DELETE FROM Album WHERE clave_album = :clave_album")
    suspend fun delete(clave_album: String)
}