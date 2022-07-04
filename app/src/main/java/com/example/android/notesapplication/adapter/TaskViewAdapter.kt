package com.example.android.notesapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.notesapplication.databinding.FragmentTaskBinding
import com.example.android.notesapplication.databinding.ListItemBinding


class TaskViewAdapter(val listener: Listener, private val context: Context, private val dataset: MutableList<String?>) :
    RecyclerView.Adapter<TaskViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class ViewHolder(binding: FragmentTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        val textView = binding.tasksView

        init {
            // Define click listener for the ViewHolder's View.
            textView.setOnClickListener {
                listener.onTaskClicked(adapterPosition)
            }

            textView.setOnLongClickListener {
                listener.onLongTaskClicked(context, adapterPosition)
                true
            }
        }
    }
/*
    fun setTasks(listOfTask:MutableList<String>){
        this.dataset = listOfTask
        notifyDataSetChanged()
    }*/

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = FragmentTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = dataset[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size

    interface Listener{
        fun onTaskClicked(index: Int)
        fun onLongTaskClicked(context: Context, index: Int)
    }
}