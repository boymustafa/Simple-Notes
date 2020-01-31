package com.boymustafa.simplenotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.boymustafa.simplenotes.adapter.NoteAdapter
import com.boymustafa.simplenotes.entity.Note
import com.boymustafa.simplenotes.viewModel.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),NoteAdapter.RecyclerClick {
    override fun onItemClick(position: Int) {

        val note = noteAdapter.getNoteAt(position)
        val id = note.id

        var editIntent = Intent(this,EditActivity::class.java)
        var title  = note.title
        var description = note.description
        var tag = note.tag
        var color = note.color

        editIntent.putExtra("title",title)
        editIntent.putExtra("description",description)
        editIntent.putExtra("tag",tag)
        editIntent.putExtra("id",id.toString())
        editIntent.putExtra("color",color.toString())

        startActivityForResult(editIntent, edit)

        Log.e("MainActivityclick",title+description+tag+position)    }

    companion object{
        val add = 1
        val edit =2
    }

    lateinit var viewModel: ViewModel
    lateinit var noteAdapter: NoteAdapter
    lateinit var getAllNotes:LiveData<List<Note>>
    lateinit var allNotes:List<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Swipe recycler view items on RIGHT
        val helper by lazy {
            object : ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    var position = viewHolder.adapterPosition
                    viewModel.delete(allNotes.get(position))
                    Toast.makeText(applicationContext,"Note Deleted",Toast.LENGTH_SHORT).show()
                }
            }
        }


        //Attaching ViewModel
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)


        //Live Data
        getAllNotes = viewModel.getAllNotes()
        getAllNotes.observe(this, Observer {

            //update RecyclerView
            allNotes = getAllNotes.value!!
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = NoteAdapter(allNotes, this)
            noteAdapter = NoteAdapter(allNotes, this)
            val swipe = ItemTouchHelper(helper)
            swipe.attachToRecyclerView(recyclerView)
        })

        //Floating action button
        fab.setOnClickListener {
            var addIntent = Intent(this, AddActivity::class.java)
            startActivityForResult(addIntent, add)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==add&&resultCode== Activity.RESULT_OK){

            var title = data?.getStringExtra("title")
            var description =  data?.getStringExtra("description")
            var tag = data?.getStringExtra("tag")
            var color = data?.getStringExtra("color")
            val note = Note(0,title.toString(),description.toString(),color.toString(),tag.toString())
            viewModel.insert(note)
            Toast.makeText(applicationContext,"Note Saved", Toast.LENGTH_SHORT).show()
        }
        if(requestCode== edit&&resultCode== Activity.RESULT_OK){

            var title = data?.getStringExtra("title")
            var description =  data?.getStringExtra("description")
            var tag = data?.getStringExtra("tag")
            var id = data?.getStringExtra("id")
            var color = data?.getStringExtra("color")
            Toast.makeText(applicationContext,"Note Updated", Toast.LENGTH_SHORT).show()
            val note  = Note(id?.toInt()!!,title.toString(),description.toString(),color.toString(),tag.toString())
            viewModel.update(note)
        }
    }
}
