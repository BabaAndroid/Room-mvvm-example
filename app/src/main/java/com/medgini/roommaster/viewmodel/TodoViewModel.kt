package com.medgini.roommaster.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.medgini.roommaster.db.AppDatabase
import com.medgini.roommaster.model.Todos
import com.medgini.roommaster.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository
    private val allTodosList: LiveData<List<Todos>>

    init {
        val todoDao = AppDatabase.getAppDatabase(application).getTodoDao()
        repository = TodoRepository(todoDao)
        allTodosList = repository.allTodos
    }

    //get all todos
    fun getAllTodos(): LiveData<List<Todos>> {
        return allTodosList
    }

    //insert todos
    fun insertTodoToDb(todos: Todos) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodos(todos)
        }
    }

    //delete todos
    fun deleteTodos(todos: Todos) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodos(todos)
        }
    }

    //update todos
    fun updateTodos(todos: Todos) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodos(todos)
        }
    }

    //delete all todos
    fun deleteAllTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTodos()
        }
    }

}