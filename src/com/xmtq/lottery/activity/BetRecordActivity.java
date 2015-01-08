package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.BetRecordListAdapter;

/**
 * 投注记录
 * 
 * @author Administrator
 * 
 */
public class BetRecordActivity extends BaseActivity {

	private ListView bet_record;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.bet_record);

	}

	@Override
	public void dealLogicBeforeInitView() {

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		bet_record = (ListView) findViewById(R.id.record_all_list);
	}

	@Override
	public void dealLogicAfterInitView() {
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		BetRecordListAdapter mAdapter = new BetRecordListAdapter(
				BetRecordActivity.this, mList);
		bet_record.setAdapter(mAdapter);

	}

	@Override
	public void onClickEvent(View view) {
		// TODO Auto-generated method stub

	}

}
