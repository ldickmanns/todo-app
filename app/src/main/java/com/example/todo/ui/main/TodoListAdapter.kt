package com.example.todo.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.database.Todo

class TodoListAdapter(
    private val onCheckBoxClickListener: (Todo) -> Unit
) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    private val todos = mutableListOf<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cardView = layoutInflater.inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.bind(todo)
    }

    override fun getItemCount(): Int = todos.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(newTodos: List<Todo>) {
        todos.clear()
        todos.addAll(newTodos)
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(todo: Todo) {
            view.findViewById<TextView>(R.id.todo_text).text = todo.todoText
            view.findViewById<CheckBox>(R.id.check_box).apply {
                isChecked = todo.isDone
                setOnClickListener {
                    val updatedTodo = todo.copy(isDone = todo.isDone.not())
                    onCheckBoxClickListener(updatedTodo)
                }
            }
        }
    }
}