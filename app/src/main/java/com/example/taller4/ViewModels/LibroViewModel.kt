package com.example.taller4.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taller4.DataBase.Libro.Libro
import com.example.taller4.DataBase.Room.LibroRoomDataBase
import com.example.taller4.Repository.LibroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LibroViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LibroRepository

    val allLibros: LiveData<List<Libro>>

    init {
        val librosDao = LibroRoomDataBase.getDatabase(application, viewModelScope).libroDao()
        repository = LibroRepository(librosDao)
        allLibros = repository.allLibros
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(libro: Libro) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(libro)
    }

}