package kz.iitu.campus.ui.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kz.iitu.campus.repository.AuthRepository
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(
    private val authRepository: AuthRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


}