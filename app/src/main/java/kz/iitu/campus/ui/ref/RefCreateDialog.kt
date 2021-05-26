package kz.iitu.campus.ui.ref

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.dialog_create_ref.*
import kotlinx.android.synthetic.main.dialog_create_ref.loading_state
import kotlinx.android.synthetic.main.dialog_exit.*
import kotlinx.android.synthetic.main.dialog_exit.btn_ok
import kz.iitu.campus.R
import kz.iitu.campus.repository.RefRepository
import kz.iitu.campus.services.ApiFactory
import kz.iitu.campus.services.UserSession

class RefCreateDialog : DialogFragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(
            this, RefViewModel.AuthFactory(
                RefRepository(
                    ApiFactory.getApi()
                )
            )
        )[RefViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_create_ref, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = listOf("Сertificate from the place of study")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, R.id.types, items)
        (type)?.setAdapter(adapter)
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Toast.makeText(
                    context,
                    it,
                    Toast.LENGTH_LONG
                ).show()
        })
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Toast.makeText(
                    context,
                    it,
                    Toast.LENGTH_LONG
                ).show()
            dismiss()
        })
        viewModel.loadingState.observe(this, Observer {
            loading_state.isVisible = it
        })
    }

    private fun setListeners() {
        val bearer: String = "Bearer " + this.context?.let { UserSession.getUserToken(it) }
        btn_ok.setOnClickListener {
            viewModel.createRef(bearer)
        }
    }


}