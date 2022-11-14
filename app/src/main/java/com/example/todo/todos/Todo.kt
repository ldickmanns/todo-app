package com.example.todo.todos

import java.util.Date

data class Todo(
    val todoText: String,
    val description: String? = null,
    val isDone: Boolean = false,
) {
    val lastEdited: Date = Date()
}
