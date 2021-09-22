package com.example.android.notesapplication.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.notesapplication.EditTaskActivity
import com.example.android.notesapplication.R
import com.example.android.notesapplication.databinding.ListItemBinding
import com.example.android.notesapplication.model.TaskViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

const val INTENT_DATA_NAME = "data"

class TaskViewAdapter(private val context: Context, private val dataset: MutableList<String>, ) : RecyclerView.Adapter<TaskViewAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        with(holder) {
            //gets position of text in RecyclerView List
            binding.listOfItemsView.text = dataset[position]

            binding.listOfItemsView.setOnClickListener {
                //creates intent passing data to [EditTask]
                val intent = Intent(context, EditTaskActivity::class.java)
                intent.putExtra(INTENT_DATA_NAME, dataset[position])
                context.startActivity(intent)
            }
            binding.listOfItemsView.setOnLongClickListener {
                //creates MaterialAlertDialog to verify if user wants to delete task
                MaterialAlertDialogBuilder(context)
                    .setTitle(R.string.title)
                    .setMessage(R.string.affirmation)
                    .setCancelable(false)
                    .setNegativeButton(R.string.decline) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(R.string.accept) { _, _ ->
                        dataset.removeAt(position)
                        notifyItemRemoved(position)
                    }
                    .show()
                true
            }
        }
    }

    override fun getItemCount(): Int = dataset.size

    class TaskViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}