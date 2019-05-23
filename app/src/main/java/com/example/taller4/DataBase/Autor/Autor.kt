package com.example.taller4.DataBase.Autor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "autor_Table")
data class Autor(
    @PrimaryKey
    @ColumnInfo(name = "nombre_autor") val autor: String
) {
}