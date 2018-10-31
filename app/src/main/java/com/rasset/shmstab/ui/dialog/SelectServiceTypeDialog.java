package com.rasset.shmstab.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.rasset.shmstab.R;
import com.rasset.shmstab.model.DiagnoseInfo;
import com.rasset.shmstab.ui.components.RecursiveRadioGroup;

/**
 * Created by andman on 2016-01-12.
 */
public class SelectServiceTypeDialog extends BaseDialogFragment   implements View.OnClickListener{

    private static volatile SelectServiceTypeDialog sFag;

    public void setOnDismissListener() {

    }
    public void setOnPositveListener(OnPositveListener listener) {
        positiveListener = listener;
    }

    public interface OnPositveListener {
        void onClickPositive(SelectServiceTypeDialog dialog);
    }

    public static SelectServiceTypeDialog newInstance(Context context, DiagnoseInfo diagnoseInfo) {
        SelectServiceTypeDialog mFag = new SelectServiceTypeDialog();
        mFag.mContext = context;
        mFag.diagnoseInfo = diagnoseInfo;
        return mFag;
    }

    public SelectServiceTypeDialog() {
    }

    boolean isCancelable = true;
    OnPositveListener positiveListener ;
    public RecursiveRadioGroup recGroup;
    public LinearLayout llSMhDiag;
    public LinearLayout llSMhRich;
    public RadioButton rbSMhDiag;
    public RadioButton rbSMhRich;
    public DiagnoseInfo diagnoseInfo;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.FullScreenDialog);
        mRootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_select_service, null);

        mBtnPositive = (mRootView.findViewById(R.id.BTN_DONE));
        recGroup = (mRootView.findViewById(R.id.RG_DIAG_SERVICE_TYPE));
        llSMhDiag = (mRootView.findViewById(R.id.LL_SMHS_DIAG));
        llSMhRich = (mRootView.findViewById(R.id.LL_SMHS_RICH));
        ImageView ivDiag = (mRootView.findViewById(R.id.IV_SMHS_DIAG));
        ImageView ivRich = (mRootView.findViewById(R.id.IV_SMHS_RICH));
        rbSMhDiag = (mRootView.findViewById(R.id.RB_SMHS_DIAG));
        rbSMhRich = (mRootView.findViewById(R.id.RB_SMHS_RICH));

        mBtnPositive.setOnClickListener(this);
        llSMhDiag.setOnClickListener(this);
        llSMhRich.setOnClickListener(this);

        boolean isEnable = diagnoseInfo.getDiagnoseId() > 0;
            rbSMhDiag.setEnabled(isEnable);
            ivDiag.setEnabled(isEnable);
            rbSMhDiag.setChecked(isEnable);
        boolean isEnableRich = diagnoseInfo.getRichSurveyId() > 0;
            rbSMhRich.setEnabled(isEnableRich);
            ivRich.setEnabled(isEnableRich);
            rbSMhRich.setChecked(isEnableRich);

        builder.setView(mRootView);
        Dialog dlg = builder.create();
        setCancelable(isCancelable);
        return dlg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.BTN_DONE) {
            dismiss();
            if (positiveListener !=null)
                positiveListener.onClickPositive(this);
        } else  if (v.getId() == R.id.LL_SMHS_DIAG) {
            if(diagnoseInfo.getDiagnoseId() > 0
                     && !rbSMhDiag.isChecked())
                recGroup.check(rbSMhDiag);
        } else  if (v.getId() == R.id.LL_SMHS_RICH) {
            if(diagnoseInfo.getRichSurveyId() > 0
                    && !rbSMhRich.isChecked())
                recGroup.check(rbSMhRich);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTvContents = null;
    }



}

