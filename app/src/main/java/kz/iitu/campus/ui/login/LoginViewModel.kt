package kz.iitu.campus.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import kz.iitu.campus.model.model.LoginResponse
import kz.iitu.campus.repository.AuthRepository
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val accessLiveData = MutableLiveData<LoginResponse>()
    val errorLiveData = MutableLiveData<String>()
    val loadingState = MutableLiveData<Boolean>(false)

    fun login(username: String, pass: String) {
        loadingState.value = true
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    authRepository.login(username, pass)
                }
            }.onSuccess {
                loadingState.value = false
                it.let {
                    accessLiveData.value = it
                    Log.d("ntwrk", it.toString())
                }
            }.onFailure {
                loadingState.value = false
                errorLiveData.value = it.message.toString()
                Log.d("ntwrk", it.message.toString())
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