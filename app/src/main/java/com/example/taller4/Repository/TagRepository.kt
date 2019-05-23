package com.example.taller4.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.taller4.DataBase.Tag.Tag
import com.example.taller4.DataBase.Tag.TagDAO

class TagRepository(private val tagDAO: TagDAO) {

    val allTag: LiveData<List<Tag>> = tagDAO.getAllTag()

    @WorkerThread
    suspend fun insert(tag: Tag) {
        tagDAO.insert(tag)
    }

}