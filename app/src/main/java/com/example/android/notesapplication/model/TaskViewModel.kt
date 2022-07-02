package com.example.android.notesapplication.model

import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    //create new mutable list to store string tasks that are entered
    private var _taskList: MutableList<String> = mutableListOf()
    val taskList: MutableList<String>
        get() = _taskList

    private var _inputString: String = ""
    val inputString : String
    get() = _inputString

    fun addEditTask(editInput: String){
        for(index in 0 until taskList.size)
            taskList[index] = editInput
    }

    fun removeTask(index: Int){
        taskList.removeAt(index)
    }
}