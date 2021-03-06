package com.example.mynoteapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynoteapp.model.NoteModel

@Dao
interface NoteDao {
    @Query( "SELECT * FROM NoteModel")
    fun getAllNotes(): LiveData<List<NoteModel>>

    @Insert
    fun insertNote(note: NoteModel)

    @Update
    fun updateNote(note: NoteModel)

    @Delete
    fun deleteNote(note: NoteModel)
}