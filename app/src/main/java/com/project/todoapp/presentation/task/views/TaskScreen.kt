package com.project.todoapp.presentation.task.views

import AddTaskDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.todoapp.R
import com.project.todoapp.domain.model.TaskEntity
import com.project.todoapp.presentation.task.ui_helper.ListStructure
import com.project.todoapp.presentation.task.ui_helper.LottieDialog
import com.project.todoapp.presentation.task.ui_helper.TaskDetailsDialog
import com.project.todoapp.presentation.task.viewmodels.TaskViewModel

@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel()
) {

    val tasks by viewModel.tasks.collectAsStateWithLifecycle()
    var selectedTask by remember { mutableStateOf<TaskEntity?>(null) }
    var showAddTaskDialog by remember { mutableStateOf(false) }
    var showUpdateTaskDialog by remember { mutableStateOf(false) }
    var showLottieDialog by remember { mutableStateOf(false) }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddTaskDialog = true

                },


                ) {
                Icon(Icons.Default.Add, contentDescription = "Add Icon ")
            }
        }
    )
    { innerPadding ->
        if (showAddTaskDialog) {
            AddTaskDialog(
                onDismiss = { showAddTaskDialog = false }, // dismiss fun
                onSave = { title, desc ->                   //when adding task
                    val task = TaskEntity(title = title, description = desc)
                    viewModel.addTask(task) // directly call insert
                }
            )
        }
        if (showUpdateTaskDialog && selectedTask != null && !showAddTaskDialog) {
            AddTaskDialog(
                onDismiss = {
                    selectedTask = null
                    showUpdateTaskDialog = false
                },
                onSave = { title, desc ->
                    val task = TaskEntity(
                        id = selectedTask?.id,
                        title = title,
                        description = desc,
                        isCompleted = selectedTask?.isCompleted
                            ?: false
                    )
                    viewModel.updateTask(task)

                }
            )
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 2.dp)
                    .background(color = MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tasks) { task ->
                    ListStructure(
                        task = task,
                        onCheckedChange = { isChecked ->
                            viewModel.onTaskCheckedChanged(task.id!!, isChecked)

                            if(isChecked){
                                showLottieDialog = false
                                showLottieDialog = true

                            }

                        },
                        onEditClick = {
                            selectedTask = task
                            showUpdateTaskDialog = true
                        },
                        onDeleteClick = { viewModel.deleteTask(task.id!!) },
                        onItemClickable = {

                            selectedTask = task

                        },
                    )


                }


            }


        }

    }

    LottieDialog(
        rawRes = R.raw.confetti,
        show = showLottieDialog,
        onHide = { showLottieDialog = false }
    )
    TaskDetailsDialog(
        showUpdateTaskDialog,
        selectedTask = selectedTask,
        onDismiss = { selectedTask = null }
    )


}



