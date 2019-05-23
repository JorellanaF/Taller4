package com.example.taller4.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4.DataBase.Editorial.Editorial
import com.example.taller4.DataBase.Editorial.EditorialDAO

class EditorialRepository(private val editorialDAO: EditorialDAO) {

    val allEditoriales: LiveData<List<Editorial>> = editorialDAO.getAllEditorial()

    @WorkerThread
    suspend fun insert(editorial: Editorial) {
        editorialDAO.insert(editorial)
    }

}