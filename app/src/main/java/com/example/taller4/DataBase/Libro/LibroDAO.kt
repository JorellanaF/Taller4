package com.example.taller4.DataBase.Libro

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taller4.DataBase.Autor.Autor
import com.example.taller4.DataBase.Editorial.Editorial
import com.example.taller4.DataBase.Tag.Tag

@Dao
interface LibroDAO {

    /*TODO("EL METODO PARA MOSTRAR DEBE SER DIFERENTE PARA OBTENER LOS DATOS DE LAS OTRAS TABLAS")*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libro: Libro)

    /*
    * SELECT p.id_pelicula, p.titulo, p.anoLanzamiento, p.longitudMinutos, p.sinopsis,
c.nombre AS nombreCompania,
d.nombre as nombreDirector
FROM PELICULAS_DIRECTORES AS pd
INNER JOIN PELICULAS AS p ON p.id_pelicula = pd.id_pelicula
INNER JOIN DIRECTORES AS d ON d.id_director = pd.id_director
INNER JOIN COMPANIAS AS c ON p.id_compania = c.id_compania;
    * */

    @Query("SELECT *FROM libro_Table")
    fun getAllLibros(): LiveData<List<Libro>>

    @Query("DELETE FROM LIBRO_TABLE")
    fun deleteAll()

}