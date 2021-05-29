package kz.iitu.campus.ui.transcript

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
import kotlinx.android.synthetic.main.fragment_study_plan.*
import kz.iitu.campus.R
import kz.iitu.campus.repository.StudyPlanRepository
import kz.iitu.campus.services.ApiFactory
import kz.iitu.campus.services.UserSession
import kz.iitu.campus.ui.utils.AnimRecipes.collapseExpand

class IupFragment : Fragment() {

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
        val root = inflater.inflate(R.layout.fragment_study_plan, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        setObservers()

        setListeners()
    }

    private fun setupViews() {
        first_semesterRVA.layoutManager =
            LinearLayoutManager(this.context)
        second_semesterRVA.layoutManager =
            LinearLayoutManager(this.context)

        val bearer: String = "Bearer " + this.context?.let { UserSession.getUserToken(it) }
        viewModel.getStudyPlan(bearer)
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
        viewModel.studyPlan.observe(viewLifecycleOwner, Observer {
            first_semesterRVA.adapter = IupRVA(it.firstsemester)
            second_semesterRVA.adapter = IupRVA(it.secondsemester)
        })
    }

    private fun setListeners() {
        ec_first.setOnClickListener{
            collapseExpand(ec_first, first_semesterRVA)
        }
        ec_second.setOnClickListener{
            collapseExpand(ec_second, second_semesterRVA)
        }
    }
}