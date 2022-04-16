package com.medgini.roommaster.repository

import androidx.lifecycle.LiveData
import com.medgini.roommaster.db.TodoDao
import com.medgini.roommaster.model.Todos

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: LiveData<List<Todos>> = todoDao.getAllTodos()

    suspend fun addTodos(todos: Todos) {
        todoDao.addTodos(todos)
    }

    suspend fun deleteTodos(todos: Todos) {
        todoDao.deleteTodo(todos)
    }

    suspend fun updateTodos(todos: Todos) {
        todoDao.updateTodo(todos)
    }

    suspend fun deleteAllTodos() {
        todoDao.deleteAllTodos()
    }

}