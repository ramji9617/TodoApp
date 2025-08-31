package com.project.todoapp.domain.usecase.taskUseCases

import com.project.todoapp.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){
    operator fun invoke() = taskRepository.getTasks()
}