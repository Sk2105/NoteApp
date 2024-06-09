package com.sgtech.noteapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

class NoteApp(val viewModel: NoteViewModel) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        NoteContent(navigator, viewModel)
    }
}

@Composable
fun NoteContent(navigator: Navigator, viewModel: NoteViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(
                text = "Notes App",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp),
                fontSize = 24.sp
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigator.push(AddNoteView(viewModel))
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }

        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            NoteList(viewModel)
        }

    }

}

@Composable
fun NoteList(viewModel: NoteViewModel) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        verticalItemSpacing = 4.dp
    ) {
        items(viewModel.noteList.size) {
            NoteItem(note = viewModel.noteList[it])
        }

    }
}

@Composable
fun NoteItem(note: Note) {
    Column(modifier = Modifier.padding(8.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp
                )
                Text(
                    text = note.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteAppPreview() {
    Navigator(NoteApp(NoteViewModel()))
}