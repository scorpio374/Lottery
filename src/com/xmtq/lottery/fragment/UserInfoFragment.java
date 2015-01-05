package com.xmtq.lottery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.lottery.R;
import com.xmtq.lottery.activity.BetRecordActivity;
import com.xmtq.lottery.activity.ModifiPasswordActivity;
import com.xmtq.lottery.activity.PersonDataActivity;

/**
 * 个人中心
 * 
 * @author mwz123
 * 
 */
public class UserInfoFragment extends BaseFragment {
	private RelativeLayout rl_repassword, rl_bet_record, rl_userinfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.userinfo, container, false);
		initView(view);
		return view;
	}

	public void initView(View v) {
		rl_repassword = (RelativeLayout) v.findViewById(R.id.rl_repassword);
		rl_bet_record = (RelativeLayout) v.findViewById(R.id.rl_bet_record);
		rl_userinfo = (RelativeLayout) v.findViewById(R.id.rl_userinfo);

		rl_bet_record.setOnClickListener(this);
		rl_repassword.setOnClickListener(this);
		rl_userinfo.setOnClickListener(this);
	}

	@Override
	public void onClickEvent(View v) {
		Intent intent;
		switch (v.getId()) {
		// 投注记录
		case R.id.rl_bet_record:
			intent = new Intent(getActivity(), BetRecordActivity.class);
			startActivity(intent);

			break;
		// 个人资料
		case R.id.rl_userinfo:
			intent = new Intent(getActivity(), PersonDataActivity.class);
			startActivity(intent);

			break;
		// 修改密码
		case R.id.rl_repassword:
			intent = new Intent(getActivity(), ModifiPasswordActivity.class);
			startActivity(intent);

			break;

		default:
			break;
		}
	}

}
