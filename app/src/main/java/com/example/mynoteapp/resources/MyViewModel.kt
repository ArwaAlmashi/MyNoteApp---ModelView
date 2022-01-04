package com.example.mynoteapp.resources

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mynoteapp.database.NoteDatabase
import com.example.mynoteapp.model.NoteModel
import com.example.mynoteapp.model.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application): AndroidViewModel(application) {


    private val repository: NoteRepository
    private val allNotes: LiveData<List<NoteModel>>

    init {
        val noteDao = NoteDatabase.getInstance(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.getNotes
    }

    fun getNotes(): LiveData<List<NoteModel>>{
        return allNotes
    }


    fun addNote(note: NoteModel){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addNote(note)
        }
    }

    fun editNote(note: NoteModel){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: NoteModel){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteNote(note)
        }
    }


}