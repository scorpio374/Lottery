package com.xmtq.lottery.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.AccountDetailListAdapter;
import com.xmtq.lottery.adapter.BetOrderDetailListAdapter;
import com.xmtq.lottery.adapter.BetRecordListAdapter;
import com.xmtq.lottery.adapter.RecomendHistoryListAdapter;
import com.xmtq.lottery.bean.AccountDetailBean;
import com.xmtq.lottery.bean.AccountDetailResponse;
import com.xmtq.lottery.bean.GameHistoryDateResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;

/**
 * 账户明细
 * 
 * @author Administrator
 * 
 */
public class AccountDetailActivity extends BaseActivity {

	private ListView account_detail_list;
	private ImageButton btn_back;
	private TextView head_right;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.account_dedail);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// 默认请求
		request("0");
	}

	@Override
	public void initView() {
		head_right = (TextView) findViewById(R.id.head_right);
		account_detail_list = (ListView) findViewById(R.id.account_detail_list);
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
		head_right.setOnClickListener(this);

		RadioGroup account_detail_radiogroup = (RadioGroup) findViewById(R.id.account_detail_radiogroup);

		account_detail_radiogroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						if (checkedId == R.id.account_my) {
							Toast.makeText(AccountDetailActivity.this, "账户",
									2000).show();
							request("0");
						} else if (checkedId == R.id.account_recharge) {
							Toast.makeText(AccountDetailActivity.this, "充值",
									2000).show();
							request("0");
						} else if (checkedId == R.id.account_deposit) {
							Toast.makeText(AccountDetailActivity.this, "提现",
									2000).show();
							request("0");
						}
					}
				});

	}

	private void request(String mFlag) {

		RequestMaker mRequestMaker = RequestMaker.getInstance();
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(mRequestMaker.getAccountDetail("", "", userid,
				mFlag, "1", "10"));
		mAsyncTask.setOnCompleteListener(mOnCompleteListener);
	}

	private OnCompleteListener<AccountDetailResponse> mOnCompleteListener = new OnCompleteListener<AccountDetailResponse>() {

		@Override
		public void onComplete(AccountDetailResponse result, String resultString) {
			if (result != null) {
				AccountDetailResponse mResponse = result;
				List<AccountDetailBean> mHistoryBeansList = mResponse.accountDetailList;
				if (mHistoryBeansList != null) {
					Toast.makeText(
							AccountDetailActivity.this,
							"AccountDetailBean获取到了数据"
									+ mHistoryBeansList.size(), 2000).show();

					// for (int i = 0; i < mHistoryBeansList.size(); i++) {
					//
					// }
					AccountDetailListAdapter mAdapter = new AccountDetailListAdapter(
							AccountDetailActivity.this, mHistoryBeansList);
					account_detail_list.setAdapter(mAdapter);

				}
			}

		}
	};

	@Override
	public void dealLogicAfterInitView() {
		// List<String> mList = new ArrayList<String>();
		// for (int i = 0; i < 10; i++) {
		// mList.add(i + "");
		// }
		// AccountDetailListAdapter mAdapter = new AccountDetailListAdapter(
		// AccountDetailActivity.this, mList);
		// account_detail_list.setAdapter(mAdapter);

	}

	@Override
	public void onClickEvent(View view) {
		switch (view.getId()) {
		case R.id.back:
			this.finish();
			break;
		case R.id.head_right:

			Intent intent = new Intent(AccountDetailActivity.this,
					AccountDetailLastweekActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

}
