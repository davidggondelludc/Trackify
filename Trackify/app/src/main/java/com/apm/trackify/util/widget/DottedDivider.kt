package com.apm.trackify.util.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.apm.trackify.R

class DottedDivider @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        setBackgroundResource(R.drawable.dotted_line)
        backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        alpha = 0.1f
    }
}