package kz.iitu.campus.ui.ref

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_ref.*
import kz.iitu.campus.R
import kz.iitu.campus.repository.RefRepository
import kz.iitu.campus.services.ApiFactory

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
    }

    private fun setupViews() {
        create_ref.setOnClickListener {
            val dialog = RefCreateDialog()
            dialog.show(childFragmentManager, "dialog")
        }
    }

}