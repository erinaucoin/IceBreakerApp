package com.erinaucoin.IceBreaker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewQuestionActivity : AppCompatActivity() {

    private lateinit var editQuestionView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_question)
        editQuestionView = findViewById(R.id.edit_question)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener{
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editQuestionView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val question = editQuestionView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, question)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object{
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}