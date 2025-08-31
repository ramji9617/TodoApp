package com.project.todoapp.presentation.task.ui_helper

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.project.todoapp.R
import com.project.todoapp.domain.model.TaskEntity


@Composable
fun TaskDetailsDialog(
    forUpdate: Boolean = false,
    selectedTask: TaskEntity?,
    onDismiss: () -> Unit
) {
    if (selectedTask != null && !forUpdate) { // only show when task is selected
        AlertDialog(
            onDismissRequest = onDismiss, // dismiss on outside tap or back press
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_description_alert),
                    contentDescription = "Hero Icon"
                )
            },
            title = {
                Text(selectedTask.title)
            },
            text = {
                Text(selectedTask.description.toString())
            },
            confirmButton = {
                TextButton(onClick = onDismiss) { // dismiss on OK button
                    Text("Ok")
                }
            },
            dismissButton = {}
        )
    }
}
