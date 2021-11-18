package com.example.checksheet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Dashboard : AppCompatActivity() {

     private lateinit var buttonQR:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val actionBar = supportActionBar
        actionBar!!.hide()

        buttonQR = findViewById(R.id.cardQRcode)

        buttonQR.setOnClickListener {
            Toast.makeText(this, "QR scanner", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SelectCS::class.java)
            startActivity(intent)
        }
    }
}