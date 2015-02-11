package com.xmtq.lottery.widget;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.ChuanGuanMoreAdapter;

public class ChuanGuanDialog {

	private Context context;
	private Dialog mdialog;
	private LinearLayout layout;
	private LinearLayout tv_more_style;

	private GridView chuanguan_more;
	private GridView chuanguan_gridview;
	private Button btn_cancel;
	private Button btn_commit;
	private OnClickListener myCancelListener;
	private OnClickListener myCommitClickListener;

	public ChuanGuanDialog(Context context,
			OnClickListener cancelClickListener,
			OnClickListener commitClickListener) {
		this.context = context;
		this.myCancelListener = cancelClickListener;
		this.myCommitClickListener = commitClickListener;
	}

	private void initview() {
		layout = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.chuan_guan_dialog, null);
		btn_cancel = (Button) layout.findViewById(R.id.btn_cancel);
		btn_commit = (Button) layout.findViewById(R.id.btn_commit);

		tv_more_style = (LinearLayout) layout.findViewById(R.id.tv_more_style);
		chuanguan_gridview = (GridView) layout.findViewById(R.id.chuanguan);
		chuanguan_more = (GridView) layout.findViewById(R.id.chuanguan_more);
		String chuanguan_more_array[] = { "3串3", "3串4", "4串4", "4串5", "4串6", "4串11",
				"5串5", "5串6", "5串10", "5串16", "5串20", "5串26", "6串6", "6串7",
				"6串15", "6串20", "6串22", "6串35", "6串42", "6串50", "6串57" };
		List<String> oddsList = new ArrayList<String>();
		for (int i = 0; i < chuanguan_more_array.length; i++) {
			oddsList.add(chuanguan_more_array[i]);
		}
		ChuanGuanMoreAdapter adapter = new ChuanGuanMoreAdapter(context,
				oddsList);
		chuanguan_more.setAdapter(adapter);
		
		String chuanguan_array[] = { "单关", "2串1", "3串1", "4串1", "5串1"};
		List<String> chuanguanList = new ArrayList<String>();
		for (int i = 0; i < chuanguan_array.length; i++) {
			chuanguanList.add(chuanguan_array[i]);
		}
		ChuanGuanMoreAdapter adapter1 = new ChuanGuanMoreAdapter(context,
				chuanguanList);
		chuanguan_gridview.setAdapter(adapter1);
		
		tv_more_style.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (chuanguan_more.getVisibility() == View.GONE) {
					chuanguan_more.setVisibility(View.VISIBLE);
				} else {
					chuanguan_more.setVisibility(View.GONE);
				}
			}
		});

	}

	private void setListener() {
		btn_cancel.setOnClickListener(myCancelListener);
		btn_commit.setOnClickListener(myCommitClickListener);
	}

	private void createDialog() {
		mdialog = new Dialog(context, R.style.dialog_style);
		mdialog.setCanceledOnTouchOutside(true);
		mdialog.setContentView(layout);
		mdialog.getWindow().setGravity(Gravity.CENTER);
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
