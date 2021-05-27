package kz.iitu.campus.ui.ref

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import kz.iitu.campus.repository.RefRepository
import kotlin.coroutines.CoroutineContext

class RefViewModel(
    private val refRepository: RefRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val user = MutableLiveData<String>()
    val errorLiveData = MutableLiveData<String>()
    val loadingState = MutableLiveData<Boolean>(false)

    fun createRef(token: String) {
        loadingState.value = true
        launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    refRepository.createRef(token)
                }
            }.onSuccess {
                loadingState.value = false
                it.let {
                    user.value = "Successfully send"
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
        private val refRepository: RefRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RefViewModel(refRepository) as T
        }
    }


}