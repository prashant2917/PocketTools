package com.pocket.allinone.ui

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import com.pocket.allinone.R
import com.pocket.allinone.extensions.isFlashAvailable
import com.pocket.allinone.extensions.showToast
import com.pocket.allinone.extensions.switchTorch
import kotlinx.android.synthetic.main.activity_torch.*


class TorchActivity : BaseActivity() {
    private var mCameraManager: CameraManager? = null
    private var mCameraId: String? = null
    override fun getLayoutResource(): Int {
        return R.layout.activity_torch
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.torch)
        setHomeButtonEnabled(true)

    }

    override fun onResume() {
        super.onResume()
        if (isFlashAvailable()) {
            initTorch()
        } else {
            showToast(resources.getString(R.string.torch_error))
        }
    }

    private fun initTorch() {
        toggle_btn_torch.visibility = View.VISIBLE
        toggle_btn_torch.setOnCheckedChangeListener(torchListener)
        mCameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            mCameraId = mCameraManager!!.cameraIdList[0]
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    val torchListener =
        CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            switchTorch(mCameraManager, mCameraId, isChecked)

        }
}