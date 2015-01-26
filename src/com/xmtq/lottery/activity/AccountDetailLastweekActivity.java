package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.AccountDetailListAdapter;
import com.xmtq.lottery.adapter.BetOrderDetailListAdapter;
import com.xmtq.lottery.adapter.BetRecordListAdapter;

/**
 * 近一周
 * 
 * @author Administrator
 * 
 */
public class AccountDetailLastweekActivity extends BaseActivity {

	private ImageButton btn_back;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.account_dedail_lastweek);

	}

	@Override
	public void dealLogicBeforeInitView() {

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
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
