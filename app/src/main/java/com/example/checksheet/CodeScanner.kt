package com.example.checksheet

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode

class CodeScanner : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_scanner)

        val actionBar = supportActionBar
        actionBar!!.title = "Scanner"
        actionBar.setDisplayHomeAsUpEnabled(true)

        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),123)
        }else{
            startScanning()
        }


    }

    private fun startScanning(){
        val scannerView: CodeScannerView = findViewById(R.id.scanner_view)
        codeScanner = CodeScanner(this,scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {

                when(it.text){

                    "PT_ED_CHECKSHEET" -> {

                        Toast.makeText(this,it.text, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, com.example.checksheet.SelectCS::class.java)
                        startActivity(intent)
                    }
                }



            }
        }

        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(this,"Camera initialization error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 123){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Camera permission granted", Toast.LENGTH_SHORT).show()
                startScanning()
            }else{
                Toast.makeText(this,"Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codeScanner.isInitialized){
            codeScanner?.startPreview()
        }
    }

    override fun onPause() {
        if (::codeScanner.isInitialized){
            codeScanner?.releaseResources()
        }
        super.onPause()
    }
}