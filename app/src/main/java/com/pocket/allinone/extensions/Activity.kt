package com.pocket.allinone.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
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