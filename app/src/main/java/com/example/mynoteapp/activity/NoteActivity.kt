package com.example.mynoteapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapp.database.NoteDatabase
import com.example.mynoteapp.databinding.ActivityNoteBinding
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.model.NoteModel
import com.example.mynoteapp.recyclerview.NotesAdapter
import com.example.mynoteapp.resources.MyViewModel
import com.example.mynoteapp.setFullScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding

    private lateinit var notesRV: RecyclerView
    private lateinit var notesAdapter: NotesAdapter

    private lateinit var myViewModel: MyViewModel


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set UI
        setFullScreen(window)
        lightStatueBar(window)
        setRecyclerview()

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.getNotes().observe(this, {
                allNotes -> notesAdapter.update(allNotes as ArrayList<NoteModel>)
        })

        // Go to Add Note
        binding.addIcon.setOnClickListener {
            val intent = Intent(this, AddNewNoteActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setRecyclerview() {
        notesRV = binding.allNotesRecyclerview
        notesAdapter = NotesAdapter(this)
        notesRV.adapter = notesAdapter
    }

}