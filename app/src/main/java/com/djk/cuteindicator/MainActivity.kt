package com.djk.cuteindicator

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val colors = arrayListOf("#FF5252","#FF9800","#3F51B5","#7C4DFF","#009688","#795548")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = object : PagerAdapter() {

            override fun instantiateItem(container: ViewGroup, position: Int): Any {

                val view = TextView(this@MainActivity)
                view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT)
                view.setBackgroundColor(Color.parseColor(colors[position]))
                container.addView(view)

                return view

            }

            override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

            override fun getCount(): Int = 6

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(`object` as View?)
            }
        }

        indicator.setupWithViewPager(pager)


    }
}
