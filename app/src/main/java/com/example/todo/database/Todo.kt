package com.example.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todo")
data class Todo(
    @ColumnInfo(name = "todo_text")
    val todoText: String,

    val description: String? = null,

    @ColumnInfo(name = "is_done")
    val isDone: Boolean = false,

    @ColumnInfo(name = "created_at")
    var createdAt: Date = Date()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
