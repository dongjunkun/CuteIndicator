package com.djk.library

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout


class CuteIndicator : LinearLayout {

    private var itemCount = 0

    private var selectedWidth = 0f

    private var dia = 0f

    private var space = 0f

    private var shadowRadius = 0f

    private var rectf = RectF()

    lateinit var paint: Paint

    private var lastPositionOffset = 0f

    private var firstVisiblePosition = 0

    private var indicatorColor = 0xffffffff

    private var shadowColor = 0x88000000

    private var isAnimation = true

    private var isShadow = true


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

        //默认值
        selectedWidth = dp2px(20f)
        dia = dp2px(10f)
        space = dp2px(5f)
        shadowRadius = dp2px(2f)

        setWillNotDraw(false)

        // Load attributes
        val a = context.obtainStyledAttributes(
                attrs, R.styleable.CuteIndicator, defStyle, 0)

        indicatorColor = a.getColor(R.styleable.CuteIndicator_IndicatorColor, indicatorColor.toInt()).toLong()
        shadowColor = a.getColor(R.styleable.CuteIndicator_IndicatorShadowColor, shadowColor.toInt()).toLong()
        selectedWidth = a.getDimension(R.styleable.CuteIndicator_IndicatorSelectedWidthDimension, selectedWidth)
        dia = a.getDimension(R.styleable.CuteIndicator_IndicatorDiaDimension, dia)
        space = a.getDimension(R.styleable.CuteIndicator_IndicatorSpaceDimension, space)
        shadowRadius = a.getDimension(R.styleable.CuteIndicator_IndicatorShadowRadiusDimension, shadowRadius)
        isAnimation = a.getBoolean(R.styleable.CuteIndicator_IndicatorIsAnimation, isAnimation)
        isShadow = a.getBoolean(R.styleable.CuteIndicator_IndicatorIsShadow, isShadow)

        a.recycle()

        if (isShadow)
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        paint = Paint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            color = indicatorColor.toInt()
            style = Paint.Style.FILL
            if (isShadow)
                setShadowLayer(shadowRadius, shadowRadius / 2, shadowRadius / 2, shadowColor.toInt())
        }

    }


    fun setupWithViewPager(viewPager: ViewPager) {
        if (viewPager.adapter == null) {
            throw IllegalArgumentException("viewPager adapter not be null")
        }

        itemCount = viewPager.adapter!!.count

        if (isShadow) {
            layoutParams.width = ((itemCount - 1) * (space + dia) + selectedWidth + shadowRadius).toInt()
            layoutParams.height = (dia + shadowRadius).toInt()
        } else {
            layoutParams.width = ((itemCount - 1) * (space + dia) + selectedWidth).toInt()
            layoutParams.height = dia.toInt()
        }

        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (isAnimation) {
                    firstVisiblePosition = position
                    lastPositionOffset = positionOffset
                    invalidate()
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (isAnimation.not()) {
                    firstVisiblePosition = position
                    invalidate()
                }
            }

        })

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isInEditMode || itemCount == 0) {
            return
        }

        for (i in 0 until itemCount) {
            var left: Float
            var right: Float

            if (i < firstVisiblePosition) {
                left = i * (dia + space)
                right = left + dia
            } else if (i == firstVisiblePosition) {
                left = i * (dia + space)
                right = left + dia + (selectedWidth - dia) * (1 - lastPositionOffset)
            } else if (i == firstVisiblePosition + 1) {
                left = (i - 1) * (space + dia) + dia + (selectedWidth - dia) * (1 - lastPositionOffset) + space
                right = i * (space + dia) + selectedWidth
            } else {
                left = (i - 1) * (dia + space) + (selectedWidth + space)
                right = (i - 1) * (dia + space) + (selectedWidth + space) + dia
            }

            val top = 0f
            val bottom = dia

            rectf.left = left
            rectf.top = top
            rectf.right = right
            rectf.bottom = bottom

            canvas.drawRoundRect(rectf, dia / 2, dia / 2, paint)

        }

    }

    fun dp2px(dpValue: Float): Float {
        return dpValue * context.resources.displayMetrics.density
    }
}
