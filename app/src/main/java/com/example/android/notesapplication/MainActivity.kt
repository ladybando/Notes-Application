package com.example.android.notesapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.notesapplication.adapter.TaskViewAdapter
import com.example.android.notesapplication.databinding.ActivityMainBinding
import com.example.android.notesapplication.model.TaskViewModel

const val INTENT_DATA_NAME = "data"
const val INDEX = "TaskIndex"

class MainActivity : AppCompatActivity(), TaskViewAdapter.Listener {
    private val viewModel : TaskViewModel by viewModels()

    private lateinit var button: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var adapter: TaskViewAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TaskViewAdapter(this, this)
        button = binding.submitButton
        recyclerView = binding.recyclerView
        editText = binding.mainEditText
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        button.setOnClickListener {
            viewModel.taskList.add(editText.text.toString())
            adapter.setTasks(viewModel.taskList)
            editText.setText("")
        }
    }

    override fun onTaskClicked(index: Int) {
        val intent = Intent(this, EditTaskActivity::class.java)
        intent.putExtra(INDEX, index)
        intent.putExtra(INTENT_DATA_NAME, viewModel.taskList[index])
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            data?.let {
                val index = it.getIntExtra(INDEX, 0)
                viewModel.taskList.removeAt(index)
                viewModel.taskList.add(index,it.getStringExtra(INTENT_DATA_NAME)!!)
                adapter.setTasks(viewModel.taskList)
            }
        }
    }

}