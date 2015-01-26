package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

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
	private ListView bank_card_list;

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
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(this);

		bank_savings_list = (ListView) findViewById(R.id.bank_savings_list);
		bank_card_list = (ListView) findViewById(R.id.bank_card_list);

		RadioGroup check_card = (RadioGroup) findViewById(R.id.check_card);

		check_card.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.bank_savings) {
					Toast.makeText(CheckBankActivity.this, "储蓄卡", 2000).show();
					bank_savings_list.setVisibility(View.VISIBLE);
					bank_card_list.setVisibility(View.GONE);
				} else if (checkedId == R.id.bank_credit_card) {
					Toast.makeText(CheckBankActivity.this, "信用卡", 2000).show();
					bank_savings_list.setVisibility(View.GONE);
					bank_card_list.setVisibility(View.VISIBLE);
				}
			}
		});

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
		bank_card_list.setAdapter(mAdapter);

	}

	@Override
	public void onClickEvent(View view) {
		switch (view.getId()) {
		case R.id.back:
			finish();
			break;

		default:
			break;
		}

	}

}
