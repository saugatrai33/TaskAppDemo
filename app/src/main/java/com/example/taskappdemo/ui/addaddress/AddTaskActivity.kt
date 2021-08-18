package com.example.taskappdemo.ui.addaddress

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.taskappdemo.R
import com.example.taskappdemo.utils.ToastUtils

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
        val textTask: EditText = findViewById(R.id.textTask)
        val btnAdd: Button = findViewById(R.id.btnAddTask)
        btnAdd.setOnClickListener {
            val task = textTask.text.toString()
            if (TextUtils.isEmpty(task)) {
                ToastUtils.showToast(
                    this,
                    getString(R.string.msg_enter_task)
                )
            } else {
                val intent = Intent()
                intent.putExtra(KEY_TASK, task)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    companion object {
        const val KEY_TASK = "TASK"
    }

}