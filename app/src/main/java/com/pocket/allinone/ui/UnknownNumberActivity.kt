package com.pocket.allinone.ui

import android.os.Bundle
import com.pocket.allinone.R
import com.pocket.allinone.extensions.openWhatsApp
import kotlinx.android.synthetic.main.activity_unknown_number.*

class UnknownNumberActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
       return R.layout.activity_unknown_number
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.unknown_whatsapp_number)
        setHomeButtonEnabled(true)
        btn_submit.setOnClickListener{
            if(et_number.text!!.isNotEmpty() && et_message.text!!.isNotEmpty()){
                openWhatsApp(et_number.text.toString(),et_message.text.toString())
            }
            else{
                showToast(resources.getString(R.string.enter_number_and_message))
            }

        }

    }


}