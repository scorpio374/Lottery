package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.BetRecordListAdapter;
import com.xmtq.lottery.bean.CheckUserResponse;
import com.xmtq.lottery.bean.ExtractCashResponse;
import com.xmtq.lottery.bean.PurchaseRecordsBean;
import com.xmtq.lottery.bean.PurchaseRecordsResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.utils.SharedPrefHelper;
import com.xmtq.lottery.utils.ToastUtil;

/**
 * 投注记录
 * 
 * @author Administrator
 * 
 */
public class BetRecordActivity extends BaseActivity {

	private ListView bet_record_all, bet_record_win, bet_record_wait;
	private ImageButton btn_back;
	private List<PurchaseRecordsBean> mRecordsBeansList;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.bet_record);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// 测试检查用户名是否存在
		// RequestMaker mRequestMaker = RequestMaker.getInstance();
		// HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		// mAsyncTask.execute(mRequestMaker.getCheckUser("xmwd", "", ""));
		// mAsyncTask.setOnCompleteListener(mtestOnCompleteListener);

		request("130", "", "", "1", "0");
	}

	// 测试检查用户名是否存在
	private OnCompleteListener<CheckUserResponse> mtestOnCompleteListener = new OnCompleteListener<CheckUserResponse>() {

		@Override
		public void onComplete(CheckUserResponse result, String resultString) {

			if (result != null) {
				if (result.errorcode.equals("0")) {
					// PurchaseRecordsResponse mResponse = result;
					// Toast.makeText(BetRecordActivity.this, "查询成功",
					// 2000).show();
					//
					// List<PurchaseRecordsBean> mRecordsBeansList =
					// mResponse.purchaseRecordsBeans;
					// // List<String> mList = new ArrayList<String>();
					// // for (int i = 0; i < 10; i++) {
					// // mList.add(i + "");
					// // }
					// BetRecordListAdapter mAdapter = new BetRecordListAdapter(
					// BetRecordActivity.this, mRecordsBeansList);
					// bet_record_all.setAdapter(mAdapter);
					// bet_record_wait.setAdapter(mAdapter);
					// bet_record_win.setAdapter(mAdapter);

				} else {
					Toast.makeText(BetRecordActivity.this, result.errormsg,
							2000).show();
				}
			} else {
				Toast.makeText(BetRecordActivity.this, "请求错误", 2000).show();
			}
		}
	};

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		bet_record_all = (ListView) findViewById(R.id.record_all_list);
		bet_record_win = (ListView) findViewById(R.id.record_win_list);
		bet_record_wait = (ListView) findViewById(R.id.record_wait_list);

		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);

		RadioGroup bet_record_radiogroup = (RadioGroup) findViewById(R.id.bet_record_radiogroup);

		bet_record_radiogroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == R.id.bet_record_all) {
							bet_record_all.setVisibility(View.VISIBLE);
							bet_record_win.setVisibility(View.GONE);
							bet_record_wait.setVisibility(View.GONE);

						} else if (checkedId == R.id.bet_record_win) {
							bet_record_all.setVisibility(View.GONE);
							bet_record_win.setVisibility(View.VISIBLE);
							bet_record_wait.setVisibility(View.GONE);
						} else if (checkedId == R.id.bet_record_wait) {
							bet_record_all.setVisibility(View.GONE);
							bet_record_win.setVisibility(View.GONE);
							bet_record_wait.setVisibility(View.VISIBLE);
						}
					}
				});

		bet_record_all.setOnItemClickListener(betDetailListener);
	}

	@Override
	public void dealLogicAfterInitView() {

	}

	private OnItemClickListener betDetailListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			Intent intent = new Intent(BetRecordActivity.this,
					BetDetailActivity.class);
			intent.putExtra("serialid", mRecordsBeansList.get(arg2)
					.getSerialid());
			if (mRecordsBeansList.get(arg2).getBonusAfterfax() != null) {

				intent.putExtra("winMoney", mRecordsBeansList.get(arg2)
						.getBonusAfterfax());
			}
			startActivity(intent);

		}
	};

	@Override
	public void onClickEvent(View view) {
		switch (view.getId()) {
		case R.id.back:
			this.finish();
			break;

		default:
			break;
		}

	}

	private void request(String lotteryid, String startdate, String enddate,
			String investtype, String statue) {
		String userid = SharedPrefHelper.getInstance(getApplicationContext())
				.getUid();

		mLoadingDialog.show("数据加载中...");
		RequestMaker mRequestMaker = RequestMaker.getInstance();
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(mRequestMaker.getPurchaseRecords("14138", lotteryid,
				startdate, enddate, investtype, "1", "5", statue));
		mAsyncTask.setOnCompleteListener(mOnCompleteListener);
	}

	private OnCompleteListener<PurchaseRecordsResponse> mOnCompleteListener = new OnCompleteListener<PurchaseRecordsResponse>() {

		@Override
		public void onComplete(PurchaseRecordsResponse result,
				String resultString) {

			if (result != null) {
				if (result.errorcode.equals("0")) {
					PurchaseRecordsResponse mResponse = result;
					Toast.makeText(BetRecordActivity.this, "查询成功", 2000).show();

					mRecordsBeansList = mResponse.purchaseRecordsBeans;

					if (mRecordsBeansList.size() == 0) {
						ToastUtil.showCenterToast(BetRecordActivity.this,
								"没有投注记录");
					}
					BetRecordListAdapter mAdapter = new BetRecordListAdapter(
							BetRecordActivity.this, mRecordsBeansList, 0);
					bet_record_all.setAdapter(mAdapter);
					bet_record_wait.setAdapter(mAdapter);
					bet_record_win.setAdapter(mAdapter);

				} else {
					Toast.makeText(BetRecordActivity.this, result.errormsg,
							2000).show();
				}
			} else {
				Toast.makeText(BetRecordActivity.this, "请求错误", 2000).show();
			}
			mLoadingDialog.dismiss();
		}
	};

}
