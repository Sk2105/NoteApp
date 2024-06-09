package com.sgtech.noteapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel() : ViewModel() {
    private val _noteList = mutableStateListOf<Note>()
    val noteList = _noteList
    private val db: NoteDatabase = Application.database
    private val noteDao = db.noteDao()

    init {
        getNotes()
    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.insert(note)
        }
        _noteList.add(note)
    }

    private fun getNotes() {
        viewModelScope.launch(Dispatchers.Main) {
            _noteList.clear()
            _noteList.addAll(noteDao.getAll())
        }

    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.delete(note)
        }
        _noteList.remove(note)

    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.update(note)
        }
        _noteList.map {
            if(it.id == note.id){
                it.title = note.title
                it.content= note.content
            }
        }

    }


}