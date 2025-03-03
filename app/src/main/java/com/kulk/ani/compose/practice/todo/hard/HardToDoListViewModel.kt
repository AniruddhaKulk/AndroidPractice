package com.kulk.ani.compose.practice.todo.hard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HardToDoListViewModel : ViewModel() {

    private var newTitle: String = ""
    private var newDescription: String = ""

    private val _state = MutableStateFlow(TodoList())
    val state = _state.asStateFlow()

    fun onAction(action: TodoListAction) {
        when (action) {
            TodoListAction.OnAddClick -> {
                if (newTitle.isNotBlank()) {
                    val todo = Todo(newTitle, newDescription, false)
                    _state.update {
                        it.copy(
                            todos = it.todos + todo
                        )
                    }
                    newTitle = ""
                    newDescription = ""
                }
            }

            is TodoListAction.OnDeleteClick -> {
                _state.update {
                    it.copy(todos = it.todos.filter { item -> item != action.todo })
                }
            }

            is TodoListAction.OnTodoCompleteToggle -> {
                _state.update {
                    it.copy(
                        todos = it.todos.map { item ->
                            if (item == action.todo) {
                                item.copy(isChecked = !item.isChecked)
                            } else {
                                item
                            }
                        }
                    )
                }
            }

            is TodoListAction.OnDescriptionUpdate -> newDescription = action.description
            is TodoListAction.OnTitleUpdate -> newTitle = action.title
        }
    }

}

sealed interface TodoListAction {
    data object OnAddClick : TodoListAction
    class OnDeleteClick(val todo: Todo) : TodoListAction
    class OnTitleUpdate(val title: String) : TodoListAction
    class OnDescriptionUpdate(val description: String) : TodoListAction
    class OnTodoCompleteToggle(val todo: Todo) : TodoListAction
}

data class Todo(val title: String, val description: String, val isChecked: Boolean)

data class TodoList(
    val todos: List<Todo> = (1..10).map {
        Todo(
            title = "Title $it", description = "Description $it", isChecked = false
        )
    }
)