package com.example.android.notesapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.notesapplication.adapter.TaskViewAdapter
import com.example.android.notesapplication.databinding.ActivityMainBinding
import com.example.android.notesapplication.model.TaskViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

const val INTENT_DATA_NAME = "data"
const val INDEX = "TaskIndex"

class MainActivity : AppCompatActivity(), TaskViewAdapter.Listener {
    private val viewModel: TaskViewModel by viewModels()

    private lateinit var button: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var adapter: TaskViewAdapter
    private var position = -1
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
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

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
        position = index
        resultLauncher.launch(intent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    val newUserInput = data.getStringExtra(INTENT_DATA_NAME)
                    if (newUserInput != null) {
                        viewModel.taskList[position] = newUserInput
                        adapter.notifyItemChanged(viewModel.taskList.indexOf(newUserInput))
                    }
                }
            }
        }

    override fun onLongTaskClicked(context: Context) {
        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.title)
            .setMessage(R.string.affirmation)
            .setCancelable(false)
            .setNegativeButton(R.string.decline) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.accept) { _, _ ->
                viewModel.taskList.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
            .show()
    }
}