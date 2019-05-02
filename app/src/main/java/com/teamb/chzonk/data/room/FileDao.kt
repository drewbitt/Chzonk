package com.teamb.chzonk.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.teamb.chzonk.data.model.ComicFile

@Dao
interface FileDao {

    @Query("select * from ComicFile")
    fun getAllBookFiles(): List<ComicFile>

    @Query("select * from ComicFile")
    fun getAllBookFilesLiveData(): LiveData<List<ComicFile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFile(file: ComicFile)

    @Delete
    fun deleteFile(file: ComicFile)

    // currently only used in testing
    @Query("DELETE FROM ComicFile")
    fun deleteFilesAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFile(file: ComicFile)
}
