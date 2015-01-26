package com.xmtq.lottery.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

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
	private ListView bet_record_all, bet_record_win, bet_record_wait;

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

		bet_record_all = (ListView) v.findViewById(R.id.record_all_list);
		bet_record_win = (ListView) v.findViewById(R.id.record_win_list);
		bet_record_wait = (ListView) v.findViewById(R.id.record_wait_list);

		RadioGroup bet_record_radiogroup = (RadioGroup) v
				.findViewById(R.id.bet_record_radiogroup);

		bet_record_radiogroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == R.id.bet_record_all) {
							Toast.makeText(getActivity(), "全部", 2000).show();
							bet_record_all.setVisibility(View.VISIBLE);
							bet_record_win.setVisibility(View.GONE);
							bet_record_wait.setVisibility(View.GONE);

						} else if (checkedId == R.id.bet_record_win) {
							Toast.makeText(getActivity(), "中奖", 2000).show();
							bet_record_all.setVisibility(View.GONE);
							bet_record_win.setVisibility(View.VISIBLE);
							bet_record_wait.setVisibility(View.GONE);
						} else if (checkedId == R.id.bet_record_wait) {
							Toast.makeText(getActivity(), "待开", 2000).show();
							bet_record_all.setVisibility(View.GONE);
							bet_record_win.setVisibility(View.GONE);
							bet_record_wait.setVisibility(View.VISIBLE);
						}
					}
				});

		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		BetRecordListAdapter mAdapter = new BetRecordListAdapter(getActivity(),
				mList);
		bet_record_all.setAdapter(mAdapter);
		bet_record_win.setAdapter(mAdapter);
		bet_record_wait.setAdapter(mAdapter);
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
