package com.pocket.allinone.ui

import android.os.Bundle
import com.pocket.allinone.R

class UnknownNumberActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
       return R.layout.activity_unknown_number
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(resources.getString(R.string.unknown_whatsapp_number))

    }


}