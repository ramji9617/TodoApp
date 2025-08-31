package com.project.todoapp.domain.model

import androidx.room.PrimaryKey
import com.project.todoapp.data.local.Task

data class TaskEntity(
    val id: Int? = null,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false
)


fun TaskEntity.toTask() :Task = Task(
    id = id ?: 0,
    title = title,
    description = description,
    isCompleted = isCompleted
)
