package com.example.finalprojectefe

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TaskAdapter
    private val taskList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val editText = findViewById<EditText>(R.id.editTextTask)
        val addButton = findViewById<Button>(R.id.buttonAdd)

        adapter = TaskAdapter(taskList,
            onToggle = { index ->
                taskList[index].isDone = !taskList[index].isDone
                adapter.update()
            },
            onDelete = { index ->
                taskList.removeAt(index)
                adapter.update()
            })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            val title = editText.text.toString()
            if (title.isNotBlank()) {
                taskList.add(Task(title))
                adapter.update()
                editText.text.clear()
            }
        }
    }
}
