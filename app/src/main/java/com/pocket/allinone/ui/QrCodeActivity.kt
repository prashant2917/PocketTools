package com.pocket.allinone.ui

import android.os.Bundle
import com.pocket.allinone.R

class QrCodeActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_qr_code
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.qr_and_barcode_scanner)
        setHomeButtonEnabled(true)

    }


}