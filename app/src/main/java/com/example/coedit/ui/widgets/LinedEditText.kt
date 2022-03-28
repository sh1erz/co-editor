package com.example.coedit.ui.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class LinedEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    private var mRect: Rect = Rect()
    private var mPaint: Paint = Paint()

    init {
//        setText("\n".repeat(count - 1))
        mPaint.style = Paint.Style.FILL_AND_STROKE
        //todo note color
        mPaint.color = Color.GRAY

    }


    override fun onDraw(canvas: Canvas?) {
        val count = (height / lineHeight).let { if (lineCount > it) lineCount else it }
        val r = mRect
        val paint = mPaint
        var baseline = getLineBounds(0, r) //first line

        for (i in 0 until count) {
            canvas?.drawLine(
                r.left.toFloat(),
                baseline + 5f,
                r.right.toFloat(),
                baseline + 5f,
                paint
            )
            baseline += lineHeight //next line
        }
        super.onDraw(canvas)
    }

}