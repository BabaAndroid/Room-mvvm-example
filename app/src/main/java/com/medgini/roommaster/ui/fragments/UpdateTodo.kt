package com.medgini.roommaster.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.medgini.roommaster.R
import com.medgini.roommaster.databinding.FragmentUpdateTodoBinding
import com.medgini.roommaster.model.Todos
import com.medgini.roommaster.viewmodel.TodoViewModel


class UpdateTodo : Fragment() {

    lateinit var binding: FragmentUpdateTodoBinding
    lateinit var viewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_todo, container, false)
        binding.toolbar.title = "Update Todo"

        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        val todos = arguments?.get("editTodoData") as Todos

        binding.apply {
            todoTitle.setText(todos.title)
            todoDescp.setText(todos.description)
        }

        binding.updateTodoBtn.setOnClickListener {
            val title = binding.todoTitle.text.toString()
            val description = binding.todoDescp.text.toString()
            if (TextUtils.isEmpty(title)) {
                binding.todoTitle.error = "Required field"
                binding.todoTitle.requestFocus()
            } else if (TextUtils.isEmpty(description)) {
                binding.todoDescp.error = "Required field"
                binding.todoDescp.requestFocus()
            } else {
                val updatedTodo = Todos(todos.id, title, description)
                viewModel.updateTodos(updatedTodo)
                findNavController().navigateUp()
            }
        }

        return binding.root
    }

}