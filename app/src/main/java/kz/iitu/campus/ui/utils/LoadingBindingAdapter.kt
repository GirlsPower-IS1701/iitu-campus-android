package kz.iitu.campus.ui.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("loadingState")
fun handleLoadingState(view: View, event: LoadingState?) {
    event?.let { loadingState ->
        when (loadingState) {
            is LoadingState.Loading -> {
                view.visibility = View.VISIBLE
            }
//            is LoadingState.Error -> {
//                val parent = view.parent
//                if (parent is CoordinatorLayout) {
//                    showSnackbar(parent, loadingState.errorMessage)
//                }
//                view.visibility = View.GONE
//            }
            is LoadingState.Idle -> {
                view.visibility = View.GONE
            }
        }
    }
}
