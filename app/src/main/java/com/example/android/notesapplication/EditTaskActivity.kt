package com.example.android.notesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
        val task = intent.getStringExtra(INTENT_DATA_NAME)
        editText.setText(task)

        button.setOnClickListener {
            val intent = Intent()
            intent.putExtra(INDEX, this.intent.getIntExtra("taskIndex", 0))
            intent.putExtra(INTENT_DATA_NAME, editText.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}