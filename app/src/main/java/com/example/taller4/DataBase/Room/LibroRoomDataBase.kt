package com.example.taller4.DataBase.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taller4.DataBase.Autor.Autor
import com.example.taller4.DataBase.Autor.AutorDAO
import com.example.taller4.DataBase.Editorial.Editorial
import com.example.taller4.DataBase.Editorial.EditorialDAO
import com.example.taller4.DataBase.Libro.Libro
import com.example.taller4.DataBase.Libro.LibroDAO
import com.example.taller4.DataBase.Tag.Tag
import com.example.taller4.DataBase.Tag.TagDAO
import com.example.taller4.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Autor::class, Editorial::class, Tag::class, Libro::class], version = 5, exportSchema = false)
abstract class LibroRoomDataBase: RoomDatabase() {

    abstract fun autorDao(): AutorDAO

    abstract fun editorialDao(): EditorialDAO

    abstract fun tagDao(): TagDAO

    abstract fun libroDao(): LibroDAO

    companion object {
        @Volatile
        private var INSTANCE: LibroRoomDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): LibroRoomDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibroRoomDataBase::class.java,
                    "word_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(LibroDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class LibroDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.autorDao(),database.editorialDao(),database.tagDao(),database.libroDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(autorDAO: AutorDAO, editorialDAO: EditorialDAO, tagDAO: TagDAO,libroDao: LibroDAO) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            autorDAO.nukeTable()
            editorialDAO.nukeTable()
            tagDAO.nukeTable()
            libroDao.deleteAll()

        }
    }

}