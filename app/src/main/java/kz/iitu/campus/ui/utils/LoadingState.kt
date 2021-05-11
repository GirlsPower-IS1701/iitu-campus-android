package kz.iitu.campus.ui.utils

sealed class LoadingState {
    object Idle : LoadingState()
    object Loading : LoadingState()
}