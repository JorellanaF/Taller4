package com.example.taller4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class new_libro : AppCompatActivity() {

    private lateinit var editTituloView: EditText
    private lateinit var editAutorView: EditText
    private lateinit var editEditorialView: EditText
    private lateinit var editTagView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_libro)

        editTituloView = findViewById(R.id.et_titulo)
        editAutorView = findViewById(R.id.et_autor)
        editEditorialView = findViewById(R.id.et_editorial)
        editTagView = findViewById(R.id.et_tag)

        val button = findViewById<Button>(R.id.btn_agregar)
        button.setOnClickListener {

            val replyIntentL = Intent()
            /*val replyIntentA = Intent()
            val replyIntentE = Intent()
            val replyIntentT = Intent()*/

            if (TextUtils.isEmpty(editTituloView.text) && TextUtils.isEmpty(editAutorView.text)
                && TextUtils.isEmpty(editEditorialView.text) && TextUtils.isEmpty(editTagView.text)
            ) {

                setResult(Activity.RESULT_CANCELED, replyIntentL)

            } else {
                val titulo = editTituloView.text.toString()
                val autor = editAutorView.text.toString()
                val editorial = editEditorialView.text.toString()
                val tag = editTagView.text.toString()
                replyIntentL.putExtra(EXTRA_REPLY_A, autor)
                replyIntentL.putExtra(EXTRA_REPLY_E, editorial)
                replyIntentL.putExtra(EXTRA_REPLY_T, tag)
                replyIntentL.putExtra(EXTRA_REPLY_L, titulo)
                setResult(Activity.RESULT_OK, replyIntentL)
            }
            finish()
        }

    }

    companion object {
        const val EXTRA_REPLY_L = "com.example.android.libroListsql.REPLY"
        const val EXTRA_REPLY_A = "com.example.android.autorListsql.REPLY"
        const val EXTRA_REPLY_E = "com.example.android.editorialListsql.REPLY"
        const val EXTRA_REPLY_T = "com.example.android.tagListsql.REPLY"
    }

}
