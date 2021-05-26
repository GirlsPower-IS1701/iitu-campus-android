package kz.iitu.campus.ui.transcript

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kz.iitu.campus.ui.ref.RefFragment

class StudyPlanPA(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> IupFragment()
            1 -> RefFragment()
            else -> IupFragment()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Study Plan"
            1 -> "My Transcripts"
            else -> ""
        }
    }
}