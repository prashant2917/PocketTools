package com.pocket.allinone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pocket.allinone.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class UnknownNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unknown_number)
        iniToolbar()
    }

    private fun iniToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title=resources.getString(R.string.unknown_whatsapp_number)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true;
    }
}