package com.example.shymko_hw_lesson21_2901021_customview

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View


class RegulatorView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        private const val DEFAULT_BACKGROUND_COLOR = Color.BLUE
        private const val DEFAULT_DOT_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val STAGE0 = 0L
        const val STAGE1 = 1L
        const val STAGE2 = 2L

    }

    private var backgroundColor1 = DEFAULT_BACKGROUND_COLOR
    private var dotColor = DEFAULT_DOT_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH

    private val paint = Paint()
    private var size = 0

    var stage = STAGE0
        set(state) {
            field = state
            invalidate()
        }

    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        // Obtain a typed array of attributes
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.RegulatorAttrs,
            0, 0
        )

        // Extract custom attributes into member variables
        stage =
            typedArray.getInt(R.styleable.RegulatorAttrs_state, STAGE0.toInt()).toLong()
        backgroundColor1 = typedArray.getColor(R.styleable.RegulatorAttrs_faceColor, DEFAULT_BACKGROUND_COLOR)
        dotColor = typedArray.getColor(R.styleable.RegulatorAttrs_eyesColor, DEFAULT_DOT_COLOR)
        borderWidth = typedArray.getDimension(
            R.styleable.RegulatorAttrs_borderWidth,
            DEFAULT_BORDER_WIDTH
        )

        // TypedArray objects are shared and must be recycled.
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        // call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)
        drawRegulatorBackground(canvas)
        drawRegulatorDot(canvas)
    }

    private fun drawRegulatorBackground(canvas: Canvas) {
        paint.color = backgroundColor1
        paint.style = Paint.Style.FILL

        val radius = size / 2f

        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        paint.color = backgroundColor1
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawRegulatorDot(canvas: Canvas) {
        paint.color = dotColor
        paint.style = Paint.Style.FILL

        if (stage== STAGE0) {
            val regDot = RectF(size * 0.1f, size * 0.45f, size * 0.2f, size * 0.55f)
            canvas.drawOval(regDot, paint)
        } else if (stage== STAGE1) {
            val regDot = RectF(size * 0.45f, size * 0.1f, size * 0.55f, size * 0.2f)
            canvas.drawOval(regDot, paint)
        } else if (stage== STAGE2) {
            val regDot = RectF(size * 0.8f, size * 0.45f, size * 0.9f, size * 0.55f)
            canvas.drawOval(regDot, paint)
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size =   400
        setMeasuredDimension(size, size)
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putLong("Stage", stage)
        bundle.putParcelable("superState", super.onSaveInstanceState())

        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var viewState = state
        if (viewState is Bundle) {
            stage = viewState.getLong("Stage1", STAGE0)
            viewState = viewState.getParcelable("superState")
        }

        super.onRestoreInstanceState(viewState)
    }
}