package com.project.todoapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<Task>>

    @Insert
   suspend fun insertTask(task: Task)

    @Query("DELETE FROM tasks WHERE id = :taskId")
    fun deleteTask(taskId: Int)

    @Update
  suspend  fun updateTask(task: Task)

    @Query("UPDATE tasks SET isCompleted = 1 WHERE id = :taskId")
    suspend fun markTaskAsCompleted(taskId: Int): Int

    @Query("UPDATE tasks SET isCompleted = 0 WHERE id = :taskId")
    suspend fun markTaskAsIncomplete(taskId: Int): Int

}