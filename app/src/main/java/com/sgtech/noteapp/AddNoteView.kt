package com.sgtech.noteapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

class AddNoteView(private val viewModel: NoteViewModel) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        NoteView(navigator, viewModel)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteView(navigator: Navigator, viewModel: NoteViewModel) {
    var noteName by remember {
        mutableStateOf("")
    }
    val noteDescription = remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Add Note")
            },
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.pop()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = noteName, onValueChange = { value ->
                noteName = value
            }, label = {
                Text(text = "Note Name")
            }, maxLines = 1)

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = noteDescription.value, onValueChange = { value ->
                noteDescription.value = value
            }, label = {
                Text(text = "Note Description")
            }, maxLines = 3)

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                viewModel.addNote(Note(title = noteName, content = noteDescription.value))
                navigator.pop()
            }) {
                Text(text = "Add Note")
            }
        }
    }
}