package com.example.taskappdemo.data

import androidx.lifecycle.LiveData
import com.example.taskappdemo.data.local.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insertTask(task: Task) : Long
    suspend fun getAllTask(): Flow<List<Task>>
}