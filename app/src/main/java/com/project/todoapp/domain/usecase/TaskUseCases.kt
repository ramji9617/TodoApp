package com.project.todoapp.domain.usecase

import com.project.todoapp.domain.usecase.taskUseCases.DeleteTaskUseCase
import com.project.todoapp.domain.usecase.taskUseCases.GetTaskUseCase
import com.project.todoapp.domain.usecase.taskUseCases.InsertTaskUseCase
import com.project.todoapp.domain.usecase.taskUseCases.MarkTaskUSeCase
import com.project.todoapp.domain.usecase.taskUseCases.UpdateTaskUseCase

data class TaskUseCases(
    val getTasks: GetTaskUseCase,
    val insertTask: InsertTaskUseCase,
    val deleteTask: DeleteTaskUseCase,
    val updateTask: UpdateTaskUseCase,
    val markTask: MarkTaskUSeCase

)