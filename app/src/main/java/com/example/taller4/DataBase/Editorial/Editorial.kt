package com.example.taller4.DataBase.Editorial

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "editorial_Table")
data class Editorial(
    @PrimaryKey
    @ColumnInfo(name = "nombre_editorial") val nombre_editorial: String
) {
}