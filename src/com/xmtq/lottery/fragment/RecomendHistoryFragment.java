package com.xmtq.lottery.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.activity.GameResultActivity;
import com.xmtq.lottery.adapter.RecomendHistoryListAdapter;
import com.xmtq.lottery.bean.GameHistoryDateBean;
import com.xmtq.lottery.bean.GameHistoryDateResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;

/*
 * 投注记录
 * 
 * @author mwz123
 * 
 */
public class RecomendHistoryFragment extends BaseFragment {
	private ListView recomend_history_list;
	private ImageButton btn_back;
	private List<GameHistoryDateBean> mHistoryBeansList;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.recomend_history, container,
				false);
		initView(view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// request();
		super.onActivityCreated(savedInstanceState);
	}

	public void initView(View v) {

		recomend_history_list = (ListView) v
				.findViewById(R.id.recomend_history_list);
		btn_back = (ImageButton) v.findViewById(R.id.back);
		btn_back.setOnClickListener(this);
		recomend_history_list.setOnItemClickListener(historyListOnItemListener);

	}

	private void request() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(new Date());
		RequestMaker mRequestMaker = RequestMaker.getInstance();
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
							getActivity(),
							"RecomendHistoryBean获取到了数据"
									+ mHistoryBeansList.size(), 2000).show();

					RecomendHistoryListAdapter mAdapter = new RecomendHistoryListAdapter(
							getActivity(), mHistoryBeansList);
					recomend_history_list.setAdapter(mAdapter);
				}
			}

		}
	};

	private OnItemClickListener historyListOnItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent i = new Intent(getActivity(), GameResultActivity.class);
			i.putExtra("mHistoryBean", mHistoryBeansList.get(arg2));
			startActivity(i);

		}
	};

	@Override
	public void onClickEvent(View v) {
		Intent intent;
		switch (v.getId()) {
		default:
			break;
		}
	}
}
