package com.medgini.roommaster.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.medgini.roommaster.R
import com.medgini.roommaster.databinding.FragmentAddTodoBinding
import com.medgini.roommaster.model.Todos
import com.medgini.roommaster.viewmodel.TodoViewModel

class AddTodo : Fragment() {

    lateinit var binding: FragmentAddTodoBinding
    lateinit var viewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_todo, container, false)
        binding.toolbar.title = "Add new todo"

        //init ViewModel
        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        binding.addTodoBtn.setOnClickListener {
            val title = binding.todoTitle.text.toString()
            val description = binding.todoDescp.text.toString()
            if (TextUtils.isEmpty(title)) {
                binding.todoTitle.error = "Required field"
                binding.todoTitle.requestFocus()
            } else if (TextUtils.isEmpty(description)) {
                binding.todoDescp.error = "Required field"
                binding.todoDescp.requestFocus()
            } else {
                val todos = Todos(0, title, description)
                addTodos(todos)
            }
        }

        return binding.root
    }

    private fun addTodos(todos: Todos) {
        viewModel.insertTodoToDb(todos)
        findNavController().navigateUp()
    }

}