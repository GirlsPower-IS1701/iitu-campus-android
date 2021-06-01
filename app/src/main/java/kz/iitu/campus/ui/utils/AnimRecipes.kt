package kz.iitu.campus.ui.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kz.iitu.campus.R

object AnimRecipes {
    private const val collapseExpandDuration = 200
    fun collapseExpand(rotatableButton: View, expandingView: View) {
        collapseExpandWithSwap(rotatableButton, expandingView, null)
    }

    fun collapseExpandWithTransition(rotatableButton: View, expandingView: View) {
        val expand = expandingView.visibility == View.GONE
        if (expand) expandWithTransition(
            rotatableButton,
            expandingView
        ) else collapseWithTransition(rotatableButton, expandingView)
    }

    fun collapseWithTransition(rotatableButton: View, expandingView: View) {
        collapseWithAnimation(expandingView)
        rotateUp(rotatableButton)
    }

    fun expandWithTransition(rotatableButton: View, expandingView: View) {
        expandWithAnimation(expandingView)
        rotateDown(rotatableButton)
    }

    private fun toggleRotatableButton(expand: Boolean, rotatableButton: View?) {
        if (rotatableButton == null) return
        if (expand) rotateDown(rotatableButton) else rotateUp(rotatableButton)
    }

    fun collapseWithAnimation(rotatableButton: View, expandingView: View) {
        if (expandingView.visibility == View.GONE) {
            return
        }
        rotateUp(rotatableButton)
        collapseWithAnimation(expandingView)
    }

    fun expandWithAnimation(rotatableButton: View, expandingView: View) {
        if (expandingView.visibility == View.VISIBLE) {
            return
        }
        rotateDown(rotatableButton)
        expandWithAnimation(expandingView)
    }

    fun collapseExpandWithSwap(rotatableButton: View, expandingView: View, swappingView: View?) {
        when (expandingView.visibility) {
            View.VISIBLE -> hideWithSwap(rotatableButton, expandingView, swappingView)
            View.GONE -> showWithSwap(rotatableButton, expandingView, swappingView)
            View.INVISIBLE -> {
            }
        }
    }

    private fun hideWithSwap(rotatableButton: View, expandingView: View, swappingView: View?) {
        fadeOutWithSwap(expandingView, swappingView)
        rotateUp(rotatableButton)
    }

    private fun showWithSwap(rotatableButton: View, expandingView: View, swappingView: View?) {
        fadeInWithSwap(expandingView, swappingView)
        rotateDown(rotatableButton)
    }

    fun swapViews(swappedView: View?, swappingView: View?) {
        checkAndSetVisibility(swappedView)
        checkAndSetVisibility(swappingView)
    }

    private fun fadeOutWithSwap(fadingView: View, swappingView: View?) {
        val listener: Animation.AnimationListener =
            VisibilityPostAnimation(fadingView, swappingView)
        startAnimation(fadingView, R.anim.fade_out, listener)
    }

    private fun fadeInWithSwap(fadingView: View, swappingView: View?) {
        swapViews(fadingView, swappingView)
        startAnimation(fadingView, R.anim.fade_in)
    }

    fun rotateDown(View: View) {
        startAnimation(View, R.anim.rotate_down)
    }

    fun rotateUp(View: View) {
        startAnimation(View, R.anim.rotate_up)
    }

    fun rotateByDegrees(view: View?, degrees: Float) {
        ViewCompat.animate(view!!)
            .rotation(degrees)
            .withLayer()
            .setDuration(300L)
            .setInterpolator(OvershootInterpolator())
            .start()
    }

    private fun startAnimation(
        view: View,
        animationResId: Int,
        listener: Animation.AnimationListener
    ) {
        startAnimation(view, animationResId, collapseExpandDuration, listener)
    }

    private fun startAnimation(
        view: View,
        animationResId: Int,
        duration: Int = collapseExpandDuration,
        listener: Animation.AnimationListener? = null
    ) {
        val animation =
            AnimationUtils.loadAnimation(view.context, animationResId)
                ?: return
        animation.setAnimationListener(listener)
        animation.duration = duration.toLong()
        animation.fillAfter = true
        view.startAnimation(animation)
    }

    fun checkAndSetVisibility(view: View?) {
        if (view == null) {
            return
        }
        if (view is ViewGroup) {
            val viewGroup = view
            for (childIndex in 0 until viewGroup.childCount) {
                setReverseVisibility(viewGroup.getChildAt(childIndex))
            }
        }
        setReverseVisibility(view)
    }

    private fun setReverseVisibility(view: View?) {
        if (view == null) return
        when (view.visibility) {
            View.GONE, View.INVISIBLE -> {
                view.visibility = View.VISIBLE
                return
            }
            View.VISIBLE -> view.visibility = View.GONE
        }
    }

    fun graduallyChangeColorOfTint(
        imageView: ImageView,
        colorFrom: Int,
        colorTo: Int,
        listener: Animator.AnimatorListener?
    ) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 150 // milliseconds
        colorAnimation.addUpdateListener { animation: ValueAnimator ->
            imageView.setColorFilter(
                animation.animatedValue as Int,
                PorterDuff.Mode.SRC_IN
            )
        }
        colorAnimation.addListener(listener)
        colorAnimation.start()
    }

    fun animateIconChange(imageView: ImageView, drawable: Int) {
        val colorFrom = imageView.resources.getColor(R.color.white)
        val colorTo = imageView.resources.getColor(R.color.white)
        graduallyChangeColorOfTint(
            imageView,
            colorFrom,
            colorTo,
            object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    imageView.setImageResource(drawable)
                    graduallyChangeColorOfTint(
                        imageView,
                        colorTo,
                        colorFrom,
                        object : AnimatorListenerAdapter() {})
                }
            })
    }

    fun showHideFabTitle(isExpanded: Boolean, vararg textViews: TextView) {
        if (textViews.size < 1) return
        val context = textViews[0].context
        val hideShowAnim = getShowHideAnim(context, isExpanded)
        for (title in textViews) title.startAnimation(hideShowAnim)
    }

    @SuppressLint("RestrictedApi")
    fun expandCollapseFab(
        expandableFab: FloatingActionButton,
        isExpanded: Boolean, openIcon: Int, closeIcon: Int,
        vararg fabs: FloatingActionButton
    ) {
        if (fabs.size < 1) return
        animateIconChange(expandableFab, if (!isExpanded) closeIcon else openIcon)
        val context = expandableFab.context
        val hideShowAnim = getShowHideAnim(context, isExpanded)
        for (fabIterator in fabs) {
            fabIterator.startAnimation(hideShowAnim)
            fabIterator.isClickable = !isExpanded
            fabIterator.visibility = if (!isExpanded) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun getShowHideAnim(context: Context, isExpanded: Boolean): Animation {
        val showAnimId: Int = R.anim.fade_in_with_scale
        val hideAnimId: Int = R.anim.fade_out_with_scale
        return AnimationUtils.loadAnimation(context, if (isExpanded) hideAnimId else showAnimId)
    }

    fun expandWithAnimation(view: View) {
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetHeight = view.measuredHeight

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        view.layoutParams.height = 1
        view.visibility = View.VISIBLE
        val customAnimation: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                view.layoutParams.height =
                    if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
                view.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        customAnimation.setDuration(
            (targetHeight / view.context.resources.displayMetrics.density) as Long
        )
        view.animation = customAnimation
        customAnimation.start()
    }

    fun collapseWithAnimation(view: View) {
        val initialHeight = view.measuredHeight
        val custonAnimation: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    view.visibility = View.GONE
                } else {
                    view.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        custonAnimation.setDuration(
            (initialHeight / view.context.resources.displayMetrics.density) as Long
        )
        view.startAnimation(custonAnimation)
    }

    fun toggleWithAnimation(expand: Boolean, v: View) {
        if (expand) expandWithAnimation(v) else collapseWithAnimation(v)
    }

    internal class VisibilityPostAnimation(
        private val expandingView: View,
        private val swappingView: View?
    ) :
        Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {
            checkAndSetVisibility(expandingView)
            checkAndSetVisibility(swappingView)
        }

        override fun onAnimationRepeat(animation: Animation) {}
    }
}