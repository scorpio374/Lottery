package com.xmtq.lottery.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.lottery.R;
import com.xmtq.lottery.activity.RecomendActivity;
import com.xmtq.lottery.adapter.RecomendListAdapter;

/**
 * 个人中心
 * 
 * @author mwz123
 * 
 */
public class RecomendFragment extends BaseFragment {

	private ImageButton imgBtnLeft, imgBtnRight;
	private ListView recomend_list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.recomend, container, false);
		initView(view);
		return view;
	}

	public void initView(View v) {
		imgBtnLeft = (ImageButton) v.findViewById(R.id.recomend_left);
		imgBtnRight = (ImageButton) v.findViewById(R.id.recomend_right);
		imgBtnLeft.setOnClickListener(this);
		imgBtnRight.setOnClickListener(this);
		recomend_list = (ListView) v.findViewById(R.id.recomend_list);

		dealLogicAfterInitView();
	}

	public void dealLogicAfterInitView() {
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		RecomendListAdapter mAdapter = new RecomendListAdapter(getActivity(),
				mList);
		recomend_list.setAdapter(mAdapter);
	}

	@Override
	public void onClickEvent(View v) {
		switch (v.getId()) {
		case R.id.recomend_left:
			((RecomendActivity) getActivity()).openLeftDrawer();
			break;

		case R.id.recomend_right:
			((RecomendActivity) getActivity()).openRightDrawer();
			break;

		default:
			break;
		}
	}

}
