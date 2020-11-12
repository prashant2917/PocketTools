package com.pocket.allinone.extensions

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.pocket.allinone.R
import com.pocket.allinone.listeners.DialogClickListener


fun Activity.navigateToUrl(url: Uri) {
    val intent = Intent(Intent.ACTION_VIEW, url)
    val chooser = Intent.createChooser(intent, resources.getString(R.string.open_with))
    if (intent.resolveActivity(this.packageManager) != null) {
        startActivity(chooser)
    }


}

fun Activity.openWhatsApp(number: String, message: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$number&text=$message")
        intent.setPackage("com.whatsapp")
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Activity.showAlertDialogWithTwoButtons(
    title: String,
    message: String,
    positiveButton: String,
    negativeButton: String,
    dialogClickListener: DialogClickListener
) {

    val alertDialog = AlertDialog.Builder(this)
    alertDialog.setTitle(title)
    alertDialog.setMessage(message)
    alertDialog.setCancelable(false)
    alertDialog.setPositiveButton(positiveButton) { dialog, which ->
        dialog.dismiss()
        dialogClickListener.onPositiveClick()

    }
    alertDialog.setNegativeButton(negativeButton) { dialog, which ->
        dialog.dismiss()
        dialogClickListener.onPositiveClick()

    }
    alertDialog.create().show()
}

fun Activity.isFlashAvailable(): Boolean {
    return applicationContext.getPackageManager()
        .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

}


fun Activity.switchTorch(cameraManager: CameraManager?, cameraId: String?, status: Boolean) {
    try {
        if (cameraId != null) {
            cameraManager?.setTorchMode(cameraId, status)
        }
    } catch (e: CameraAccessException) {
        e.printStackTrace()
    }
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

fun Activity.requestPermission(permissionArray: Array<String>, permissionCode: Int) {
    ActivityCompat
        .requestPermissions(
            this,
            permissionArray, permissionCode
        )
}