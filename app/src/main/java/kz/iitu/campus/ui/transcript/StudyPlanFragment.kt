package kz.iitu.campus.ui.transcript

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_study_plan_main.*
import kz.iitu.campus.R

class StudyPlanFragment: Fragment() {
    private lateinit var adapter: StudyPlanPA
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_study_plan_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = StudyPlanPA(childFragmentManager)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = adapter
        this.tab_layout.setupWithViewPager(this.viewPager)
    }
}