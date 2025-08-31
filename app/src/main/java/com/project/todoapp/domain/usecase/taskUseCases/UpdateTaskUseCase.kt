package com.project.todoapp.domain.usecase.taskUseCases

import com.project.todoapp.domain.model.TaskEntity
import com.project.todoapp.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){
   suspend operator fun invoke(taskEntity: TaskEntity) = taskRepository.updateTask(taskEntity)
}