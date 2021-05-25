package kz.iitu.campus.ui.academ_calendar

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import kz.iitu.campus.model.academic_calendar.AcademicCalendar
import kz.iitu.campus.model.academic_calendar.AcademicCalendarDto
import kz.iitu.campus.model.academic_calendar.CalendarItem
import kz.iitu.campus.model.model.StudyPlan
import kz.iitu.campus.repository.AcademicCalendarRepository
import kz.iitu.campus.repository.StudyPlanRepository
import kz.iitu.campus.ui.transcript.IupViewModel
import kotlin.coroutines.CoroutineContext

class AcademicCalendarViewModel(
    private val academicCalendarRepository: AcademicCalendarRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    val calendar = MutableLiveData<AcademicCalendarDto>()
    val errorLiveData = MutableLiveData<String>()

    fun getCalendar(token: String) {
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    academicCalendarRepository.getCalendar(bearer = token)
                }
            }.onSuccess {
                it.let {
                    it.also { calendar.value = it }
                    Log.d("ntwrk", it.toString())
                }
            }.onFailure {
                errorLiveData.value = it.message.toString()
                Log.d("ntwrk", it.message.toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    class CalendarFactory(
        private val academicCalendarRepository: AcademicCalendarRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AcademicCalendarViewModel(academicCalendarRepository) as T
        }
    }
}