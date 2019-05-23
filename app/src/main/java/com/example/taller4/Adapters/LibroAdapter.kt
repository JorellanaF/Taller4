package com.example.taller4.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taller4.DataBase.Autor.Autor
import com.example.taller4.DataBase.Editorial.Editorial
import com.example.taller4.DataBase.Libro.Libro
import com.example.taller4.DataBase.Tag.Tag
import com.example.taller4.R

class LibroAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var libros = emptyList<Libro>() // Cached copy of words
    private var autores = emptyList<Autor>()
    private var editoriales = emptyList<Editorial>()
    private var tags = emptyList<Tag>()

    inner class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val libroItemView: TextView = itemView.findViewById(R.id.tv_nombre_libro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val itemView = inflater.inflate(R.layout.cardview_libro, parent, false)
        return LibroViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val current = libros[position]
        holder.libroItemView.text = current.titulo
    }

    internal fun setLibros(libros: List<Libro>) {
        this.libros = libros
        notifyDataSetChanged()
    }

    internal fun setAutores(autores: List<Autor>) {
        this.autores = autores
        notifyDataSetChanged()
    }

    internal fun setEditoriales(editoriales: List<Editorial>) {
        this.editoriales = editoriales
        notifyDataSetChanged()
    }

    internal fun setTags(tags: List<Tag>) {
        this.tags = tags
        notifyDataSetChanged()
    }

    override fun getItemCount() = libros.size
}