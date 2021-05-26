package kz.iitu.campus.ui.transcript

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kz.iitu.campus.ui.ref.RefFragment

class StudyPlanPA(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment?
        if (position == 0) {
            fragment = IupFragment()
        } else {
            fragment = RefFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }


    override fun getPageTitle(position: Int): CharSequence {
        var fragment: String?

        if (position == 0) {
            fragment = "Study Plan"
        } else {
            fragment = "My Transcripts"
        }
        return fragment
    }
}