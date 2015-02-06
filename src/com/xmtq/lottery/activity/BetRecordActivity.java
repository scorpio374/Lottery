package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.BetRecordListAdapter;
import com.xmtq.lottery.bean.ExtractCashResponse;
import com.xmtq.lottery.bean.PurchaseRecordsBean;
import com.xmtq.lottery.bean.PurchaseRecordsResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.utils.SharedPrefHelper;

/**
 * 投注记录
 * 
 * @author Administrator
 * 
 */
public class BetRecordActivity extends BaseActivity {

	private ListView bet_record_all, bet_record_win, bet_record_wait;
	private ImageButton btn_back;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.bet_record);

	}

	@Override
	public void dealLogicBeforeInitView() {
		//

		request("130", "", "", "", "0");
	}

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
							Toast.makeText(BetRecordActivity.this, "全部", 2000)
									.show();
							bet_record_all.setVisibility(View.VISIBLE);
							bet_record_win.setVisibility(View.GONE);
							bet_record_wait.setVisibility(View.GONE);

						} else if (checkedId == R.id.bet_record_win) {
							Toast.makeText(BetRecordActivity.this, "中奖", 2000)
									.show();
							bet_record_all.setVisibility(View.GONE);
							bet_record_win.setVisibility(View.VISIBLE);
							bet_record_wait.setVisibility(View.GONE);
						} else if (checkedId == R.id.bet_record_wait) {
							Toast.makeText(BetRecordActivity.this, "待开", 2000)
									.show();
							bet_record_all.setVisibility(View.GONE);
							bet_record_win.setVisibility(View.GONE);
							bet_record_wait.setVisibility(View.VISIBLE);
						}
					}
				});
	}

	@Override
	public void dealLogicAfterInitView() {

	}

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

		RequestMaker mRequestMaker = RequestMaker.getInstance();
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(mRequestMaker.getPurchaseRecords(userid, lotteryid,
				startdate, enddate, investtype, "1", "10", statue));
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

					List<PurchaseRecordsBean> mRecordsBeansList = mResponse.purchaseRecordsBeans;
					// List<String> mList = new ArrayList<String>();
					// for (int i = 0; i < 10; i++) {
					// mList.add(i + "");
					// }
					BetRecordListAdapter mAdapter = new BetRecordListAdapter(
							BetRecordActivity.this, mRecordsBeansList);
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
		}
	};

}
