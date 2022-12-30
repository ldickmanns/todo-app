package com.example.todo.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoDaoTests {

    private lateinit var database: AppDatabase
    private lateinit var todoDao: TodoDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).allowMainThreadQueries().build()

        todoDao = database.todoDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun `sample test`() {
        // given
        val todo = Todo(todoText = "Test todo")

        // when
        todoDao.insert(todo)

        // then
        assertEquals(1, todoDao.count())
    }
}