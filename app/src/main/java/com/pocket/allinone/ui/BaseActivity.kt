package com.pocket.allinone.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.pocket.allinone.R
import com.pocket.allinone.databinding.ActivityBaseBinding
import com.pocket.allinone.databinding.ActivityQrCodeBinding
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.layout_toolbar.*


abstract class BaseActivity : AppCompatActivity() {
    abstract fun getLayoutResource(): Int
    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        val view = LayoutInflater.from(this).inflate(getLayoutResource(), rootLayout, false)
        container.addView(view)
        iniToolbar()


    }

    private fun iniToolbar() {
        setSupportActionBar(toolbar)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        supportActionBar?.title = title
    }

    fun setHomeButtonEnabled(isEnabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isEnabled)

    }





}