package kz.iitu.campus.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kz.iitu.campus.model.model.*
import kz.iitu.campus.repository.AuthRepository
import kz.iitu.campus.ui.login.LoginViewModel
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(
    private val authRepository: AuthRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val profile = MutableLiveData<StudentProfile>()

    fun getProfile() {
        profile.value = StudentProfile(
            Student(
                User("Ainur", "Makymetova", "23961", "ainur.is1701@gmail.com", "8(707) 563-63-72"),
                StudyStatus("active", "23.08.2017", false),
                StudyForm("daily", 4, "23.08.2017", false),
                PaymentForm("", "23.08.2017", false),
                StudyStatus("bacheleor", "23.08.2017", false),
                4, false, "23.08.2017"
            ),
            "", "23.08.2017", false
        )
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