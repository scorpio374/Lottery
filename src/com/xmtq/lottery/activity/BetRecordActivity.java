package com.xmtq.lottery.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.lottery.R;
import com.xmtq.lottery.fragment.BetRecordFragment;

/**
 * 投注记录
 * 
 * @author Administrator
 * 
 */
public class BetRecordActivity extends BaseActivity implements
		OnCheckedChangeListener {

	private ImageButton btn_back;
	private String mFormerTag;
	private final static String ALL_TAG = "0";
	private final static String WIN_TAG = "1";
	private final static String WAIT_TAG = "2";
	private BetRecordFragment recordAllFragment;
	private BetRecordFragment recordWinFragment;
	private BetRecordFragment recordWaitFragment;

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

		// request("130", "", "", "1", statue);
		recordAllFragment = new BetRecordFragment(ALL_TAG);
		recordWinFragment = new BetRecordFragment(WIN_TAG);
		recordWaitFragment = new BetRecordFragment(WAIT_TAG);

	}

	// // 测试检查用户名是否存在
	// private OnCompleteListener<CheckUserResponse> mtestOnCompleteListener =
	// new OnCompleteListener<CheckUserResponse>() {
	//
	// @Override
	// public void onComplete(CheckUserResponse result, String resultString) {
	//
	// if (result != null) {
	// if (result.errorcode.equals("0")) {
	// // PurchaseRecordsResponse mResponse = result;
	// // Toast.makeText(BetRecordActivity.this, "查询成功",
	// // 2000).show();
	// //
	// // List<PurchaseRecordsBean> mRecordsBeansList =
	// // mResponse.purchaseRecordsBeans;
	// // // List<String> mList = new ArrayList<String>();
	// // // for (int i = 0; i < 10; i++) {
	// // // mList.add(i + "");
	// // // }
	// // BetRecordListAdapter mAdapter = new BetRecordListAdapter(
	// // BetRecordActivity.this, mRecordsBeansList);
	// // bet_record_all.setAdapter(mAdapter);
	// // bet_record_wait.setAdapter(mAdapter);
	// // bet_record_win.setAdapter(mAdapter);
	//
	// } else {
	// Toast.makeText(BetRecordActivity.this, result.errormsg,
	// 2000).show();
	// }
	// } else {
	// Toast.makeText(BetRecordActivity.this, Consts.REQUEST_ERROR,
	// 2000).show();
	// }
	// }
	// };

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);

		RadioGroup bet_record_radiogroup = (RadioGroup) findViewById(R.id.bet_record_radiogroup);

		bet_record_radiogroup.setOnCheckedChangeListener(this);
		mFormerTag = ALL_TAG;
		getSupportFragmentManager().beginTransaction()
				.add(R.id.content_frame, recordAllFragment, ALL_TAG).commit();
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		// TODO Auto-generated method stub
		FragmentTransaction mTransaction = getSupportFragmentManager()
				.beginTransaction();
		mTransaction.hide(getSupportFragmentManager().findFragmentByTag(
				mFormerTag));

		if (checkedId == R.id.bet_record_all) {
			mFormerTag = ALL_TAG;
			if (recordAllFragment.isAdded()) {
				mTransaction.show(recordAllFragment).commit();
			} else {
				mTransaction
						.add(R.id.content_frame, recordAllFragment, ALL_TAG)
						.commit();
			}
		} else if (checkedId == R.id.bet_record_win) {
			mFormerTag = WIN_TAG;
			if (recordWinFragment.isAdded()) {
				mTransaction.show(recordWinFragment).commit();
			} else {
				mTransaction
						.add(R.id.content_frame, recordWinFragment, WIN_TAG)
						.commit();
			}
		} else if (checkedId == R.id.bet_record_wait) {
			mFormerTag = WAIT_TAG;
			if (recordWaitFragment.isAdded()) {
				mTransaction.show(recordWaitFragment).commit();
			} else {
				mTransaction.add(R.id.content_frame, recordWaitFragment,
						WAIT_TAG).commit();
			}
		}
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
}
