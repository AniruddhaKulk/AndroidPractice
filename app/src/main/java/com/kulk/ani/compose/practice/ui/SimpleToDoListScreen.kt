package com.kulk.ani.compose.practice.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SimpleToDoListScreen(
    modifier: Modifier,
    toDo: ToDo,
    onCheckedListener: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = toDo.title,
                fontWeight = FontWeight.Bold,
                textDecoration = if (toDo.isChecked) {
                    TextDecoration.LineThrough
                } else {
                    TextDecoration.None
                }
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = toDo.description,
                textDecoration = if (toDo.isChecked) {
                    TextDecoration.LineThrough
                } else {
                    TextDecoration.None
                }
            )
        }
        Checkbox(
            checked = toDo.isChecked,
            onCheckedChange = onCheckedListener
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun TodoItemPreview() {
    SimpleToDoListScreen(
        modifier = Modifier,
        toDo = ToDo(
            title = "Test title",
            description = "This is a todo description",
            isChecked = false
        ),
        onCheckedListener = {}
    )
}

data class ToDo(val title: String, val description: String, val isChecked: Boolean)

