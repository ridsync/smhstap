package com.rasset.shmstab.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rasset.shmstab.R;
import com.rasset.shmstab.ui.components.RecursiveRadioGroup;

/**
 * Created by andman on 2016-01-12.
 */
public class SelectSubCategoryDialog extends BaseDialogFragment   implements View.OnClickListener{

    private static volatile SelectSubCategoryDialog sFag;

    public void setOnDismissListener() {

    }
    public void setOnPositveListener(OnPositveListener listener) {
        positiveListener = listener;
    }

    public interface OnPositveListener {
        void onClickPositive(SelectSubCategoryDialog dialog);
    }

    public static SelectSubCategoryDialog newInstance(Context context) {
        SelectSubCategoryDialog mFag = new SelectSubCategoryDialog();
        mFag.mContext = context;
        return mFag;
    }

    public SelectSubCategoryDialog() {
    }

    boolean isCancelable = true;
    OnPositveListener positiveListener ;
    public RecursiveRadioGroup recGroup;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.FullScreenDialog);
        mRootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_select_sub_category, null);

        mBtnPositive = (mRootView.findViewById(R.id.BTN_DONE));
        recGroup = (mRootView.findViewById(R.id.RG_DIAG_RESTATE_TYPE));
        mBtnPositive.setOnClickListener(this);

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
            if (positiveListener !=null)
                positiveListener.onClickPositive(this);
        }
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTvContents = null;
    }



}

