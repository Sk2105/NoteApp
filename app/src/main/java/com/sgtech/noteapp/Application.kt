package com.sgtech.noteapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }

    companion object {
        lateinit var database:  NoteDatabase
    }

    private fun init() {
        database = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "NoteDatabase"
        ).build()

    }
}