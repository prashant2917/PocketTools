package com.pocket.allinone.ui

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.pocket.allinone.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class QrCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)
        iniToolbar()
    }

    private fun iniToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title=resources.getString(R.string.qr_and_barcode_scanner)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed();
        return true;
    }
}