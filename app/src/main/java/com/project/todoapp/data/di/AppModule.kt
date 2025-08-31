package com.project.todoapp.data.di

import android.content.Context
import androidx.room.Room
import com.project.todoapp.data.local.TaskDatabase
import com.project.todoapp.data.repository.TaskRepositoryImpl
import com.project.todoapp.domain.usecase.TaskUseCases
import com.project.todoapp.domain.repository.TaskRepository
import com.project.todoapp.domain.usecase.taskUseCases.DeleteTaskUseCase
import com.project.todoapp.domain.usecase.taskUseCases.GetTaskUseCase
import com.project.todoapp.domain.usecase.taskUseCases.InsertTaskUseCase
import com.project.todoapp.domain.usecase.taskUseCases.MarkTaskUSeCase
import com.project.todoapp.domain.usecase.taskUseCases.UpdateTaskUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTaskRepository(
        impl: TaskRepositoryImpl
    ): TaskRepository
}


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext app: Context
    ): TaskDatabase =
        Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            "task_db"
        ).build()


    @Provides
    @Singleton
    fun provideTaskDao(db: TaskDatabase) = db.taskDao()


    @Provides
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasks = GetTaskUseCase(repository),
            insertTask = InsertTaskUseCase(repository),
            deleteTask = DeleteTaskUseCase(repository),
            updateTask = UpdateTaskUseCase(repository),
            markTask = MarkTaskUSeCase(repository),

            )
    }
}