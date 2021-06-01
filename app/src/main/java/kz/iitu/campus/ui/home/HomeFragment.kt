package kz.iitu.campus.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kz.iitu.campus.R
import kz.iitu.campus.repository.ScheduleRepository
import kz.iitu.campus.services.ApiFactory
import kz.iitu.campus.services.UserSession

class HomeFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(
            this, HomeViewModel.ScheduleFactory(
                ScheduleRepository(
                    ApiFactory.getApi()
                )
            )
        )[HomeViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        thursdayRv.layoutManager =
            LinearLayoutManager(this.context)
        fridayRV.layoutManager =
            LinearLayoutManager(this.context)
    }

    private fun setupViews() {
        val bearer: String = "Bearer " + this.context?.let { UserSession.getUserToken(it) }
        viewModel.getGroupTimeTable(bearer)
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
        viewModel.mondayList.observe(viewLifecycleOwner, Observer {
            mondayRv.adapter = ScheduleRVA(it)
        })

        viewModel.tuesdayList.observe(viewLifecycleOwner, Observer {
            tuesdayRv.adapter = ScheduleRVA(it)
        })

        viewModel.wednesdayList.observe(viewLifecycleOwner, Observer {
            wednesdayRv.adapter = ScheduleRVA(it)
        })

        viewModel.thursdayList.observe(viewLifecycleOwner, Observer {
            thursdayRv.adapter = ScheduleRVA(it)
        })

        viewModel.fridayList.observe(viewLifecycleOwner, Observer {
            fridayRV.adapter = ScheduleRVA(it)
        })
    }
}