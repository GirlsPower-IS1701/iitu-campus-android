package kz.iitu.campus.ui.transcript

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import kz.iitu.campus.model.model.StudyPlan
import kz.iitu.campus.model.model.StudyPlanDto
import kz.iitu.campus.repository.StudyPlanRepository
import kotlin.coroutines.CoroutineContext

class IupViewModel(
    private val studyPlanRepository: StudyPlanRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val studyPlan = MutableLiveData<StudyPlanDto>()
    val errorLiveData = MutableLiveData<String>()

    fun getStudyPlan(token: String) {
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    studyPlanRepository.getStudyPlan(bearer = token)
                }
            }.onSuccess {
                it.let {
                    it.also { studyPlan.value = it }
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

    class IupFactory(
        private val studyPlanRepository: StudyPlanRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return IupViewModel(studyPlanRepository) as T
        }
    }
}