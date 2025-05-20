package com.example.finalprojectefe

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onToggle: (Int) -> Unit,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkbox: CheckBox = itemView.findViewById(R.id.taskCheck)
        val title: TextView = itemView.findViewById(R.id.taskTitle)
        val delete: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val task = tasks[position]

        holder.checkbox.setOnCheckedChangeListener(null)

        holder.checkbox.isChecked = task.isDone
        holder.title.text = task.title

        holder.checkbox.setOnCheckedChangeListener { _, _ -> onToggle(position) }

        holder.delete.setOnClickListener { onDelete(position) }
    }

    override fun getItemCount(): Int = tasks.size

    fun update() {
        Handler(Looper.getMainLooper()).post {
            notifyDataSetChanged()
        }
    }
}
