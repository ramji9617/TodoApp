package com.project.todoapp.data.repository

import com.project.todoapp.data.local.TaskDao
import com.project.todoapp.data.local.toTaskEntity
import com.project.todoapp.domain.model.TaskEntity
import com.project.todoapp.domain.model.toTask
import com.project.todoapp.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : TaskRepository {

    override fun getTasks(): Flow<List<TaskEntity>> {

        return dao.getTasks().map {tasks ->
            tasks.map { it.toTaskEntity() }
        }

    }

    override suspend fun insertTask(task: TaskEntity) {

        dao.insertTask(task.toTask())

    }

    override suspend fun deleteTask(taskId: Int) {
        withContext(Dispatchers.IO){
            dao.deleteTask(taskId)
        }

    }

    override suspend fun updateTask(task: TaskEntity) {
        withContext(Dispatchers.IO){
            dao.updateTask(task.toTask())
        }
    }

    override suspend fun markTaskAsCompleted(taskId: Int) {
        withContext(Dispatchers.IO){
            dao.markTaskAsCompleted(taskId)
        }
    }

    override suspend fun markTaskAsIncomplete(taskId: Int) {
        withContext(Dispatchers.IO){
            dao.markTaskAsIncomplete(taskId)
        }
    }

}