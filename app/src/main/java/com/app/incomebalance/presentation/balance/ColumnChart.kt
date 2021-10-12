package com.app.incomebalance.presentation.balance

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.app.incomebalance.R
import com.app.incomebalance.domain.model.Balance
import kotlin.math.max

class ColumnChart(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var leftRect: RectF = RectF()
    private var rightRect: RectF = RectF()
    private var offset = 28 * resources.displayMetrics.density
    private val textMargin = 58 * resources.displayMetrics.density
    private val incomeColor = context.getColor(R.color.incomeChart)
    private val outcomeColor = context.getColor(R.color.outcomeChart)
    private var income = 0.0
    private var outcome = 0.0
    private var balance: Double = 0.0
    private var maxValue = 0.0

    private var incomeChartPaint = Paint().apply {
        color = incomeColor
        style = Paint.Style.FILL
    }

    private var outcomeChartPaint = Paint().apply {
        color = outcomeColor
        style = Paint.Style.FILL
    }

    fun update(balance: Balance) {
        this.income = balance.income
        this.outcome = balance.outcome
        this.balance = balance.balance
        maxValue = max(income, outcome)

        createShapes()
        invalidate()
        requestLayout()
    }

    private fun createShapes() {
        val heightToValue = height / maxValue
        leftRect = RectF(
            left + (textMargin - offset),
            (height - heightToValue * income).toFloat(),
            (width/2 - offset),
            height.toFloat(),
        )

        rightRect = RectF(
            width/2 + offset,
            (height - heightToValue * outcome).toFloat(),
            right - (textMargin - offset/2),
            height.toFloat()
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawRect(
                leftRect,
                incomeChartPaint
            )

            drawRect(
                rightRect,
                outcomeChartPaint
            )
        }
    }
}