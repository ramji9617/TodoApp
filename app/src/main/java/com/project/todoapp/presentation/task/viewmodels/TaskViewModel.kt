package com.project.todoapp.presentation.task.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.todoapp.domain.model.TaskEntity
import com.project.todoapp.domain.usecase.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases

) : ViewModel() {

    val tasks = taskUseCases.getTasks().map { taskList ->
        taskList.sortedWith(
            compareBy<TaskEntity> { it.isCompleted }
                .thenBy { it.id }
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun onTaskCheckedChanged(taskId: Int, isChecked: Boolean) {
        viewModelScope.launch {
            if (isChecked) {
                taskUseCases.markTask.taskCompleted(taskId)
            } else {
                taskUseCases.markTask.taskInCompleted(taskId)
            }
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            taskUseCases.deleteTask(taskId)
        }
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            taskUseCases.updateTask(task)
        }
    }

    fun addTask(task: TaskEntity) {
        viewModelScope.launch {
            taskUseCases.insertTask(task)
        }




    }

}