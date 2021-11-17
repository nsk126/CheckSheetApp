package com.example.checksheet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    // Variables for each layout object

    private lateinit var loginSubmit:Button
    private lateinit var userid:EditText
    private lateinit var pwd:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.hide()


        loginSubmit =findViewById(R.id.loginSubmit)
        userid = findViewById(R.id.userid_TXT)
        pwd = findViewById(R.id.pwd_TXT)

        loginSubmit.setOnClickListener {

            if (userid.text.toString() == "admin" && pwd.text.toString() == "12345"){

                //case for successful admin login

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)

            }else{

                //case for failed admin login

                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()

            }
        }

    }
}