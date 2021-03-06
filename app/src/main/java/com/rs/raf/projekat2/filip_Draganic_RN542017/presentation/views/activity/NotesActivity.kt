package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.activity


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rsrafprojekat1.Filip_Draganic_RN542017.R
import kotlinx.android.synthetic.main.activity_notes.*


class NotesActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)


        val kod = intent.getStringExtra("requestCode")

        if(kod == "1337"){
            izmeniBtn.text = "Napravi"
        }
        else{
            val title = intent.getStringExtra("title")
            val content = intent.getStringExtra("content")

            titleET.setText(title)
            contentET.setText(content)

            izmeniBtn.text = "Izmeni"
        }


        init()
    }

    private fun init(){
        initListeners()
        initObservers()
    }

    private fun initListeners(){
        odustaniBtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        izmeniBtn.setOnClickListener {
            val title = titleET.text.toString()
            val content = contentET.text.toString()

            if(title.length < 1 && content.length < 1){
                Toast.makeText(this, "Both fields need to be filled in order to complete this action", Toast.LENGTH_SHORT).show()


            }
            else{
                if (intent.getStringExtra("requestCode") == "1337") {
                    val data = Intent()

                    data.putExtra("title", title)
                    data.putExtra("content", content)
                    setResult(Activity.RESULT_OK, data)
                    finish()
                }
                else{
                    val id = intent.getLongExtra("id", 1)
                    val data = Intent()

                    data.putExtra("title", title)
                    data.putExtra("content", content)
                    data.putExtra("id", id )
                    setResult(Activity.RESULT_OK, data)
                    finish()

                }
            }


        }
    }

    private fun initObservers(){

    }
}