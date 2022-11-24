package com.example.todo.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.todo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false).apply {
        val button: FloatingActionButton = findViewById(R.id.add_button)
        val editText: EditText = findViewById(R.id.edit_text)
        val inputMethodManager = getInputMethodManager()
        button.setOnClickListener { inputMethodManager.showSoftInput(editText, 0) }
    }

    private fun getInputMethodManager() = requireContext().getSystemService(
        Context.INPUT_METHOD_SERVICE
    ) as InputMethodManager
}