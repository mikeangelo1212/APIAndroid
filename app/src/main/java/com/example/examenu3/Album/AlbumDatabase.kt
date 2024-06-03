package com.example.examenu3.Album

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Album::class], version = 1)
abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: AlbumDatabase? = null

        fun getDatabase(context: Context): AlbumDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumDatabase::class.java,
                    "album_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}