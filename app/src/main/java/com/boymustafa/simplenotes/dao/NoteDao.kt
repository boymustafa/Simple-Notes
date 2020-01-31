package com.boymustafa.simplenotes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.boymustafa.simplenotes.entity.Note

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("select * from tbl_note")
    fun getAllNotes():LiveData<List<Note>>

}