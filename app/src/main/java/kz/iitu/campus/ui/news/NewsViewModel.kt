package kz.iitu.campus.ui.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import kz.iitu.campus.model.model.Notification
import kz.iitu.campus.repository.AcademicCalendarRepository
import kotlin.coroutines.CoroutineContext

class NewsViewModel (
    private val academicCalendarRepository: AcademicCalendarRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    val news = MutableLiveData<List<Notification>>()
    val errorLiveData = MutableLiveData<String>()

    fun getNews(token: String) {
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    academicCalendarRepository.getNews(bearer = token)
                }
            }.onSuccess {
                it.let {
                    it.also { news.value = it }
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

    class NewsFactory(
        private val academicCalendarRepository: AcademicCalendarRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewsViewModel(academicCalendarRepository) as T
        }
    }
}