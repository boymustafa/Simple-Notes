package com.boymustafa.simplenotes.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_note")
class Note(@PrimaryKey(autoGenerate = true)
            val id:Int,
           val title: String,
           val description: String,
           val color: String,
           val tag: String) {

}