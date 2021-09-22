package com.example.android.notesapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.notesapplication.adapter.INTENT_DATA_NAME
import com.example.android.notesapplication.adapter.TaskViewAdapter
import com.example.android.notesapplication.databinding.ActivityMainBinding
import com.example.android.notesapplication.model.TaskViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        val adapter = TaskViewAdapter(this, viewModel.taskList)
        val userInput = binding.mainEditText
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.submitButton.setOnClickListener {
            val taskUserInput = userInput.text
            // Get the new task entered
            val newTask = taskUserInput.toString()
            // Clear the EditText field
            taskUserInput.clear()
            //add string task to taskList in [ViewModel]
            viewModel.taskList.add(newTask)
            adapter.notifyItemChanged(viewModel.taskList.size - 1)
        }

    }
}