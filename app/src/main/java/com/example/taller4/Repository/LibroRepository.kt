package com.example.taller4.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4.DataBase.Libro.Libro
import com.example.taller4.DataBase.Libro.LibroDAO

class LibroRepository(private val libroDAO: LibroDAO) {

    val allLibros: LiveData<List<Libro>> = libroDAO.getAllLibros()

    @WorkerThread
    suspend fun insert(libro: Libro) {
        libroDAO.insert(libro)
    }

}