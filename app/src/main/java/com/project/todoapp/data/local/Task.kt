package com.project.todoapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.todoapp.domain.model.TaskEntity

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false
)

fun Task.toTaskEntity(): TaskEntity = TaskEntity(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted

)