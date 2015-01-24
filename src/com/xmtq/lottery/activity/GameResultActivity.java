package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.BetRecordListAdapter;
import com.xmtq.lottery.adapter.GameResuleDetailListAdapter;
import com.xmtq.lottery.adapter.RecomendHistoryListAdapter;
import com.xmtq.lottery.adapter.RecomendListAdapter;

/**
 * 赛果详情
 * 
 * @author Administrator
 * 
 */
public class GameResultActivity extends BaseActivity {

	private ListView game_result_detail_list;
	private ImageButton btn_back;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.game_result_detail);

	}

	@Override
	public void dealLogicBeforeInitView() {

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		game_result_detail_list = (ListView) findViewById(R.id.game_result_detail_list);
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		GameResuleDetailListAdapter mAdapter = new GameResuleDetailListAdapter(
				GameResultActivity.this, mList);
		game_result_detail_list.setAdapter(mAdapter);

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
