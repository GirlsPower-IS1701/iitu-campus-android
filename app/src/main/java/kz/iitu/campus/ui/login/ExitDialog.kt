package kz.iitu.campus.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_exit.*
import kz.iitu.campus.R

class ExitDialog : DialogFragment() {

    private lateinit var callback: OnExitCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            if (activity is OnExitCallback)
                callback = activity as OnExitCallback
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_exit, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        btn_no.setOnClickListener {
            dismiss()
        }

        btn_ok.setOnClickListener {
            callback.onExit()
        }
    }

    interface OnExitCallback {
        fun onExit() = Unit
    }
}