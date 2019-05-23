package com.example.taller4.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taller4.DataBase.Editorial.Editorial
import com.example.taller4.DataBase.Room.LibroRoomDataBase
import com.example.taller4.Repository.EditorialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditorialViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EditorialRepository

    val allEditoriales: LiveData<List<Editorial>>

    init {
        val editorialDao = LibroRoomDataBase.getDatabase(application, viewModelScope).editorialDao()
        repository = EditorialRepository(editorialDao)
        allEditoriales = repository.allEditoriales
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(editorial: Editorial) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(editorial)
    }

}