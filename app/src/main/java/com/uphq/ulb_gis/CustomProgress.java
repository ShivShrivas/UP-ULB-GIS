package com.uphq.ulb_gis;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;



public class CustomProgress {

    private static CustomProgress customProgress;
    private static Dialog mDialog;
    private static ProgressBar mProgressBar;

    public static CustomProgress getInstance() {
        if (customProgress == null) {
            customProgress = new CustomProgress();
        }
        return customProgress;
    }

    public  void showProgress(Context context, String message, boolean cancelable) {
        mDialog = new Dialog(context);
        // no title for the dialog
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.prograss_bar_dialog);
        mProgressBar = mDialog.findViewById(R.id.progress_bar);
        TextView progressText = mDialog.findViewById(R.id.progress_text);
        progressText.setText(message);
        progressText.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        // you can change or add this line according to your need
        mProgressBar.setIndeterminate(true);
        mDialog.setCancelable(cancelable);
        mDialog.setCanceledOnTouchOutside(cancelable);
        mDialog.show();
    }

    public  void hideProgress() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
