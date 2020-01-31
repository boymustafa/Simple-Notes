package com.boymustafa.simplenotes.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.boymustafa.simplenotes.dao.NoteDao
import com.boymustafa.simplenotes.database.NoteDatabase
import com.boymustafa.simplenotes.entity.Note

class Repository(app:Application) {

    var noteDao:NoteDao? = NoteDatabase.getDatabase(app)?.noteDao()

    fun insert(note: Note) {
        InsertAsync(noteDao).execute(note)
    }

    fun delete(note:Note) {
        DeleteAsync(noteDao).execute(note)
    }

    fun update(note:Note) {
        UpdateAsync(noteDao).execute(note)
    }

    fun getAllNotes():LiveData<List<Note>> {
       return GetAllNotesAsync(noteDao).execute().get()
    }


    class InsertAsync(noteDao: NoteDao?):AsyncTask<Note,Void,Unit>() {
        var noteDao = noteDao
        override fun doInBackground(vararg p0: Note) {
            noteDao?.insert(p0[0])
        }
    }

    class DeleteAsync(noteDao: NoteDao?):AsyncTask<Note,Void,Unit>(){
        var noteDao = noteDao
        override fun doInBackground(vararg p0: Note) {
            noteDao?.delete(p0[0])
        }
    }

    class UpdateAsync(noteDao: NoteDao?):AsyncTask<Note,Void,Unit>(){
        var noteDao = noteDao
        override fun doInBackground(vararg p0: Note) {
            noteDao?.update(p0[0])
        }
    }

    class GetAllNotesAsync(noteDao: NoteDao?):AsyncTask<Unit,Void,LiveData<List<Note>>>(){
        var noteDao = noteDao
        override fun doInBackground(vararg p0: Unit?): LiveData<List<Note>>? {
            return noteDao?.getAllNotes()
        }
    }

}