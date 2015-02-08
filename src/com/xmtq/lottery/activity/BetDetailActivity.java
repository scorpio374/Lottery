package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.BetOrderDetailListAdapter;
import com.xmtq.lottery.adapter.BetRecordListAdapter;

/**
 * 投注详情
 * 
 * @author Administrator
 * 
 */
public class BetDetailActivity extends BaseActivity {

	private ListView bet_record_detail;
	private ImageButton btn_back;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.bet_orderdetail);
	}

	@Override
	public void dealLogicBeforeInitView() {

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		bet_record_detail = (ListView) findViewById(R.id.bet_orderdetail_list);
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		BetOrderDetailListAdapter mAdapter = new BetOrderDetailListAdapter(
				BetDetailActivity.this, mList);
		bet_record_detail.setAdapter(mAdapter);

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
