package com.example.taskappdemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskappdemo.data.local.Task
import com.example.taskappdemo.ui.TaskAdapter
import com.example.taskappdemo.ui.TaskViewModel
import com.example.taskappdemo.ui.addaddress.AddTaskActivity
import com.example.taskappdemo.utils.ToastUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var taskViewModel: TaskViewModel

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: FloatingActionButton = findViewById(R.id.fabAdd)

        val taskList: RecyclerView = findViewById(R.id.taskList)
        taskList.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL, false
        )
        taskList.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        taskAdapter = TaskAdapter()
        taskList.adapter = taskAdapter

        fab.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            resultLauncher.launch(intent)
        }

        taskViewModel.getAllTask()
        taskViewModel.allTasks.observe(this) { tasks ->
            taskAdapter.addAllTasks(tasks)
        }

    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val newTask = data!!.extras!!.getString(AddTaskActivity.KEY_TASK, "")
                if (!TextUtils.isEmpty(newTask)) {
                    val task = Task(newTask)
                    taskViewModel.insertTask(task)
                    taskAdapter.addTask(task)
                }
            }
        }

}