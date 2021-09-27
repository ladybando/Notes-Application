package com.example.android.notesapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.notesapplication.R
import com.example.android.notesapplication.databinding.ListItemBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class TaskViewAdapter(val listener: Listener, private val context: Context) :
    RecyclerView.Adapter<TaskViewAdapter.ViewHolder>() {
    private var dataset = mutableListOf<String>()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val textView: TextView = binding.listOfItemsView

        init {
            // Define click listener for the ViewHolder's View.
            textView.setOnClickListener {
                listener.onTaskClicked(adapterPosition)
            }

            textView.setOnLongClickListener {
                listener.onLongTaskClicked(context)
                true
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = dataset[position]

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size

    fun setTasks(listOfTask:MutableList<String>){
        this.dataset = listOfTask
        notifyDataSetChanged()
    }

    interface Listener{
        fun onTaskClicked(index: Int)
        fun onLongTaskClicked(context: Context)
    }
}