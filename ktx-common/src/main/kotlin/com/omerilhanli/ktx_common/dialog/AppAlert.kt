package com.omerilhanli.ktx_common.dialog

import android.app.Activity
import android.app.AlertDialog

object AppAlert {
    fun showAlert(activity: Activity, title: String, message: String, buttonTitle: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(buttonTitle) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}