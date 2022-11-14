package com.example.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "todo_text")
    val todoText: String,

    val description: String? = null,

    @ColumnInfo(name = "is_done")
    val isDone: Boolean = false,
) {
    @ColumnInfo(name = "last_edited")
    val lastEdited: Date = Date()
}
