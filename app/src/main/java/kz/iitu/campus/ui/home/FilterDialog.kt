package kz.iitu.campus.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.dialog_create_ref.btn_ok
import kotlinx.android.synthetic.main.dialog_filter.*
import kz.iitu.campus.R
import kz.iitu.campus.repository.ScheduleRepository
import kz.iitu.campus.services.ApiFactory
import kz.iitu.campus.services.UserSession


class FilterDialog : DialogFragment() {

    private lateinit var callback: OnFilterSelectedCallback
    private var selectedFilter: Int = 0
    private var selectedStaff: Int = 1
    private var selectedGroup: Int = 1

    private val viewModel by lazy {
        ViewModelProviders.of(
            this, HomeViewModel.ScheduleFactory(
                ScheduleRepository(
                    ApiFactory.getApi()
                )
            )
        )[HomeViewModel::class.java]
    }

    companion object {
        fun newInstance(selectedFilter: Int): FilterDialog {
            val args = Bundle()
            args.putInt("content", selectedFilter)
            val fragment = FilterDialog()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            if (parentFragment is OnFilterSelectedCallback)
                callback = parentFragment as OnFilterSelectedCallback
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_filter, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()

        setObservers()

        setListeners()
    }

    private fun setUpViews() {
        val bearer: String = "Bearer " + this.context?.let { UserSession.getUserToken(it) }
        viewModel.getGroupList(bearer)
        viewModel.getStaffList(bearer)

        val dataPassed: Int? = arguments?.getInt("content")
        selectedFilter = dataPassed!!
        if (selectedFilter == 0){
            my.isChecked = true
        }
        if (selectedFilter == 1){
            staff.isChecked = true
        }
        if (selectedFilter == 3){
            group.isChecked = true
        }
        if (selectedFilter == 5){
            room.isChecked = true
        }
    }

    private fun setObservers() {
        staffs.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            selectedStaff = viewModel.staffList.value!!.get(i).id
        }
        groups.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            selectedGroup = viewModel.groupList.value!!.get(i).id
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Toast.makeText(
                    context,
                    it,
                    Toast.LENGTH_LONG
                ).show()
            dismiss()
        })
        viewModel.staffList.observe(viewLifecycleOwner, Observer {
            val adapter = ArrayAdapter(requireContext(), R.layout.list_staff_item, R.id.sstaff, it)
            (staffs)?.setAdapter(adapter)
        })
        viewModel.groupList.observe(viewLifecycleOwner, Observer {
            val adapter = ArrayAdapter(requireContext(), R.layout.list_group_item, R.id.sgroup, it)
            (groups)?.setAdapter(adapter)
        })
    }

    private fun setListeners() {
        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val radioButton: View = radioGroup.findViewById(checkedId)
            val index: Int = radioGroup.indexOfChild(radioButton)
            selectedFilter = index
        })

        btn_no.setOnClickListener {
            dismiss()
        }
        btn_ok.setOnClickListener {
            if (selectedFilter == 0) {
                callback.onMyGroupChecked()
            }
            if (selectedFilter == 1) {
                callback.onByStaffChecked(selectedStaff)
            }
            if (selectedFilter == 3) {
                callback.onByGroupChecked(selectedGroup)
            }
            if (selectedFilter == 5) {
                if (roomText.text.isNullOrBlank()) {
                    Toast.makeText(
                        context,
                        "Please,select room",
                        Toast.LENGTH_LONG
                    ).show()
                }
                var room = roomText.text.toString().toInt()
                callback.onByRoomChecked(room)
            }
            dismiss()
        }
    }

    interface OnFilterSelectedCallback {

        fun onMyGroupChecked() = Unit

        fun onByStaffChecked(id: Int) = Unit

        fun onByGroupChecked(id: Int) = Unit

        fun onByRoomChecked(room: Int) = Unit

    }
}