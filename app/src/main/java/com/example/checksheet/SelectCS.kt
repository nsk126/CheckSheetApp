package com.example.checksheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContentProviderCompat.requireContext

class SelectCS : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_cs)

        val stations = resources.getStringArray(R.array.stations)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        val adapter = ArrayAdapter(applicationContext,R.layout.dropdown_items,R.id.textView,stations)

        autoCompleteTextView.setAdapter(adapter)
    }




}