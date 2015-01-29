package com.xmtq.lottery.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.BetRecordListAdapter;
import com.xmtq.lottery.adapter.RecomendHistoryListAdapter;
import com.xmtq.lottery.adapter.RecomendListAdapter;
import com.xmtq.lottery.bean.GameHistoryDateBean;
import com.xmtq.lottery.bean.GameHistoryDateResponse;
import com.xmtq.lottery.bean.RecomendHistoryBean;
import com.xmtq.lottery.bean.RecomendHistoryResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.utils.DateUtil;

/**
 * 推荐历史
 * 
 * @author Administrator
 * 
 */
public class RecomendHistoryActivity extends BaseActivity {

	private ListView recomend_history_list;
	private ImageButton btn_back;
	private List<GameHistoryDateBean> mHistoryBeansList;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.recomend_history);

	}

	@Override
	public void dealLogicBeforeInitView() {
		request();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		recomend_history_list = (ListView) findViewById(R.id.recomend_history_list);
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
		recomend_history_list.setOnItemClickListener(historyListOnItemListener);
	}

	@Override
	public void dealLogicAfterInitView() {
		// List<String> mList = new ArrayList<String>();
		// for (int i = 0; i < 10; i++) {
		// mList.add(i + "");
		// }
		// RecomendHistoryListAdapter mAdapter = new RecomendHistoryListAdapter(
		// RecomendHistoryActivity.this, mList);
		// recomend_history_list.setAdapter(mAdapter);

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

	private void request() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(new Date());
		RequestMaker mRequestMaker = RequestMaker.getInstance("");
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(mRequestMaker.getGameHistoryDateList("", "", "1",
				"10"));
		mAsyncTask.setOnCompleteListener(mOnCompleteListener);
	}

	private OnCompleteListener<GameHistoryDateResponse> mOnCompleteListener = new OnCompleteListener<GameHistoryDateResponse>() {

		@Override
		public void onComplete(GameHistoryDateResponse result,
				String resultString) {
			if (result != null) {
				GameHistoryDateResponse mResponse = result;
				mHistoryBeansList = mResponse.mHistoryDateBeansList;
				if (mHistoryBeansList != null) {
					Toast.makeText(
							RecomendHistoryActivity.this,
							"RecomendHistoryBean获取到了数据"
									+ mHistoryBeansList.size(), 2000).show();

					RecomendHistoryListAdapter mAdapter = new RecomendHistoryListAdapter(
							RecomendHistoryActivity.this, mHistoryBeansList);
					recomend_history_list.setAdapter(mAdapter);
				}
			}

		}
	};

	private OnItemClickListener historyListOnItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent i = new Intent(RecomendHistoryActivity.this,
					GameResultActivity.class);
			i.putExtra("mHistoryBean", mHistoryBeansList.get(arg2));
			startActivity(i);

		}
	};

}
