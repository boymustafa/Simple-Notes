package com.boymustafa.simplenotes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.boymustafa.simplenotes.entity.Note
import com.boymustafa.simplenotes.repository.Repository

class ViewModel(app:Application):AndroidViewModel(app) {

    var repository:Repository = Repository(app)

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun delete(note: Note) {
        repository.delete(note)
    }

    fun update(note: Note) {
        repository.update(note)
    }

    fun getAllNotes():LiveData<List<Note>> {
        return repository.getAllNotes()
    }
}