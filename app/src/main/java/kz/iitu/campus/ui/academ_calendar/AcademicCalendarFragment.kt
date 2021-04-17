package kz.iitu.campus.ui.academ_calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_academic_calendar.*
import kz.iitu.campus.R

class AcademicCalendarFragment : Fragment() {

    private lateinit var viewmodel: AcademicCalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel =
            ViewModelProvider(this).get(AcademicCalendarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_academic_calendar, container, false)
        setupViews()
        setObservers()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        autumnRV.layoutManager =
            LinearLayoutManager(this.context)
        springRV.layoutManager =
            LinearLayoutManager(this.context)

    }

    private fun setupViews() {
        viewmodel.setList()
    }

    private fun setObservers() {
        viewmodel.list.observe(viewLifecycleOwner, Observer {
            autumnRV.adapter = CalendarRVA(it.autumnSemesterItems)
            springRV.adapter =
                CalendarRVA(it.springSemesterItems)
        })
    }
}