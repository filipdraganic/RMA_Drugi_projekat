package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rsrafprojekat1.Filip_Draganic_RN542017.R
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

class LoginActivity : AppCompatActivity(R.layout.activity_login) {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListeners()
    }

    private fun initListeners(){




        prijavaBtn.setOnClickListener{
            Timber.e("Pokusaj prijave")
            val pin = pinET.text.toString()
            val ime = imeET.text.toString()
            val prezime = prezimeET.text.toString()



            if(pin.length > 4){
                Toast.makeText(this,"Pin je predugacak", Toast.LENGTH_SHORT).show()

            }else if(pin.length < 4){
                Toast.makeText(this,"Pin je prekratak", Toast.LENGTH_SHORT).show()

            }

            else if(ime.length < 3){
                Toast.makeText(this,"Ime je prekratko", Toast.LENGTH_SHORT).show()
            }

            else if(prezime.length < 3){
                Toast.makeText(this,"Prezime je prekratko", Toast.LENGTH_SHORT).show()
            }

            else{
                if(pin != "1234"){
                    Toast.makeText(this,"Pin je netacan", Toast.LENGTH_SHORT).show()
                }

                else{



                    Toast.makeText(this, "Uspesna prijava", Toast.LENGTH_SHORT).show()
                    Timber.e("Prijava")



                    intent = Intent(this, MainActivity::class.java)

                    startActivity(intent)
                    finish()
                }
            }
        }


    }


}
