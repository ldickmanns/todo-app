package com.example.todo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getAll(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getById(id: Int): Todo

    @Query("SELECT COUNT(*) FROM todo")
    fun count(): Int

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(todo: Todo): Int

    @Delete
    fun delete(todo: Todo): Int
}
