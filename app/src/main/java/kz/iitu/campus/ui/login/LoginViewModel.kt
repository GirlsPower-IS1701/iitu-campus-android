package kz.iitu.campus.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kz.iitu.campus.pojo.auth.LoginResponse
import kz.iitu.campus.repository.AuthRepository
import java.net.ConnectException
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val accessToken = MutableLiveData<LoginResponse>()
    val accessLiveData: LiveData<LoginResponse> = accessToken

    fun login(username: String, pass: String) {
        launch {
            try {
                val response = authRepository.login(username, pass)
                response.let {
                    accessToken.value = it
                }
            } catch (e: ConnectException) {
                Log.d("ntwrk", "No Internet Connection")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    class AuthFactory(
        private val authRepository: AuthRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(authRepository) as T
        }
}

}