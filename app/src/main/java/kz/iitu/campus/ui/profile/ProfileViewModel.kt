package kz.iitu.campus.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import kz.iitu.campus.model.model.*
import kz.iitu.campus.repository.AuthRepository
import kz.iitu.campus.services.UserSession
import kz.iitu.campus.ui.login.LoginViewModel
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(
    private val authRepository: AuthRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val profile = MutableLiveData<StudentProfile>()
    val errorLiveData = MutableLiveData<String>()

    fun getProfile(token: String) {
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    authRepository.getUserInfo(bearer = token)
                }
            }.onSuccess {
                it.let {
                    profile.value = it
                    Log.d("ntwrk", it.toString())
                }
            }.onFailure {
                errorLiveData.value = it.message.toString()
                Log.d("ntwrk", it.message.toString())
            }
        }
        /*profile.value = StudentProfile(
            Student(
                User("Ainur", "Makymetova", "23961", "ainur.is1701@gmail.com", "8(707) 563-63-72"),
                StudyStatus("active", "23.08.2017", false),
                StudyForm("daily", 4, "23.08.2017", false),
                PaymentForm("", "23.08.2017", false),
                StudyStatus("bacheleor", "23.08.2017", false),
                4, false, "23.08.2017"
            ),
            "", "23.08.2017", false
        )*/
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    class AuthFactory(
        private val authRepository: AuthRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProfileViewModel(authRepository) as T
        }
    }
}