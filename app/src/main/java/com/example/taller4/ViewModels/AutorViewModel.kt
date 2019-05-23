package com.example.taller4.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taller4.DataBase.Autor.Autor
import com.example.taller4.DataBase.Room.LibroRoomDataBase
import com.example.taller4.Repository.AutorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AutorViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AutorRepository

    val allAutores: LiveData<List<Autor>>

    init {
        val autorDao = LibroRoomDataBase.getDatabase(application, viewModelScope).autorDao()
        repository = AutorRepository(autorDao)
        allAutores = repository.allAutores
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(autor: Autor) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(autor)
    }

}