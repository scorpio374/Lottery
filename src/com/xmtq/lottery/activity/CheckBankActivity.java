package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.BankSavingListAdapter;
import com.xmtq.lottery.adapter.BetOrderDetailListAdapter;
import com.xmtq.lottery.adapter.BetRecordListAdapter;

/**
 * 选择银行卡
 * 
 * @author Administrator
 * 
 */
public class CheckBankActivity extends BaseActivity {

	private ListView bank_savings_list;
	private ImageButton btn_back;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.check_bank_card);

	}

	@Override
	public void dealLogicBeforeInitView() {

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		bank_savings_list = (ListView) findViewById(R.id.bank_savings_list);
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		BankSavingListAdapter mAdapter = new BankSavingListAdapter(
				CheckBankActivity.this, mList);
		bank_savings_list.setAdapter(mAdapter);

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
