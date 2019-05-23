package com.example.taller4.DataBase.Libro

import androidx.room.*
import com.example.taller4.DataBase.Autor.Autor
import com.example.taller4.DataBase.Editorial.Editorial
import com.example.taller4.DataBase.Tag.Tag
import com.example.taller4.R

@Entity(
    tableName = "libro_Table",
    foreignKeys =
    arrayOf(
        ForeignKey(
            entity = Autor::class,
            parentColumns = arrayOf("nombre_autor"),
            childColumns = arrayOf("nombre_Autor"),
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = Editorial::class,
            parentColumns = arrayOf("nombre_editorial"),
            childColumns = arrayOf("nombre_Editorial"),
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = Tag::class,
            parentColumns = arrayOf("nombre_tag"),
            childColumns = arrayOf("nombre_Tag"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class Libro(
    @ColumnInfo(name = "titulo") val titulo: String,
    @ColumnInfo(name = "nombre_Autor", index = true)
    val nombreAutor: String,                  //TODO("UN AUTOR PUEDE TENER VARIOS LIBROS")
    @ColumnInfo(name = "nombre_Editorial", index = true)
    val nombreEditorial: String,         //TODO("UNA EDITORIAL PUEDE TENER VARIOS LIBROS")
    @ColumnInfo(name = "nombre_Tag", index = true)
    val nombreTag: String                     //TODO("UN TAG PUEDE ESTAR RELACIONADO A VARIOS LIBROS")
) {
    @PrimaryKey(autoGenerate = true) var ID: Long = 0
    var imagen: Int = R.mipmap.ic_libro
    var favorito: Boolean = false
}