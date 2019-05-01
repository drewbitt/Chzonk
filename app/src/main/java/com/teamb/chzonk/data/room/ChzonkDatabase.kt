package com.teamb.chzonk.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.teamb.chzonk.Constants
import com.teamb.chzonk.data.model.ComicFile

@Database(entities = [(ComicFile::class)], version = Constants.DATABASE_VERSION, exportSchema = false)
abstract class ChzonkDatabase : RoomDatabase() {
    abstract fun fileDao(): FileDao
}
