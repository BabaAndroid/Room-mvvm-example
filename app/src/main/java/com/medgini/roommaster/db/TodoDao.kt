package com.medgini.roommaster.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.medgini.roommaster.model.Todos

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodos(todos: Todos)

    @Update
    suspend fun updateTodo(todos: Todos)

    @Delete
    suspend fun deleteTodo(todos: Todos)

    @Query("SELECT * FROM todo_table order by id desc")
    fun getAllTodos(): LiveData<List<Todos>>

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTodos()

}