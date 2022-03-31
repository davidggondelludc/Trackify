package com.apm.trackify.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.math.MathUtils
import com.apm.trackify.util.extension.isDarkMode
import kotlin.math.abs

object CoverUtil {

    private val COLORS = listOf(
        intArrayOf(0xFF_00_C9_FF.toInt(), 0xFF_92_FE_9D.toInt()),
        intArrayOf(0xFF_F5_4E_A2.toInt(), 0xFF_FF_76_76.toInt()),
        intArrayOf(0xFF_17_EA_D9.toInt(), 0xFF_92_FE_9D.toInt()),
        intArrayOf(0xFF_7B_43_97.toInt(), 0xFF_DC_24_30.toInt()),
        intArrayOf(0xFF_1C_D8_D2.toInt(), 0xFF_93_ED_C7.toInt()),
        intArrayOf(0xFF_1F_86_EF.toInt(), 0xFF_56_41_DB.toInt()),
        intArrayOf(0xFF_F0_2F_C2.toInt(), 0xFF_60_94_EA.toInt()),
        intArrayOf(0xFF_00_D2_FF.toInt(), 0xFF_3A_7B_D5.toInt()),
        intArrayOf(0xFF_F8_57_A6.toInt(), 0xFF_FF_58_58.toInt()),
        intArrayOf(0xFF_AA_FF_A9.toInt(), 0xFF_11_FF_BD.toInt()),
        intArrayOf(0xFF_00_C6_FF.toInt(), 0xFF_00_72_FF.toInt()),
        intArrayOf(0xFF_43_CE_a2.toInt(), 0xFF_18_5A_9D.toInt()),
        intArrayOf(0xFF_B6_50_DB.toInt(), 0xFF_28_73_E1.toInt()),
        intArrayOf(0xFF_17_EA_d9.toInt(), 0xFF_60_98_EA.toInt()),
        intArrayOf(0xFF_38_EE_7e.toInt(), 0xFF_13_9C_8E.toInt()),
        intArrayOf(0xFF_38_CE_DC.toInt(), 0xFF_5A_89_E5.toInt()),
        intArrayOf(0xFF_15_85_CB.toInt(), 0xFF_2A_36_B3.toInt()),
        intArrayOf(0xFF_99_4F_BB.toInt(), 0xFF_30_34_b3.toInt()),
        intArrayOf(0xFF_83_00_FF.toInt(), 0xFF_DD_00_ff.toInt()),
        intArrayOf(0xFF_DF_26_74.toInt(), 0xFF_FE_4F_32.toInt()),
        intArrayOf(0xFF_84_04_81.toInt(), 0xFF_E2_60_92.toInt()),
        intArrayOf(0xFF_FF_60_62.toInt(), 0xFF_FF_96_66.toInt()),
        intArrayOf(0xFF_FC_4E_1B.toInt(), 0xFF_F8_B3_33.toInt()),
        intArrayOf(0xFF_F7_9F_32.toInt(), 0xFF_FC_CA_1C.toInt())
    )

    private val DESATURATED_COLORS = COLORS.map {
        val colors = it.copyOf()
        colors[0] = desaturate(colors[0])
        colors[1] = desaturate(colors[1])
        colors
    }

    fun getDrawable(context: Context, @DrawableRes id: Int, position: Int): Drawable {
        val drawable = ContextCompat.getDrawable(context, id)!!.mutate() as LayerDrawable
        val gradient = drawable.getDrawable(0) as GradientDrawable

        if (context.isDarkMode()) {
            gradient.colors = DESATURATED_COLORS[abs(position % DESATURATED_COLORS.size)]
        } else {
            gradient.colors = COLORS[abs(position % COLORS.size)]
        }

        return drawable
    }

    private fun desaturate(@ColorInt color: Int): Int {
        val alpha = Color.alpha(color)
        val colorWithFullAlpha = ColorUtils.setAlphaComponent(color, 255)

        val hsl = FloatArray(3)
        ColorUtils.colorToHSL(colorWithFullAlpha, hsl)
        if (hsl[1] > 0.75f) hsl[1] = MathUtils.clamp(hsl[1] - 0.25f, 0.75f, 1f)
        val desaturatedColorWithFullAlpha = ColorUtils.HSLToColor(hsl)

        return ColorUtils.setAlphaComponent(desaturatedColorWithFullAlpha, alpha)
    }
}