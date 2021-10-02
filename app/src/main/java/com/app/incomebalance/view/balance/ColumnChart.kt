package com.app.incomebalance.view.balance

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.app.incomebalance.R
import kotlin.math.max

class ColumnChart(context: Context,attrs: AttributeSet) : View(context,attrs) {
    private var v: Double = 0.0
    private var maxValue = 0.0
    private var leftRect: Rect? = null
    private var rightRect: Rect? = null
    private var offset = 28 * resources.displayMetrics.density
    val textMargin = 58 * resources.displayMetrics.density
    private var maxHeight = 0
    private val incomeColor = resources.getColor(R.color.incomeChart)
    private val outcomeColor = resources.getColor(R.color.outcomeChart)
    private var horizontalCenter = 0
    var income = 0.0
    var outcome = 0.0


    var incomeChartPaint = Paint().apply {
        color = incomeColor
        style = Paint.Style.FILL
    }

    private var outcomeChartPaint = Paint().apply {
        color = outcomeColor
        style = Paint.Style.FILL
    }

    init {
        if (isInEditMode) {
            income = 100.0
            outcome = 50.0
            update(income, outcome)
        }
    }

    private fun update(income: Double, outcome: Double) {
        this.income = income
        this.outcome = outcome
        maxValue = max(income, outcome)

        v = height / maxValue

        invalidate()
        requestLayout()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawRect(
                left + (textMargin - offset),
                (height - height * income / maxValue).toFloat(),
                (width / 2 - offset),
                height.toFloat(),
                incomeChartPaint
            )

            drawRect(
                width / 2 + offset,
                (height - height * outcome / maxValue).toFloat(),
                right - (textMargin - offset),
                height.toFloat(),
                outcomeChartPaint
            )
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        horizontalCenter = width / 2
    }
}