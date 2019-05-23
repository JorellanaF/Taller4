package com.example.taller4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller4.Adapters.LibroAdapter
import com.example.taller4.DataBase.Autor.Autor
import com.example.taller4.DataBase.Editorial.Editorial
import com.example.taller4.DataBase.Libro.Libro
import com.example.taller4.DataBase.Tag.Tag
import com.example.taller4.ViewModels.AutorViewModel
import com.example.taller4.ViewModels.EditorialViewModel
import com.example.taller4.ViewModels.LibroViewModel
import com.example.taller4.ViewModels.TagViewModel

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val newLibroActivityRequestCode = 1
    private lateinit var autorViewModel: AutorViewModel
    private lateinit var editorialViewModel: EditorialViewModel
    private lateinit var tagViewModel: TagViewModel
    private lateinit var libroViewModel: LibroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val recyclerView = findViewById<RecyclerView>(R.id.rv_libro)
        val adapter = LibroAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        /********************************  Autor  ******************************************/
        autorViewModel = ViewModelProviders.of(this).get(AutorViewModel::class.java)

        autorViewModel.allAutores.observe(this, Observer { autores ->
            autores?.let { adapter.setAutores(it) }
        })

        /********************************  Editorial  ******************************************/
        editorialViewModel = ViewModelProviders.of(this).get(EditorialViewModel::class.java)

        editorialViewModel.allEditoriales.observe(this, Observer { editoriales ->
            editoriales?.let { adapter.setEditoriales(it) }
        })

        /********************************  Editorial  ******************************************/
        tagViewModel = ViewModelProviders.of(this).get(TagViewModel::class.java)

        tagViewModel.allTags.observe(this, Observer { tags ->
            tags?.let { adapter.setTags(it) }
        })

        /********************************  Libro  ******************************************/
        libroViewModel = ViewModelProviders.of(this).get(LibroViewModel::class.java)

        libroViewModel.allLibros.observe(this, Observer { libros ->
            libros?.let { adapter.setLibros(it) }
        })

        /********************************  Boton  ******************************************/
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, new_libro::class.java)
            startActivityForResult(intent, newLibroActivityRequestCode)
        }

        /*val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    /******************************************************************************************************************/
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newLibroActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val autor = Autor(data.getStringExtra(new_libro.EXTRA_REPLY_A))
                autorViewModel.insert(autor)

                val editorial = Editorial(data.getStringExtra(new_libro.EXTRA_REPLY_E))
                editorialViewModel.insert(editorial)

                val tag = Tag(data.getStringExtra(new_libro.EXTRA_REPLY_T))
                tagViewModel.insert(tag)

                val libro = Libro(data.getStringExtra(new_libro.EXTRA_REPLY_L), autor.autor, editorial.nombre_editorial, tag.tag)
                libroViewModel.insert(libro)

            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    /******************************************************************************************************************/

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
