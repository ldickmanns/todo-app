package com.example.todo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.database.AppDatabase
import com.example.todo.database.Todo
import com.example.todo.database.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var todoDao: TodoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val database = AppDatabase.getInstance(requireContext())
        todoDao = database.todoDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false).also {
        setupRecyclerView(it)
        setupSaveButton(it)
    }

    private fun setupRecyclerView(rootView: View) = rootView.apply {
        val todoListAdapter = TodoListAdapter {
            lifecycleScope.launch(Dispatchers.IO) { todoDao.update(it) }
        }
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = todoListAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        val todosLiveData = todoDao.getAll().asLiveData()
        todosLiveData.observe(viewLifecycleOwner) { todos: List<Todo> ->
            todoListAdapter.update(todos)
        }

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                println("MOVED!")
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                println("SWIPED!")
            }
        }
        ItemTouchHelper(callback).attachToRecyclerView(recyclerView)
    }

    private fun setupSaveButton(rootView: View) = rootView.apply {
        val button: Button = findViewById(R.id.add_button)
        val editText: EditText = findViewById(R.id.edit_text)
        button.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                val todo = Todo(todoText = text)
                lifecycleScope.launch(Dispatchers.IO) { todoDao.insert(todo) }
                editText.text.clear()
            }
        }
    }
}