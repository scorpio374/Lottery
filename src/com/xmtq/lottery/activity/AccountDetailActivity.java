package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.AccountDetailListAdapter;
import com.xmtq.lottery.adapter.BetOrderDetailListAdapter;
import com.xmtq.lottery.adapter.BetRecordListAdapter;

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

	}

	@Override
	public void initView() {
		head_right = (TextView) findViewById(R.id.head_right);
		account_detail_list = (ListView) findViewById(R.id.account_detail_list);
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
		head_right.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		AccountDetailListAdapter mAdapter = new AccountDetailListAdapter(
				AccountDetailActivity.this, mList);
		account_detail_list.setAdapter(mAdapter);

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
