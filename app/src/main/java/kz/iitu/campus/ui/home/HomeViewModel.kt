package kz.iitu.campus.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import kz.iitu.campus.model.schedule.Group
import kz.iitu.campus.model.schedule.Staff
import kz.iitu.campus.model.schedule.Timetable
import kz.iitu.campus.repository.ScheduleRepository
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val scheduleRepository: ScheduleRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    val mondayList = MutableLiveData<List<Timetable>>()
    val tuesdayList = MutableLiveData<List<Timetable>>()
    val wednesdayList = MutableLiveData<List<Timetable>>()
    val thursdayList = MutableLiveData<List<Timetable>>()
    val fridayList = MutableLiveData<List<Timetable>>()

    val staffList = MutableLiveData<List<Staff>>()
    val groupList = MutableLiveData<List<Group>>()

    val errorLiveData = MutableLiveData<String>()
    val group = MutableLiveData<String>("IS-1701K")
    val selectedFilter = MutableLiveData<Int>(0)
    val loadingState = MutableLiveData<Boolean>(false)

    fun getStaffList(token: String) {
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    scheduleRepository.getStaffList(bearer = token)
                }
            }.onSuccess {
                it.let {
                    it.also { staffList.value = it }
                    Log.d("ntwrk", it.toString())
                }
            }.onFailure {
                errorLiveData.value = it.message.toString()
                Log.d("ntwrk", it.message.toString())
            }
        }
    }

    fun getGroupList(token: String) {
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    scheduleRepository.getGroupList(bearer = token)
                }
            }.onSuccess {
                it.let {
                    it.also { groupList.value = it }
                    Log.d("ntwrk", it.toString())
                }
            }.onFailure {
                errorLiveData.value = it.message.toString()
                Log.d("ntwrk", it.message.toString())
            }
        }
    }

    fun getGroupTimeTable(token: String) {
        loadingState.value = true
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    scheduleRepository.getGroupTimetable(bearer = token)
                }
            }.onSuccess {
                loadingState.value = false
                it.let {
                    it.also {
                        group.value = "IS-1701K"
                        setDayOfWeeks(it)
                    }
                    Log.d("ntwrk", it.toString())
                }
            }.onFailure {
                loadingState.value = false
                errorLiveData.value = it.message.toString()
                Log.d("ntwrk", it.message.toString())
            }
        }
    }

    fun getGroupTimeTableByStaff(token: String, staffId: Int) {
        loadingState.value = true
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    scheduleRepository.getGroupTimetableByStaff(bearer = token, id = staffId)
                }
            }.onSuccess {
                loadingState.value = false
                it.let {
                    it.also {
                        group.value = "IS-1701K"
                        setDayOfWeeks(it)
                    }
                    Log.d("ntwrk", it.toString())
                }
            }.onFailure {
                loadingState.value = false
                errorLiveData.value = it.message.toString()
                Log.d("ntwrk", it.message.toString())
            }
        }
    }

    fun getGroupTimeTableByGroup(token: String, groupId: Int) {
        loadingState.value = true
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    scheduleRepository.getGroupTimetableByGroup(bearer = token, id = groupId)
                }
            }.onSuccess {
                loadingState.value = false
                it.let {
                    it.also {
                        group.value = it.group
                        setDayOfWeeks(it.timetable)
                    }
                    Log.d("ntwrk", it.toString())
                }
            }.onFailure {
                loadingState.value = false
                errorLiveData.value = it.message.toString()
                Log.d("ntwrk", it.message.toString())
            }
        }
    }

    fun getGroupTimeTableByRoom(token: String, room: Int) {
        loadingState.value = true
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    scheduleRepository.getGroupTimetableByRoom(bearer = token, room = room)
                }
            }.onSuccess {
                loadingState.value = false
                it.let {
                    it.also {
                        group.value = "IS-1701K"
                        setDayOfWeeks(it)
                    }
                    Log.d("ntwrk", it.toString())
                }
            }.onFailure {
                loadingState.value = false
                errorLiveData.value = it.message.toString()
                Log.d("ntwrk", it.message.toString())
            }
        }
    }

    private fun setDayOfWeeks(list: List<Timetable>) {
        val monday = mutableListOf<Timetable>()
        val tuesday = mutableListOf<Timetable>()
        val wednesday = mutableListOf<Timetable>()
        val thursday = mutableListOf<Timetable>()
        val friday = mutableListOf<Timetable>()
        for (timetable in list) {
            if (timetable.day_of_week == 1) {
                monday.add(timetable)
            }
            if (timetable.day_of_week == 1) {
                tuesday.add(timetable)
            }
            if (timetable.day_of_week == 3) {
                wednesday.add(timetable)
            }
            if (timetable.day_of_week == 4) {
                thursday.add(timetable)
            }
            if (timetable.day_of_week == 5) {
                friday.add(timetable)
            }
        }
        mondayList.value = monday
        tuesdayList.value = tuesday
        wednesdayList.value = wednesday
        thursdayList.value = thursday
        fridayList.value = friday

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    class ScheduleFactory(
        private val scheduleRepository: ScheduleRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(scheduleRepository) as T
        }
    }
}