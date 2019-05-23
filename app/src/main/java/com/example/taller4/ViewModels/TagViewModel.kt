package com.example.taller4.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taller4.DataBase.Room.LibroRoomDataBase
import com.example.taller4.DataBase.Tag.Tag
import com.example.taller4.Repository.TagRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TagViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TagRepository

    val allTags: LiveData<List<Tag>>

    init {
        val tagDao = LibroRoomDataBase.getDatabase(application, viewModelScope).tagDao()
        repository = TagRepository(tagDao)
        allTags = repository.allTag
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(tag: Tag) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(tag)
    }

}