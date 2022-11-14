package com.example.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Todo(
    @ColumnInfo(name = "todo_text")
    val todoText: String,

    val description: String? = null,

    @ColumnInfo(name = "is_done")
    val isDone: Boolean = false,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "last_edited")
    var lastEdited: Date = Date()
}
