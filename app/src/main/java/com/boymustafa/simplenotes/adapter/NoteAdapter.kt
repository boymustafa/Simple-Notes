package com.boymustafa.simplenotes.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.boymustafa.simplenotes.R
import com.boymustafa.simplenotes.entity.Note
import kotlinx.android.synthetic.main.note_items.view.*

class NoteAdapter(val note: List<Note>, val recyclerClick: RecyclerClick) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.note_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return note.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = note.get(position)
        holder.txtTitle?.text = note.title
        holder.txtDescription?.text = note.description
        holder.txtTag?.text = note.tag
        holder.cardView?.setBackgroundColor(Color.parseColor(note.color))
        holder.cardViewTag?.setCardBackgroundColor(Color.parseColor(note.color))
    }


    inner class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var txtTitle: TextView? = null
        var txtDescription: TextView? = null
        var txtTag: TextView? = null
        var cardView: CardView? = null
        var cardViewTag:CardView? = null


        init {
            row.setOnClickListener{
                recyclerClick.onItemClick(adapterPosition)
            }
            this.txtTitle = row.findViewById(R.id.txtTitle)
            this.txtDescription =row.findViewById(R.id.txtDescription)
            this.txtTag =row.findViewById(R.id.txtTag)
            this.cardView =row.findViewById(R.id.cardView)
            this.cardViewTag =row.findViewById(R.id.cardViewTag)
        }


    }

    interface RecyclerClick{
        fun onItemClick(position: Int)
    }

    fun getNoteAt(position: Int): Note{
        return note.get(position)
    }
}