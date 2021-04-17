package kz.iitu.campus.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kz.iitu.campus.R
import kz.iitu.campus.pojo.schedule.ScheduleItem
import kz.iitu.campus.pojo.schedule.ScheduleRVA

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        setupViews()
        setObservers()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mondayRv.layoutManager =
            LinearLayoutManager(this.context)
        tuesdayRv.layoutManager =
            LinearLayoutManager(this.context)
        wednesdayRv.layoutManager =
            LinearLayoutManager(this.context)
    }

    private fun setupViews() {
        homeViewModel.setList()
    }

    private fun setObservers() {
        homeViewModel.list.observe(viewLifecycleOwner, Observer {
            mondayRv.adapter = ScheduleRVA(it.mondayList)
            tuesdayRv.adapter = ScheduleRVA(it.tuesdayList)
            wednesdayRv.adapter = ScheduleRVA(it.wednesdayList)
        })
    }
}