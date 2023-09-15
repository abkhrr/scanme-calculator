package com.abkhrr.libs.camera

import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CameraOverlayView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val borderPaint: Paint = Paint()
    private val borderRect: RectF = RectF()
    private val path: Path = Path()
    private val blurPaint: Paint = Paint()

    private val blurRadius = 5f // Adjust the blur radius for the outside area
    private val borderRadius = 20f // Adjust the border radius as needed

    // Preallocate the Xfermode objects
    private val dstOutXfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    private val clearXfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

    init {
        borderPaint.apply {
            color = Color.YELLOW // Set the border color
            style = Paint.Style.STROKE
            strokeWidth = 2f // Adjust the border width as needed
        }

        blurPaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Calculate the coordinates for the square border
        val centerX = width / 2f
        val centerY = height / 2f
        val squareSize = minOf(width, height) * 0.6f // Adjust the size of the square

        // Reuse the existing RectF for the border rectangle
        borderRect.set(
            centerX - squareSize / 2,
            centerY - squareSize / 2,
            centerX + squareSize / 2,
            centerY + squareSize / 2
        )

        // Draw the rounded square border in the center of the view
        canvas.drawRoundRect(borderRect, borderRadius, borderRadius, borderPaint)

        // Reuse the existing Path for calculations
        path.reset()
        path.addRect(0f, 0f, width.toFloat(), height.toFloat(), Path.Direction.CW)
        path.addRoundRect(borderRect, borderRadius, borderRadius, Path.Direction.CW)

        // Apply a subtle blur effect to the area outside the rounded square border
        blurPaint.xfermode = dstOutXfermode
        canvas.drawPath(path, blurPaint)

        // Make the inside of the border transparent
        borderPaint.color = Color.TRANSPARENT
        borderPaint.xfermode = clearXfermode
        canvas.drawRoundRect(borderRect, borderRadius, borderRadius, borderPaint)
    }
}


