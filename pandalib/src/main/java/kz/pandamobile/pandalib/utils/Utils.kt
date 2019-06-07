package kz.pandamobile.pandalib.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView

/**
 *   Created by Assylkhanov Aslan on 24.02.2019.
 */
class Utils {

    companion object {

        fun RGBtoIntArray(rgbHex: String): IntArray {
            var rgbHex = rgbHex
            rgbHex = rgbHex.substring(1)
            val r = hexToDecimal(rgbHex.substring(0, 2))
            val g = hexToDecimal(rgbHex.substring(2, 4))
            val b = hexToDecimal(rgbHex.substring(4, 6))
            return intArrayOf(r, g, b)
        }

        fun hexToDecimal(hex: String): Int {
            return Integer.parseInt(hex, 16)
        }


        fun hideSoftKeyboard(activity: Activity?) {
            try {
                activity?.let {
                    val inputMethodManager =
                        it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(
                        it.currentFocus!!.windowToken,
                        0
                    )
                }
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }

        }

        fun drawableToBitmap(drawable: Drawable, width: Int, height: Int): Bitmap {

            if (drawable is BitmapDrawable) {
                return drawable.bitmap
            }

            val bitmap =
                Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
            drawable.draw(canvas)

            return bitmap
        }

        fun setUnderline(textView: TextView) {
            textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }

        fun removeUnderline(textView: TextView) {
            textView.paintFlags = 0
        }

        /**
         * Display height in pixels
         */
        fun getDisplayHeight (context: Context) : Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size.y
        }

        /**
         * Display width in pixels
         */
        fun getDisplayWidth (context: Context) : Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size.x
        }

        /**
         * dip to px
         */
        fun dip2px(context: Context, dp: Float): Float {
            return dp * context.resources.displayMetrics.density
        }

        /**
         * px to dp
         */
        fun px2dip(context: Context, px: Float): Float {
            return px / context.resources.displayMetrics.density
        }

    }

}