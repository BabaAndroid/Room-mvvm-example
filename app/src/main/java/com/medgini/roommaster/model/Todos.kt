package com.medgini.roommaster.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo_table")
data class Todos(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String
) : Serializable
