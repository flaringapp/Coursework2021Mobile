package com.flaringapp.coursework2021.presentation.utils.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

open class DividerItemDecoration(
    private val drawable: Drawable,
    spacing: Int = 0,
    private val paddingLeft: Int? = null,
    private val paddingRight: Int? = null,
) : SpacingItemDecoration(spacing) {

    constructor(
        context: Context,
        @DrawableRes drawableRes: Int,
        spacing: Int = 0,
        drawableLeft: Int? = null,
        drawableRight: Int? = null,
    ) : this(
        ContextCompat.getDrawable(context, drawableRes)!!,
        spacing,
        drawableLeft,
        drawableRight
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildLayoutPosition(view) != 0) {
            outRect.top += drawable.intrinsicHeight
        }
    }

    override fun onDraw(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val dividerLeft = paddingLeft ?: parent.paddingLeft
        val dividerRight = parent.width - (paddingRight ?: parent.paddingRight)
        val childCount = parent.childCount

        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom: Int = dividerTop + drawable.intrinsicHeight

            drawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)

            drawable.draw(canvas)
        }
    }
}