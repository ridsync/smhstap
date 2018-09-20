package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst
import com.rasset.shmstab.ui.dialog.BaseDialogFragment
import com.rasset.shmstab.ui.dialog.SearchAddressDialog
import com.rasset.shmstab.utils.hideIME
import kotlinx.android.synthetic.main.fragment_diag_survey_asset_sell.*
import java.util.*

/**
 * Created by devok on 2018-09-05.
 */

class DiagSurveyAssetSellFragment : BaseFragment() {

    private object Holder { val INSTANCE = DiagSurveyAssetSellFragment() }

    companion object {
        val singleTone: DiagSurveyAssetSellFragment by lazy { Holder.INSTANCE }

        val instance: DiagSurveyAssetSellFragment by lazy { DiagSurveyAssetSellFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagSurveyAssetSellFragment::class.java)
            return intent
        }
    }

    var mPurchasedYear:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_survey_asset_sell, container, false)
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

        ET_LOCATION_ADDRESS_DETAIL.setOnEditorActionListener { v, actionId, event ->
            if (v?.id === ET_LOCATION_ADDRESS_DETAIL.id && actionId === EditorInfo.IME_ACTION_NEXT) {
                hideIME(mContext,ET_LOCATION_ADDRESS_DETAIL)
            }
            false
        }

        BTN_SEARCH_ADDRESS_SHOW.setOnClickListener {
            showDialog()
        }

        initSpinners()
    }

    private fun showDialog(){

        val dialog = SearchAddressDialog.newInstance(mContext).apply {
            setOnDismissListener(object : BaseDialogFragment.OnDismissListener{
                override fun onDismiss(dialog: BaseDialogFragment) {
                    val result = dialog as SearchAddressDialog
                    result.mSelectedAddress?.let {
                        this@DiagSurveyAssetSellFragment.TV_LOCATION_ADDRESS.text = result.mSelectedAddress
                    }
                }
            })
        }
        dialog.show(fragmentManager, AppConst.DIALOG_CUSTOMER_INFO_PRIVACY)
    }

    private fun initSpinners() {

        /* 스피너 디폴트 텍스트 고민좀 해보자 */
        // ++ BORN ++
        val maxAge = 50
        val minAge = 0
        val cal = Calendar.getInstance()
        val nowYear = cal.get(Calendar.YEAR)
        val minYear = nowYear - minAge
        val rangeAge = maxAge - minAge

        val bornSpinnerAdapter = object : ArrayAdapter<String>(mContext, R.layout.item_spinner) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val row = inflater.inflate(R.layout.item_spinner, parent, false)
                val label = row.findViewById(android.R.id.text1) as TextView
//                label.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
                if (position == 0) {
                    label.text = "선택"
//                    label.setTextColor(resources.getColor(R.color.color_black_12))
                } else if (position < count) {
//                    label.setTextColor(resources.getColor(R.color.color_black_87))
                    label.text = getItem(position) + "년"
                }

                return row
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View? {
                var v: View? = null
                if (position == 0) {
                    val tv = TextView(context)
                    tv.height = 0
                    tv.visibility = View.GONE
                    v = tv
                } else {
                    v = super.getDropDownView(position, null, parent)
                }
                parent.isVerticalScrollBarEnabled = false
                return v
            }
        }

        val arrYear = arrayOfNulls<String>(rangeAge)
        for (i in 0 until rangeAge) {
            arrYear[i] = (minYear - i).toString() + ""
        }

        // index 0
        bornSpinnerAdapter.add("년도선택")
        for (i in arrYear.indices) {
            bornSpinnerAdapter.add(arrYear[i])
        }

        bornSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        SPN_CONSULTING_DATE_YMD.adapter = bornSpinnerAdapter
        SPN_CONSULTING_DATE_YMD.onItemSelectedListener = mItemSelectedListener
    }

    private val mItemSelectedListener = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            if (position == 0) return
            mPurchasedYear = parent.getItemAtPosition(position).toString()
        }

        override fun onNothingSelected(parent: AdapterView<*>) {

        }
    }

}