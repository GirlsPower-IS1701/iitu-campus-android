package kz.iitu.campus.ui.transcript

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_transcript.*
import kz.iitu.campus.R
import kz.iitu.campus.repository.StudyPlanRepository
import kz.iitu.campus.services.ApiFactory

class TranscriptFragment
    : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(
            this, IupViewModel.IupFactory(
                StudyPlanRepository(
                    ApiFactory.getApi()
                )
            )
        )[IupViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_transcript, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        create_iup.setOnClickListener {
            Toast.makeText(
                context,
                "Not available now",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}