package com.project.todoapp.domain.usecase.taskUseCases

import com.project.todoapp.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){
    suspend operator fun invoke(taskId: Int) {
        taskRepository.deleteTask(taskId)
    }

}