package com.example.android.notesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.example.android.notesapplication.databinding.ActivityEditTaskBinding

class EditTaskActivity : AppCompatActivity() {
    private lateinit var button: ImageButton
    private lateinit var editText: EditText
    private lateinit var binding: ActivityEditTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        button = binding.submitEditButton
        editText = binding.editText
        val userInput = intent.getStringExtra(INTENT_DATA_NAME)
        editText.setText(userInput)

        button.setOnClickListener {
            val newEditText = editText.text.toString()
            val intent = Intent()

            intent.putExtra(INTENT_DATA_NAME, newEditText)
            editText.text.clear()

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}