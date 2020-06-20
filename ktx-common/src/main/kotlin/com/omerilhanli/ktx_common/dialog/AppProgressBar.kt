package com.omerilhanli.ktx_common.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import com.omerilhanli.ktx_common.R

class AppProgressBar {

    var dialog: Dialog? = null
        private set

    @JvmOverloads
    fun show(context: Context, cancelable: Boolean = false): Dialog {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        dialog = Dialog(context, R.style.ThemeDialog)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window!!.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.parseColor(context.getString(R.string.color_dialog_background))))

        val view = inflater.inflate(R.layout.app_progress_bar, null)
        dialog?.setCancelable(cancelable)
        dialog?.setContentView(view)

        try {
            dialog!!.show()
        } catch (t: Throwable) {
            t.printStackTrace()
        }

        return dialog as Dialog
    }
}