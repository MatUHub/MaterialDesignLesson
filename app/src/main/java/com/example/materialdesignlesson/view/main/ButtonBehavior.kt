package com.example.materialdesignlesson.view.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import java.lang.Math.abs

class ButtonBehavior (context: Context, attributeSet: AttributeSet): CoordinatorLayout.Behavior<View>(context, attributeSet) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        var bar = dependency as AppBarLayout
        val bary = abs(bar.y)
        val barHeight = bar.height/2
        if(bary>barHeight){
            child.visibility = View.GONE
        } else {
            child.visibility = View.VISIBLE
            val alpha = (barHeight - bary)/barHeight
            child.alpha = alpha
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}