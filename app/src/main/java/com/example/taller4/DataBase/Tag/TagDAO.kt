package com.example.taller4.DataBase.Tag

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TagDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tag: Tag)

    @Query("SELECT * FROM tag_Table")
    fun getAllTag(): LiveData<List<Tag>>

    @Query("DELETE FROM tag_Table")
    fun nukeTable()

}