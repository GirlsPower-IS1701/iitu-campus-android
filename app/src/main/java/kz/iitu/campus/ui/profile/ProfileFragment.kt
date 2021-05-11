package kz.iitu.campus.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_profile.*
import kz.iitu.campus.R
import kz.iitu.campus.repository.AuthRepository
import kz.iitu.campus.services.ApiFactory

class ProfileFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(
            this, ProfileViewModel.AuthFactory(
                AuthRepository(
                    ApiFactory.getApi()
                )
            )
        )[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        setupViews()
        setObservers()
        return root
    }

    private fun setupViews() {
        viewModel.getProfile()
    }

    private fun setObservers() {
        viewModel.profile.observe(viewLifecycleOwner, Observer {
            if (it == null)
                return@Observer

            profile_id.text = it.student.user.userName
            profile_course.text = it.student.course.toString()
            profile_email.text = it.student.user.email
            profile_phone.text = it.student.user.phoneNumber
            profile_name.text = it.student.user.firstName + " " + it.student.user.lastName
            profile_group.text = "IS-1701K"
        })
    }
}