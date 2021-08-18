package com.example.taskappdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskappdemo.R
import com.example.taskappdemo.data.local.Task
import java.util.*

class TaskAdapter(
    private var taskList: ArrayList<Task> = ArrayList()
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val task: TextView = itemView.findViewById(R.id.task)
    }

    fun addTask(task: Task) {
        this.taskList.add(task)
        notifyItemInserted(this.taskList.size - 1)
    }

    fun addAllTasks(tasks: List<Task>) {
        this.taskList.clear()
        this.taskList.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.task.text = task.name
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}