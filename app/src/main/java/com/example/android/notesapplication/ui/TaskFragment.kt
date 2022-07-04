package com.example.android.notesapplication.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.notesapplication.R
import com.example.android.notesapplication.adapter.TaskViewAdapter
import com.example.android.notesapplication.databinding.ListItemBinding
import com.example.android.notesapplication.model.TaskViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TaskFragment : Fragment(), TaskViewAdapter.Listener {

    private val viewModel: TaskViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskViewAdapter
    private var _binding: ListItemBinding? = null
    private val binding get() = _binding!!
    private val args: TaskFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tasksList = mutableListOf(args.newTask)
        adapter = TaskViewAdapter(this, this.requireActivity(), tasksList)
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.fab.setOnClickListener{
            val action = TaskFragmentDirections.actionTaskFragmentToAddTaskFragment()
            findNavController().navigate(action)
        }
    }

    override fun onTaskClicked(index: Int) {
        val taskEntered = viewModel.taskList[index]
        Log.d("TaskFrag", taskEntered)
    }

    override fun onLongTaskClicked(context: Context, index: Int) {
        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.title)
            .setMessage(R.string.affirmation)
            .setCancelable(false)
            .setNegativeButton(R.string.decline) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.accept) { _, _ ->
                viewModel.taskList.removeAt(index)
                adapter.notifyItemRemoved(index)
            }
            .show()
    }
}