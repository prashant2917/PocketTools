package com.pocket.allinone.ui

import android.Manifest
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.CompoundButton
import androidx.annotation.RequiresApi
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.pocket.allinone.R
import com.pocket.allinone.databinding.ActivityQrCodeBinding
import com.pocket.allinone.extensions.*
import com.pocket.allinone.listeners.DialogClickListener
import kotlinx.android.synthetic.main.activity_qr_code.*
import me.dm7.barcodescanner.zxing.ZXingScannerView


class QrCodeActivity : BaseActivity(), ZXingScannerView.ResultHandler {
    private lateinit var mScannerView: ZXingScannerView
    private var mFlash = false
    private var mAutoFocus = false
    private var mSelectedIndices: ArrayList<Int>? = null
    private var mCameraId = -1
    private lateinit var binding: ActivityQrCodeBinding

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 101
        private const val FLASH_STATE = "FLASH_STATE"
        private const val AUTO_FOCUS_STATE = "AUTO_FOCUS_STATE"
        private const val SELECTED_FORMATS = "SELECTED_FORMATS"
        private const val CAMERA_ID = "CAMERA_ID"
    }

    private val permissionArray = Array(1) { Manifest.permission.CAMERA }
    override fun getLayoutResource(): Int {
        return R.layout.activity_qr_code
    }

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        title = resources.getString(R.string.qr_and_barcode_scanner)
        setHomeButtonEnabled(true)
        if (state != null) {
            mFlash = state.getBoolean(FLASH_STATE, false)
            mAutoFocus = state.getBoolean(AUTO_FOCUS_STATE, true)
            mSelectedIndices = state.getIntegerArrayList(SELECTED_FORMATS)
            mCameraId = state.getInt(CAMERA_ID, -1)
        } else {
            mFlash = false
            mAutoFocus = true
            mSelectedIndices = null
            mCameraId = -1
        }
        mScannerView = ZXingScannerView(this)
        setupFormats()
        content_frame.addView(mScannerView)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        if (!hasPermission(Manifest.permission.CAMERA)) {
            requestPermission(permissionArray, CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            initScanner()
        }
    }

    private fun initScanner() {
        mScannerView.setResultHandler(this)
        mScannerView.startCamera(mCameraId)
        mScannerView.flash = mFlash
        mScannerView.setAutoFocus(mAutoFocus)
        switch_autofocus.setOnCheckedChangeListener(autoFocusListener)
        switch_flash.setOnCheckedChangeListener(flashListener)
    }

    private val autoFocusListener =
        CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            mAutoFocus = !mAutoFocus
            mScannerView.setAutoFocus(isChecked)

        }

    private val flashListener =
        CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            mFlash = !mFlash
            mScannerView.flash = isChecked


        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                initScanner()

            } else {
                showToast(resources.getString(R.string.permission_denied_message))
            }
        }
    }

    override fun handleResult(rawResult: Result?) {
        try {
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(applicationContext, notification)
            r.play()
        } catch (e: Exception) {
        }
        if (rawResult!!.barcodeFormat == BarcodeFormat.QR_CODE) {
            navigateToUrl(Uri.parse(rawResult.text))

        } else {
            showAlertDialogWithTwoButtons(
                resources.getString(R.string.qr_code_dialog_title),
                resources.getString(
                    R.string.scan_result_is,
                    "Contents = " + rawResult.text + ", Format = " + rawResult.barcodeFormat.toString()
                ),
                resources.getString(R.string.OK),
                resources.getString(R.string.cancel),
                dialogClickListener
            )
        }

    }

    private fun setupFormats() {
        val formats: MutableList<BarcodeFormat> = ArrayList()
        if (mSelectedIndices == null || mSelectedIndices!!.isEmpty()) {
            mSelectedIndices = ArrayList()
            for (i in ZXingScannerView.ALL_FORMATS.indices) {
                mSelectedIndices!!.add(i)
            }
        }
        for (index in mSelectedIndices!!) {
            formats.add(ZXingScannerView.ALL_FORMATS[index])
        }
        if (mScannerView != null) {
            mScannerView.setFormats(formats)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FLASH_STATE, mFlash)
        outState.putBoolean(AUTO_FOCUS_STATE, mAutoFocus)
        outState.putIntegerArrayList(SELECTED_FORMATS, mSelectedIndices)
        outState.putInt(CAMERA_ID, mCameraId)
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()

    }

    private val dialogClickListener = object : DialogClickListener {
        override fun onPositiveClick() {
            mScannerView.resumeCameraPreview(this@QrCodeActivity)
        }

        override fun onNegativeClick() {
            mScannerView.resumeCameraPreview(this@QrCodeActivity)
        }


    }

}