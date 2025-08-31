package com.project.todoapp.domain.repository

import com.project.todoapp.domain.model.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<TaskEntity>>
    suspend fun insertTask(task: TaskEntity)
    suspend fun deleteTask(taskId: Int)
    suspend fun updateTask(task: TaskEntity)
    suspend fun markTaskAsCompleted(taskId: Int)
    suspend fun markTaskAsIncomplete(taskId: Int)

}