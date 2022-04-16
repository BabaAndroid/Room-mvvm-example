package com.medgini.roommaster.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medgini.roommaster.R
import com.medgini.roommaster.adapter.SwipeGesture
import com.medgini.roommaster.adapter.TodoAdapter
import com.medgini.roommaster.databinding.FragmentTodoListBinding
import com.medgini.roommaster.viewmodel.TodoViewModel

class TodoList : Fragment() {

    private lateinit var binding: FragmentTodoListBinding
    private lateinit var viewModel: TodoViewModel
    private lateinit var layoutmanager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false)
        binding.toolbar.title = "All Todos"

        //init ViewModel
        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        layoutmanager = LinearLayoutManager(context)
        val todoAdapter = TodoAdapter()

        //init adapter and recycler view
        binding.todoList.layoutManager = layoutmanager
        binding.todoList.adapter = todoAdapter

        //adding swipe gestures in todos list
        val swipeGesture = object : SwipeGesture(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteTodos(todoAdapter.getTodoAtPosition(viewHolder.adapterPosition))
                super.onSwiped(viewHolder, direction)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.apply {
            attachToRecyclerView(binding.todoList)
        }

        //observing todos list
        viewModel.getAllTodos().observe(viewLifecycleOwner, Observer {
            todoAdapter.setList(it)
        })
        binding.deleteAllTodo.setOnClickListener {
            viewModel.deleteAllTodos()
        }

        binding.addNewTodo.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_todoList_to_addTodo)
        }

        return binding.root
    }

}