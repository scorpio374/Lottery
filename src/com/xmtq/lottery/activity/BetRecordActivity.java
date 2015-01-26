package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.BetRecordListAdapter;

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
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		BetRecordListAdapter mAdapter = new BetRecordListAdapter(
				BetRecordActivity.this, mList);
		bet_record_all.setAdapter(mAdapter);
		bet_record_wait.setAdapter(mAdapter);
		bet_record_win.setAdapter(mAdapter);

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
