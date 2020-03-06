package com.koidev.stack_overflow_stars.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.koidev.stack_overflow_stars.StackOverApplication
import com.koidev.stack_overflow_stars.di.common.ComponentManager
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

fun Activity.toast(
    message: CharSequence,
    duration: Int = Toast.LENGTH_SHORT,
    gravity: Int? = null
) {
    if (gravity != null) {
        Toast.makeText(this, message, duration)
            .apply { setGravity(gravity, 0, 100) }
            .show()
    } else {
        Toast.makeText(this, message, duration)
            .show()
    }
}

fun Fragment.snackBar(
    parent: View,
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(parent, message, duration).show()
}

fun Activity.componentManager(): ComponentManager {
    return (this.application as StackOverApplication).getComponentManager()
}

fun Activity.hideKeyboard(focus: View) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        hideSoftInputFromWindow(focus.windowToken, 0)
    }
}

fun Activity.showKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}

fun <T> List<T>.swap(first: Int, second: Int): List<T> = this.toMutableList()
    .also { newList ->
        newList[first] = this[second]
        newList[second] = this[first]
    }

fun Fragment.componentManager(): ComponentManager {
    return (activity as AppCompatActivity).componentManager()
}

fun <T> Observable<T>.filterRapidClicks(millis: Long = 350) =
    throttleFirst(millis, TimeUnit.MILLISECONDS).compose(ObserveOnMainTransformer())

fun <T> Observable<T>.skipTyping(millis: Long = 350) =
    debounce(millis, TimeUnit.MILLISECONDS).compose(ObserveOnMainTransformer())


/**
 * Sets view and all it's children to enabled/disabled state.
 *
 * @param enabled false if this view and it's children have to be disabled, true otherwise
 */
fun View.setEnabledWithChildren(enabled: Boolean) {
    isEnabled = enabled

    if (this !is ViewGroup) {
        return
    }

    for (idx in 0 until childCount) {
        getChildAt(idx).setEnabledWithChildren(enabled)
    }
}

/**
 *  Inflate extension for recyclerview adapters
 */
fun ViewGroup.inflate(layoutId: Int, attachToParent: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToParent)


/**
 * Transformer that observes on the main thread.
 */
private class ObserveOnMainTransformer<T> : ObservableTransformer<T, T> {
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.observeOn(AndroidSchedulers.mainThread())
    }

}
