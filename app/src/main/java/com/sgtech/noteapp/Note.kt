package com.sgtech.noteapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0, var title: String, var content: String
)