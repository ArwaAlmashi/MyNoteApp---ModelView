package com.example.mynoteapp.model

import androidx.lifecycle.LiveData
import com.example.mynoteapp.database.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val getNotes: LiveData<List<NoteModel>> = noteDao.getAllNotes()

    fun addNote(note: NoteModel){
        noteDao.insertNote(note)
    }

    fun updateNote(note: NoteModel){
        noteDao.updateNote(note)
    }

    fun deleteNote(note: NoteModel){
        noteDao.deleteNote(note)
    }

}