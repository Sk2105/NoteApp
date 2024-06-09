package com.sgtech.noteapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
abstract class NoteDao {
    @Insert
    abstract suspend fun insert(note: Note)

    @Update
    abstract suspend fun update(note: Note)

    @Delete
    abstract suspend fun delete(note: Note)

    @Query("SELECT * FROM Notes")
    abstract suspend fun getAll(): List<Note>

    @Query("SELECT * FROM Notes WHERE id = :id")
    abstract suspend fun getById(id: Int): Note?

}