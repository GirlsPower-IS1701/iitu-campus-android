package kz.iitu.campus.ui.ref

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_academic_calendar.*
import kotlinx.android.synthetic.main.fragment_ref.*
import kz.iitu.campus.R
import kz.iitu.campus.repository.RefRepository
import kz.iitu.campus.services.ApiFactory
import kz.iitu.campus.services.UserSession
import kz.iitu.campus.ui.transcript.IupRVA

class RefFragment
    : Fragment() {

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
        val root = inflater.inflate(R.layout.fragment_ref, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()

        setListeners()

        setObservers()
    }

    private fun setupViews() {
        refHistoryRV.layoutManager =
            LinearLayoutManager(this.context)

        val bearer: String = "Bearer " + this.context?.let { UserSession.getUserToken(it) }
        viewModel.getHistory(bearer)
    }

    private fun setListeners() {
        create_ref.setOnClickListener {
            val dialog = RefCreateDialog()
            dialog.show(childFragmentManager, "dialog")
        }
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
        viewModel.history.observe(viewLifecycleOwner, Observer {
            refHistoryRV.adapter = RefHistoryRVA(it)
        })
    }

}