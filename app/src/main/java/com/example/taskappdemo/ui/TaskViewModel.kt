package com.example.taskappdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskappdemo.data.TaskRepository
import com.example.taskappdemo.data.local.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class TaskViewModel  @ViewModel constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private var _allTasks: MutableLiveData<List<Task>> = MutableLiveData()
    val allTasks: LiveData<List<Task>> = _allTasks

    private var _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private var _error: MutableLiveData<String> = MutableLiveData("")
    val error: LiveData<String> = _error

    private var _success: MutableLiveData<String> = MutableLiveData("")
    val success: LiveData<String> = _success

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }

    fun getAllTask() {
        viewModelScope.launch {
            repository.getAllTask()
                .onStart { _loading.postValue(true) }
                .catch { e ->
                    _error.postValue(e.localizedMessage)
                    _loading.postValue(false)
                }
                .collect { tasks ->
                    Timber.d(tasks.toString())
                    _allTasks.postValue(tasks)
                    _loading.postValue(false)
                }
        }
    }

}