package com.project.todoapp.domain.usecase.taskUseCases

import com.project.todoapp.domain.repository.TaskRepository
import javax.inject.Inject

class MarkTaskUSeCase @Inject constructor(
    private val taskRepository: TaskRepository
){
    suspend fun taskCompleted(taskId:Int) = taskRepository.markTaskAsCompleted(taskId)

    suspend fun taskInCompleted(taskId:Int) = taskRepository.markTaskAsIncomplete(taskId)
}