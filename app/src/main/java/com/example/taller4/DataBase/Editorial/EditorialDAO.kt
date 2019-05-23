package com.example.taller4.DataBase.Editorial

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EditorialDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(editorial: Editorial)

    @Query("SELECT * FROM editorial_Table")
    fun getAllEditorial(): LiveData<List<Editorial>>

    @Query("DELETE FROM editorial_Table")
    fun nukeTable()

}