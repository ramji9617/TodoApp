package com.project.todoapp.presentation.task.ui_helper

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import com.project.todoapp.domain.model.TaskEntity


@Composable
fun ListStructure(
    task: TaskEntity,
    onCheckedChange: (Boolean) -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onItemClickable :() -> Unit
) {

  Card(
      shape = RoundedCornerShape(16.dp),
      onClick = onItemClickable,
      border = BorderStroke(
          width = 2.dp ,
          color = MaterialTheme.colorScheme.onTertiary
      ),
      elevation = CardDefaults.cardElevation(16.dp),
      modifier = Modifier.fillMaxWidth()
          .background(
              color = MaterialTheme.colorScheme.surfaceContainer
          )


  ) {
      Row(
          modifier = Modifier
              .fillMaxWidth()
              .padding(10.dp),
          verticalAlignment = Alignment.CenterVertically,


      ) {

          Checkbox(
              checked = task.isCompleted,
              colors = CheckboxDefaults.colors(
                  checkedColor = Color.Green,
                  uncheckedColor = Color.DarkGray,
                  checkmarkColor = Color.White
              ),
              onCheckedChange = {isChecked->
                  onCheckedChange(isChecked)
              },
              
          )

          Text(
              text = task.title,
              style = MaterialTheme.typography.titleMedium,
              modifier = Modifier.weight(1f)
          )

          IconButton(
              onClick =onEditClick
          ) {
              Icon(
                  imageVector = Icons.Rounded.Edit,
                  contentDescription = "Edit Icon"
              )
          }

          IconButton(
              onClick =onDeleteClick
          ) {
              Icon(
                  imageVector = Icons.Rounded.Delete,
                  contentDescription = "Delete Icon"
              )
          }






      }
  }


}