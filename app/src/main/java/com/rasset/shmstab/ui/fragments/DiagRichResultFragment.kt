package com.rasset.shmstab.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.RectF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.rasset.shmstab.R
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.rasset.shmstab.ui.components.DayAxisValueFormatter
import com.rasset.shmstab.ui.components.MyAxisValueFormatter
import com.rasset.shmstab.ui.components.XYMarkerView
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import android.support.v4.content.ContextCompat
import android.util.Log
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.fragment_diag_rich_result.*

/**
 * Created by devok on 2018-09-05.
 */

class DiagRichResultFragment : BaseFragment() , OnChartValueSelectedListener {

    private object Holder { val INSTANCE = DiagRichResultFragment() }

    companion object {
        val singleTone: DiagRichResultFragment by lazy { Holder.INSTANCE }

        val instance: DiagRichResultFragment by lazy { DiagRichResultFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagRichResultFragment::class.java)
            return intent
        }
    }

    lateinit var barChart: BarChart
    val chartXaxis = arrayOf("", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_rich_result, container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isFirstInit){
            isFirstInit = true
            initFirst()
        }

    }

    override fun onStop() {
        super.onStop()
    }

    fun initFirst(){
            setBarChart()
    }

    private fun setBarChart() {
        barChart = MP_BARCHART

        barChart.animateXY(1200,1700,Easing.EasingOption.EaseInCubic,Easing.EasingOption.Linear)
        barChart.setOnChartValueSelectedListener(this)
        barChart.setDrawBarShadow(false)
        barChart.setDrawValueAboveBar(true)
        barChart.description.isEnabled = false

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(10)

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false)
        barChart.clipToPadding = false
        barChart.extraBottomOffset = 30f
        barChart.extraTopOffset = 30f

        barChart.setDrawGridBackground(false)
        // barChart.setDrawYLabels(false);

        val xAxisFormatter = DayAxisValueFormatter(barChart)

        val xAxis = barChart.xAxis
        xAxis.position = XAxisPosition.BOTTOM
//        xAxis.setTypeface(mTfLight)
        xAxis.setDrawGridLines(false)
        xAxis.yOffset = 20f
        xAxis.granularity = 1f // only intervals of 1 day
        xAxis.labelCount = 5
        xAxis.valueFormatter = xAxisFormatter

        val custom = MyAxisValueFormatter()

        val leftAxis = barChart.axisLeft
//        leftAxis.setTypeface(mTfLight)
        leftAxis.setLabelCount(8, false)
        leftAxis.valueFormatter = custom
        leftAxis.granularity = 20f
        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 95f
        leftAxis.axisMaximum = 100f
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val rightAxis = barChart.axisRight
        rightAxis.setDrawGridLines(false)
//        rightAxis.setTypeface(mTfLight)
        rightAxis.setLabelCount(8, false)
        rightAxis.valueFormatter = custom
        rightAxis.spaceTop = 15f
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
        rightAxis.isEnabled = false

        barChart.legend.isEnabled = false
        val l = barChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form = LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f
        // l.setExtra(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });
        // l.setCustom(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });

//        val mv = XYMarkerView(mContext, xAxisFormatter)
//        mv.chartView = barChart // For bounds control
//        barChart.marker = mv // Set the marker to the chart

        setData(5, 100f)


    }

    private fun setData(count: Int, range: Float) {

        val start = 0f

        val yVals1 = ArrayList<BarEntry>()

        var i = start.toInt()
        while (i < start + count.toFloat()) {
            val mult = range
            val `val` = (Math.random() * mult).toFloat()

            if (Math.random() * 100 < 25) {
                yVals1.add(BarEntry(i.toFloat(), `val`, resources.getDrawable(R.drawable.star)))
            } else {
                yVals1.add(BarEntry(i.toFloat(), `val`.toInt().toFloat()))
            }
            i++
        }

        val set1: BarDataSet

        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            set1 = barChart.getData().getDataSetByIndex(0) as BarDataSet
            set1.values = yVals1
            barChart.data.notifyDataChanged()
            barChart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "")

            set1.setDrawIcons(false)

//            set1.colors =ColorTemplate.MATERIAL_COLORS.asList()

            /*int startColor = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor = ContextCompat.getColor(this, android.R.color.holo_blue_bright);
            set1.setGradientColor(startColor, endColor);*/

            val startColor1 = ContextCompat.getColor(mContext, R.color.chart_yellow_light)
            val startColor2 = ContextCompat.getColor(mContext, R.color.chart_green_light)
            val startColor3 = ContextCompat.getColor(mContext, R.color.chart_blue_light)
            val startColor4 = ContextCompat.getColor(mContext, R.color.chart_indigo_light)
            val startColor5 = ContextCompat.getColor(mContext,R.color.chart_purple_dark)

            set1.resetColors()
            set1.addColor(startColor1)
            set1.addColor(startColor2)
            set1.addColor(startColor3)
            set1.addColor(startColor4)
            set1.addColor(startColor5)

            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            val data = BarData(dataSets)
            data.setValueTextSize(17f)
//            data.setValueTypeface(mTfLight)
            data.barWidth = 0.7f

            barChart.data = data
        }
    }


    private var mOnValueSelectedRectF = RectF()

    @SuppressLint("NewApi")
    override fun onValueSelected(e:Entry?, h:Highlight) {
        if (e == null)
        return

        val bounds = mOnValueSelectedRectF
        barChart.getBarBounds(e as BarEntry?, bounds)
        val position = barChart.getPosition(e, YAxis.AxisDependency.LEFT)

        Log.i("bounds", bounds.toString())
        Log.i("position", position.toString())

        Log.i("x-index",
                "low: " + barChart.getLowestVisibleX() + ", high: "
                + barChart.getHighestVisibleX())

        MPPointF.recycleInstance(position)
    }

    override fun onNothingSelected() {
    }

}