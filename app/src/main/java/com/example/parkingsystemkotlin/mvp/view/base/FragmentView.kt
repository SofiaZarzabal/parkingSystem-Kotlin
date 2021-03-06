package com.example.parkingsystemkotlin.mvp.view.base

import android.content.Context
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

open class FragmentView(fragment: Fragment) {
    private val fragmentRef: WeakReference<Fragment> = WeakReference(fragment)

    val fragment: Fragment?
        get() = fragmentRef.get()

    val context: Context?
        get() = fragment?.context
}
