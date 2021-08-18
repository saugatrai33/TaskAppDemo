package com.example.taskappdemo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    var name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
