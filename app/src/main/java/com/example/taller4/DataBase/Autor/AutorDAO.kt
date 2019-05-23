package com.example.taller4.DataBase.Autor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AutorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(autor: Autor)

    @Query("SELECT * FROM autor_Table")
    fun getAllAutor(): LiveData<List<Autor>>

    @Query("DELETE FROM autor_Table")
    fun nukeTable()

}