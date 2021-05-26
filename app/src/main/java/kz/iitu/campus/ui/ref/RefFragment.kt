package kz.iitu.campus.ui.ref

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_ref.*
import kz.iitu.campus.R
import kz.iitu.campus.ui.academ_calendar.AcademicCalendarViewModel

class RefFragment
    : Fragment() {

    private lateinit var viewmodel: AcademicCalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel =
            ViewModelProvider(this).get(AcademicCalendarViewModel::class.java)
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