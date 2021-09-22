package com.example.android.notesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.android.notesapplication.adapter.INTENT_DATA_NAME
import com.example.android.notesapplication.databinding.ActivityEditTaskBinding
import com.example.android.notesapplication.model.TaskViewModel

class EditTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditTaskBinding
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userInput = binding.editText
        val dataFromMain = intent.getStringExtra(INTENT_DATA_NAME)
        // Get the new task entered
        userInput.setText(dataFromMain)

        binding.submitEditButton.setOnClickListener {
            // set new user input from edit text to new variable
            val editedUserInput = userInput.text.toString()
            //create new intent instance
            val intent = Intent()
            //associates edited string to intent_data_name for use in [MainActivity]
            intent.putExtra(INTENT_DATA_NAME, editedUserInput)
            viewModel.editTask(editedUserInput)
            //should send edited text to [MainActivity]
            setResult(RESULT_OK, intent)
            //other attempts to update text
           // viewModel.taskList.set(0,"editedUserInput")
           /* for (index in 0 until viewModel.taskList.size) {
                //viewModel.taskList.get(index)

                //viewModel.taskList[viewModel.taskList.indexOf(editedUserInput)]
                //viewModel.taskList[index] = editedUserInput
            }*/
            //userInput.text.clear()
            finish()
        }
        binding.newSubmit.setOnClickListener {
            viewModel.taskList.remove("Wash clothes")
            finish()
        }
    }
}