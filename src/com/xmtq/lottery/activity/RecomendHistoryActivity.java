package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.BetRecordListAdapter;
import com.xmtq.lottery.adapter.RecomendHistoryListAdapter;
import com.xmtq.lottery.adapter.RecomendListAdapter;

/**
 * 推荐历史
 * 
 * @author Administrator
 * 
 */
public class RecomendHistoryActivity extends BaseActivity {

	private ListView recomend_history_list;
	private ImageButton btn_back;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.recomend_history);

	}

	@Override
	public void dealLogicBeforeInitView() {

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		recomend_history_list = (ListView) findViewById(R.id.recomend_history_list);
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		RecomendHistoryListAdapter mAdapter = new RecomendHistoryListAdapter(
				RecomendHistoryActivity.this, mList);
		recomend_history_list.setAdapter(mAdapter);

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
