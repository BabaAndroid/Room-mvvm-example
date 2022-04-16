package com.medgini.roommaster.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.medgini.roommaster.R
import com.medgini.roommaster.model.Todos

class TodoAdapter() :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todoList: List<Todos> = emptyList()

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todoTile: TextView = itemView.findViewById(R.id.todo_title)
        val todoDescription: TextView = itemView.findViewById(R.id.todo_descp)
        val editTodos: LinearLayout = itemView.findViewById(R.id.edit_todos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_layout, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.todoTile.text = todoList[position].title
        holder.todoDescription.text = todoList[position].description
        holder.editTodos.setOnClickListener {
            val bundle: Bundle = Bundle()
            bundle.putSerializable("editTodoData", todoList[position])
            Navigation.findNavController(it).navigate(R.id.action_todoList_to_updateTodo, bundle)
        }
    }

    fun getTodoAtPosition(position: Int): Todos {
        return todoList[position]
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newTodoList: List<Todos>) {
        todoList = newTodoList
        notifyDataSetChanged()
    }

}