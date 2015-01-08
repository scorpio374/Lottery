package com.xmtq.lottery.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lottery.R;
import com.xmtq.lottery.activity.BetRecordActivity;
import com.xmtq.lottery.activity.ModifiPasswordActivity;
import com.xmtq.lottery.activity.PersonDataActivity;
import com.xmtq.lottery.adapter.BetRecordListAdapter;

/**
 * 投注记录
 * 
 * @author mwz123
 * 
 */
public class BetRecordFragment extends BaseFragment {
	private ListView bet_record;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bet_record, container, false);
		initView(view);
		return view;
	}

	public void initView(View v) {
		bet_record = (ListView) v.findViewById(R.id.record_all_list);
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		BetRecordListAdapter mAdapter = new BetRecordListAdapter(getActivity(),
				mList);
		bet_record.setAdapter(mAdapter);
	}

	@Override
	public void onClickEvent(View v) {
		Intent intent;
		switch (v.getId()) {

		default:
			break;
		}
	}

}
