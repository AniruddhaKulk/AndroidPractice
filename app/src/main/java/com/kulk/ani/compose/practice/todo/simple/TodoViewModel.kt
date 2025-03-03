package com.kulk.ani.compose.practice.todo.simple

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodoViewModel: ViewModel() {

    private val _viewState = MutableStateFlow(ToDo("Some title", "Some description", false))
    val viewState = _viewState.asStateFlow()

    fun updateSelection(isChecked: Boolean) {
        _viewState.update { it.copy(isChecked = isChecked) }
    }
}

data class ToDo(val title: String, val description: String, val isChecked: Boolean)