package com.example.taskappdemo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.example.taskappdemo.data.local.Task
import com.example.taskappdemo.data.local.TaskDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultTaskRepository @Inject constructor(
    private val taskDao: TaskDao,
    private val ioDispatcher: CoroutineDispatcher
) : TaskRepository {

    override suspend fun insertTask(task: Task): Long {
        return taskDao.insertTask(task)
    }

    override suspend fun getAllTask(): Flow<List<Task>> {
        return taskDao.getAllTask().asFlow()
            .flowOn(ioDispatcher)
    }
}