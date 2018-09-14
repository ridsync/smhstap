package com.rasset.shmstab.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.AppCompatCheckBox
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.ui.LoginActivity
import com.rasset.shmstab.ui.components.RecursiveRadioGroup
import com.rasset.shmstab.ui.dialog.AlertSimpleDialog
import com.rasset.shmstab.ui.dialog.BaseDialogFragment
import com.rasset.shmstab.ui.dialog.MainCustomDialog
import com.rasset.shmstab.ui.dialog.PrivacyAgreeDialog
import com.rasset.shmstab.utils.*
import kotlinx.android.synthetic.main.fragment_diag_default_info.*
import java.util.*


/**
 * Created by devok on 2018-09-05.
 */

class DiagSubCustomerInfoFragment : BaseFragment() {

    private object Holder { val INSTANCE = DiagSubCustomerInfoFragment() }

    companion object {
        val singleTone: DiagSubCustomerInfoFragment by lazy { Holder.INSTANCE }

        val instance: DiagSubCustomerInfoFragment by lazy { DiagSubCustomerInfoFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagSubCustomerInfoFragment::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater?.inflate(R.layout.fragment_diag_default_info, container, false)
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

    lateinit var acbPrivacyAgree: AppCompatCheckBox
    fun initFirst(){
        acbPrivacyAgree = ACB_PRIVACY_AGREE
        RG_DIAG_FIELD.check(RB_DIAG_INVEST)

        RG_DIAG_FIELD.setOnCheckedChangeListener{ _: RecursiveRadioGroup, checkedId: Int ->

        }

        BTN_PRIVACY_SHOW.setOnClickListener {
            val dialog = PrivacyAgreeDialog.newInstance(mContext).apply {
                setOnDismissListener(object : BaseDialogFragment.OnDismissListener{
                    override fun onDismiss(dialog: BaseDialogFragment) {
                        acbPrivacyAgree.isChecked = true
                    }
                })
            }
            dialog.show(fragmentManager, AppConst.DIALOG_CUSTOMER_INFO_PRIVACY)
        }

        TV_CONSULTING_DATE_YMD.setOnClickListener {
            setDateYMD()
            hideIME(mContext,ET_CUSTOMER_PHONE)
        }
        TV_CONSULTING_DATE_START_HOUR.setOnClickListener {
            setDateTime()
            hideIME(mContext,ET_CUSTOMER_PHONE)
        }
        TV_CONSULTING_DATE_END_HOUR.setOnClickListener {
            setDateTime()
            hideIME(mContext,ET_CUSTOMER_PHONE)
        }
        ET_CUSTOMER_PHONE.addTextChangedListener(mContactWatcher)
    }

    private fun setDateYMD() {
        val onDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            val strBuf = StringBuffer()
            strBuf.append(year)
            strBuf.append("-")
            strBuf.append(month + 1)
            strBuf.append("-")
            strBuf.append(dayOfMonth)

            val datePickerValueTextView = TV_CONSULTING_DATE_YMD as TextView
            datePickerValueTextView.text = strBuf.toString()
        }

        // Get current year, month and day.
        val now = Calendar.getInstance()
        val year = now.get(java.util.Calendar.YEAR)
        val month = now.get(java.util.Calendar.MONTH)
        val day = now.get(java.util.Calendar.DAY_OF_MONTH)

        // Create the new DatePickerDialog instance.
        val datePickerDialog = DatePickerDialog(mContext, onDateSetListener, year, month, day)

        // Set dialog icon and title.
        datePickerDialog.setIcon(R.drawable.ic_check)
        datePickerDialog.setTitle("Please select date.")

        // Popup the dialog.
        datePickerDialog.show()
    }

    private fun setDateTime() {
        val onTimeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            val strBuf = StringBuffer()
            strBuf.append(if(hour > 9) hour else "0$hour")
            strBuf.append(":")
            strBuf.append(if(minute > 9) minute else "0$minute")

            val timePickerValueTextView = TV_CONSULTING_DATE_START_HOUR as TextView
            timePickerValueTextView.text = strBuf.toString()

            val strBufEnd = StringBuffer()
            val hourEnd = hour +1
            strBufEnd.append(if(hourEnd > 9) hourEnd else "0$hourEnd")
            strBufEnd.append(":")
            strBufEnd.append(if(minute > 9) minute else "0$minute")

            val timeEndTextView = TV_CONSULTING_DATE_END_HOUR as TextView
            timeEndTextView.text = strBufEnd.toString()
        }

        val now = Calendar.getInstance()
        val hour = now.get(java.util.Calendar.HOUR_OF_DAY)
        val minute = now.get(java.util.Calendar.MINUTE)

        // Whether show time in 24 hour format or not.
        val is24Hour = true

        val timePickerDialog = TimePickerDialog(mContext, onTimeSetListener, hour, minute, is24Hour)

        timePickerDialog.setIcon(R.drawable.ic_check)
        timePickerDialog.setTitle("Please select time.")

        timePickerDialog.show()
    }

    private val mContactWatcher = object : TextWatcher {
        internal var previousString = ""
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            previousString = s.toString()
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            if (start > 5) {
                var temp = s.toString()
                if (temp.contains("-")) {
                    temp = temp.replace("-", "")
                }
                val strPhone = makePhoneNumber(temp)
                ET_CUSTOMER_PHONE.setText(strPhone)
                ET_CUSTOMER_PHONE.setSelection(strPhone.length)
            }
        }

        override fun afterTextChanged(s: Editable) {}
    }

    override fun onNetSuccess(data: BaseModel?, nReqType: Int) {

    }

    override fun onNetFail(retCode: Int, strErrorMsg: String, nReqType: Int) {
        super.onNetFail(retCode, strErrorMsg, nReqType)
    }

    override fun onProgresStart(nReqType: Int) {

    }

    override fun onProgresStop(nReqType: Int) {

    }

}