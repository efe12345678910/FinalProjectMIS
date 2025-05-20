package com.example.finalprojectefe

import TaskViewModel
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val editText = findViewById<EditText>(R.id.editTextTask)
        val addButton = findViewById<Button>(R.id.buttonAdd)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        adapter = TaskAdapter(mutableListOf(),
            onToggle = { index ->
                val task = adapter.getTask(index)
                viewModel.update(task.copy(isDone = !task.isDone))
            },
            onDelete = { index ->
                viewModel.delete(adapter.getTask(index))
            })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.allTasks.observe(this) { tasks ->
            adapter.setTasks(tasks.toMutableList())
        }

        addButton.setOnClickListener {
            val title = editText.text.toString()
            if (title.isNotBlank()) {
                viewModel.insert(Task(title = title))
                editText.text.clear()
            }
        }
    }
}

