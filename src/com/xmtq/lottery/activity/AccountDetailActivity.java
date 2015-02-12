package com.xmtq.lottery.activity;

import java.io.Serializable;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.AccountDetailListAdapter;
import com.xmtq.lottery.bean.AccountDetailBean;
import com.xmtq.lottery.bean.AccountDetailResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.utils.SharedPrefHelper;
import com.xmtq.lottery.utils.ToastUtil;
import com.xmtq.lottery.widget.LoadingDialog;

/**
 * 账户明细
 * 
 * @author Administrator
 * 
 */
public class AccountDetailActivity extends BaseActivity {
	private LoadingDialog mDialog;
	private ListView account_detail_list;
	private ImageButton btn_back;
	private TextView head_right;
	private List<AccountDetailBean> mHistoryBeansList;

	// private String count;
	private String pay;
	private String income;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.account_dedail);

	}

	@Override
	public void dealLogicBeforeInitView() {
		mDialog = new LoadingDialog(this);
		// 默认请求
		request("");
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
							request("");
						} else if (checkedId == R.id.account_recharge) {
							request("93,1");
						} else if (checkedId == R.id.account_deposit) {
							request("5,6");
						}
					}
				});

	}

	private void request(String mFlag) {
		String userid = SharedPrefHelper.getInstance(getApplicationContext())
				.getUid();
		mDialog.show("数据加载中...");
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
				mHistoryBeansList = mResponse.accountDetailList;
				if (mHistoryBeansList != null) {

					AccountDetailListAdapter mAdapter = new AccountDetailListAdapter(
							AccountDetailActivity.this, mHistoryBeansList);
					account_detail_list.setAdapter(mAdapter);
					mDialog.dismiss();

					pay = mResponse.getPay();
					income = mResponse.getIncome();

				}
			} else {
				ToastUtil.showCenterToast(AccountDetailActivity.this, "数据请求失败");
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
			if (mHistoryBeansList != null && mHistoryBeansList.size() > 0) {
				intent.putExtra("mHistoryBeansList",
						(Serializable) mHistoryBeansList);
				intent.putExtra("pay", pay);
				intent.putExtra("income", income);
				startActivity(intent);
			} else {
				ToastUtil.showCenterToast(AccountDetailActivity.this,
						"近一周没有交易信息");
			}
			break;
		default:
			break;
		}

	}
}
