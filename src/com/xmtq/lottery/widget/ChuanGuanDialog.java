package com.xmtq.lottery.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.example.lottery.R;

public class ChuanGuanDialog {

	private Context context;
	private Dialog mdialog;
	private LinearLayout layout;

	public ChuanGuanDialog(Context context) {
		this.context = context;
		// this.myCancelListener = myCancelListener;
	}

	private void initview() {
		layout = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.chuan_guan_dialog, null);

	}

	private void setListener() {
	}

	private void createDialog() {
		mdialog = new Dialog(context, R.style.dialog_style);
		mdialog.setCanceledOnTouchOutside(true);
		mdialog.setContentView(layout);
		mdialog.getWindow().setGravity(Gravity.BOTTOM);
		mdialog.getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mdialog.show();
		mdialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				mdialog = null;
			}
		});
	}

	public void show() {
		initview();
		setListener();
		createDialog();
	}

	public void dismiss() {
		if (mdialog != null) {
			mdialog.dismiss();
		}
	}
}
