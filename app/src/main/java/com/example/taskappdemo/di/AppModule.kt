package com.example.taskappdemo.di

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.taskappdemo.data.DefaultTaskRepository
import com.example.taskappdemo.data.TaskRepository
import com.example.taskappdemo.data.local.TaskDao
import com.example.taskappdemo.data.local.TaskDatabase
import com.example.taskappdemo.other.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTaskDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, TaskDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideTaskDao(
        taskDatabase: TaskDatabase
    ) = taskDatabase.taskDao()

    @Provides
    @Singleton
    fun provideDefaultTaskRepository(
        taskDao: TaskDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): TaskRepository {
        return DefaultTaskRepository(taskDao, ioDispatcher)
    }

}