package com.boymustafa.simplenotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    var color:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        btnAdd.setOnClickListener {

            val  title = addTitle.text.toString()
            val description = addDescription.text.toString()
            val tag = addTag.text.toString()

            val addIntent = Intent()
            addIntent.putExtra("title",title)
            addIntent.putExtra("description",description)
            addIntent.putExtra("tag",tag)
            if (color == null) {
                addIntent.putExtra("color","ffffff")
            } else {
                addIntent.putExtra("color",color)
            }

            setResult(Activity.RESULT_OK,addIntent)
            finish()
        }
    }

    fun buttonColor(view:View){
        val id = view.id
        if (id == R.id.btncolorLightBrown) {
            cardView.setCardBackgroundColor(resources.getColor(R.color.colorBrown))
            color = "#A1887F"
        } else if (id == R.id.btncolorPink) {
            cardView.setCardBackgroundColor(resources.getColor(R.color.colorPink))
            color = "#E880FC"
        } else if (id == R.id.btncolorYellow) {
            cardView.setCardBackgroundColor(resources.getColor(R.color.colorYellow))
            color = "#FFC400"
        } else if (id == R.id.btncolorLightBlue) {
            cardView.setCardBackgroundColor(resources.getColor(R.color.colorLightBlue))
            color = "#BBDEFB"
        } else if (id == R.id.btncolorBlue) {
            cardView.setCardBackgroundColor(resources.getColor(R.color.colorBlue))
            color = "#64FFDA"
        }
    }
}
