package com.example.taller4.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4.DataBase.Autor.Autor
import com.example.taller4.DataBase.Autor.AutorDAO
import com.example.taller4.DataBase.Libro.Libro
import com.example.taller4.DataBase.Libro.LibroDAO

class AutorRepository(private val autorDAO: AutorDAO) {

    val allAutores: LiveData<List<Autor>> = autorDAO.getAllAutor()

    @WorkerThread
    suspend fun insert(autor: Autor) {
        autorDAO.insert(autor)
    }

}