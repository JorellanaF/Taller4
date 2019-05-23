package com.example.taller4.DataBase.Tag

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_Table")
data class Tag(
    @PrimaryKey
    @ColumnInfo(name = "nombre_tag") val tag: String
) {
}