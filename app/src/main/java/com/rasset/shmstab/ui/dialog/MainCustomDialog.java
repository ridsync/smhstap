package com.rasset.shmstab.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rasset.shmstab.R;

/**
 * Created by andman on 2016-01-12.
 * 커스텀 Custom다이얼로그 Base  import android.support.v7.app.AlertDialo
 *  1) Title | ConetntTxt | 확인 | 취소
 *  2) Custom Theme Use
 *  미완성
 */
public class MainCustomDialog extends BaseDialogFragment implements View.OnClickListener{

    private static volatile MainCustomDialog sFag;

    public static MainCustomDialog newInstance(Context context) {
        MainCustomDialog mFag = new MainCustomDialog();
        mFag.setMContext(context);
        return mFag;
    }

    public static MainCustomDialog getInstance() {
        if(sFag == null) {
            synchronized (MainCustomDialog.class) {
                if(sFag == null) {
                    sFag = new MainCustomDialog();
                }
            }
        }
        return sFag;
    }

    public MainCustomDialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MainCustomDialog);
        mRootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_maincustom, null);

        mBtnPositive = mRootView.findViewById(R.id.BTN_DONE);

        mBtnNegative = mRootView.findViewById(R.id.BTN_CANCEL);
        mTvContents = mRootView.findViewById(R.id.TV_CONTENTS);

        mBtnPositive.setOnClickListener(this);
        mBtnNegative.setOnClickListener(this);

        builder.setView(mRootView);
        Dialog dlg = builder.create();
        dlg.setCancelable(false);
        dlg.setCanceledOnTouchOutside(false);
        return dlg;
    }

    @Override
    public void onClick(View v) {
        if (mOnViewClickListener!=null)
            mOnViewClickListener.onClick(v,this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

}

