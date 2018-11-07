package com.rasset.shmstab.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.RectF
import android.graphics.Typeface
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
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.rasset.shmstab.model.ResultInfo
import com.rasset.shmstab.ui.DiagRichSurveyActivity
import kotlinx.android.synthetic.main.fragment_diag_rich_result.*
import java.util.*

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

    var resultInfo: ResultInfo? = null

    enum class ResultSurvType(var type:Int, var title:Int,var desc:Int, var ability:Int,var strategy:Int, var advice:Int){
        RESULT_TYPE_SUCCESS(1,R.string.rich_result_title1,R.string.rich_result_desc1,R.string.rich_result_ability1,R.string.rich_result_stratgey1,R.string.rich_result_advice1),
        RESULT_TYPE_POSSIBLE(2,R.string.rich_result_title2,R.string.rich_result_desc2,R.string.rich_result_ability2,R.string.rich_result_stratgey2,R.string.rich_result_advice2),
        RESULT_TYPE_EFFORT(3,R.string.rich_result_title3,R.string.rich_result_desc3,R.string.rich_result_ability3,R.string.rich_result_stratgey3,R.string.rich_result_advice3),
        RESULT_TYPE_SERIOUS(4,R.string.rich_result_title4,R.string.rich_result_desc4,R.string.rich_result_ability4,R.string.rich_result_stratgey4,R.string.rich_result_advice4)
    }

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

       if (activity is DiagRichSurveyActivity){
           val act = activity as DiagRichSurveyActivity
           this.resultInfo = act.resultInfo
       }
        setBarChart()
        setViewsResultType()
    }

    private fun setViewsResultType() {
        resultInfo?.let {
            var enResult = when (it.resultRichType) {
                ResultSurvType.RESULT_TYPE_SUCCESS.type -> ResultSurvType.RESULT_TYPE_SUCCESS
                ResultSurvType.RESULT_TYPE_POSSIBLE.type -> ResultSurvType.RESULT_TYPE_POSSIBLE
                ResultSurvType.RESULT_TYPE_EFFORT.type -> ResultSurvType.RESULT_TYPE_EFFORT
                ResultSurvType.RESULT_TYPE_SERIOUS.type -> ResultSurvType.RESULT_TYPE_SERIOUS
                else -> ResultSurvType.RESULT_TYPE_SUCCESS
            }

            var strTitle = getString(enResult.title)
            var spanTitle = SpannableString(strTitle)
            spanTitle.setSpan(ForegroundColorSpan(resources.getColor(R.color.main_accent_color)), 4, strTitle.indexOf("형")+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spanTitle.setSpan(StyleSpan(Typeface.BOLD), 4, strTitle.indexOf("형")+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); // make first 4 characters Bold

            TV_RESULT_TYPE_TITLE.text = spanTitle
            TV_RESULT_DESC.setText(enResult.desc)
            TV_RESULT_ABILITY.setText(enResult.ability)
            TV_RESULT_STRATEGY.setText(enResult.strategy)
            TV_RESULT_ADVICE.setText(enResult.advice)
        }

    }

    private fun setBarChart() {
        barChart = MP_BARCHART

        barChart.animateXY(1200,1700,Easing.EasingOption.Linear,Easing.EasingOption.EaseInOutQuad)
        barChart.setOnChartValueSelectedListener(this)
        barChart.setDrawBarShadow(false)
        barChart.setDrawValueAboveBar(true)
        barChart.description.isEnabled = false

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(10)

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false)
        barChart.isDoubleTapToZoomEnabled = false
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

        setData()


    }

    private fun setData() {

        val yVals1 = ArrayList<BarEntry>()

        resultInfo?.let {
            yVals1.add(BarEntry(0f, it.firstScore.toFloat()))
            yVals1.add(BarEntry(1f, it.secondScore.toFloat()))
            yVals1.add(BarEntry(2f, it.thirdScore.toFloat()))
            yVals1.add(BarEntry(3f, it.fourthScore.toFloat()))
            yVals1.add(BarEntry(4f, it.fifthScore.toFloat()))
        }

        val set1: BarDataSet

        if (barChart.data != null && barChart.data.dataSetCount > 0) {
            set1 = barChart.data.getDataSetByIndex(0) as BarDataSet
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
            val formatter = DefaultValueFormatter(0)
            data.setValueFormatter(formatter)
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