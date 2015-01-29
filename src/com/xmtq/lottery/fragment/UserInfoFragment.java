package com.xmtq.lottery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lottery.R;
import com.xmtq.lottery.activity.AccountDetailActivity;
import com.xmtq.lottery.activity.BetRecordActivity;
import com.xmtq.lottery.activity.ExtractMoneyActivity;
import com.xmtq.lottery.activity.ModifiPasswordActivity;
import com.xmtq.lottery.activity.PersonDataActivity;
import com.xmtq.lottery.activity.RechargeMoneyActivity;
import com.xmtq.lottery.activity.RecomendActivity;
import com.xmtq.lottery.bean.NewUserLoginBean;
import com.xmtq.lottery.utils.SharedPrefHelper;

/**
 * 个人中心
 * 
 * @author mwz123
 * 
 */
public class UserInfoFragment extends BaseFragment {
	private RelativeLayout rl_repassword, rl_bet_record, rl_userinfo;
	private RelativeLayout account_information;
	private TextView tv_esc_login, user_name, account_balance;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.userinfo, container, false);
		initView(view);
		initData();
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	public void initView(View v) {
		rl_repassword = (RelativeLayout) v.findViewById(R.id.rl_repassword);
		rl_bet_record = (RelativeLayout) v.findViewById(R.id.rl_bet_record);
		rl_userinfo = (RelativeLayout) v.findViewById(R.id.rl_userinfo);
		tv_esc_login = (TextView) v.findViewById(R.id.exit_loading);
		tv_esc_login.setOnClickListener(this);
		account_information = (RelativeLayout) v
				.findViewById(R.id.account_information);
		// 充值
		TextView recharge_money = (TextView) v.findViewById(R.id.recharge);
		// 提现
		TextView extract_money = (TextView) v.findViewById(R.id.extract_money);
		user_name = (TextView) v.findViewById(R.id.user_name);
		account_balance = (TextView) v.findViewById(R.id.account_balance);

		rl_bet_record.setOnClickListener(this);
		rl_repassword.setOnClickListener(this);
		rl_userinfo.setOnClickListener(this);
		account_information.setOnClickListener(this);
		recharge_money.setOnClickListener(this);
		extract_money.setOnClickListener(this);
	}

	private void initData() {
		try {
			NewUserLoginBean newUserLoginBean = (NewUserLoginBean) getArguments()
					.getSerializable("newUserLoginBean");
			if (newUserLoginBean != null) {
				user_name.setText(newUserLoginBean.getUsername());
				account_balance.setText(newUserLoginBean.getMoney());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

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
		// 账户明细
		case R.id.account_information:
			intent = new Intent(getActivity(), AccountDetailActivity.class);
			startActivity(intent);

			break;
		// 修改密码
		case R.id.rl_repassword:
			intent = new Intent(getActivity(), ModifiPasswordActivity.class);
			startActivity(intent);

			break;

		// 充值
		case R.id.recharge:
			intent = new Intent(getActivity(), RechargeMoneyActivity.class);
			startActivity(intent);

			break;
		// 提现
		case R.id.extract_money:
			intent = new Intent(getActivity(), ExtractMoneyActivity.class);
			startActivity(intent);

			break;
		case R.id.exit_loading:
			intent = new Intent(getActivity(), RecomendActivity.class);
			startActivity(intent);
			// SharedPreferences spf = getActivity().getSharedPreferences(
			// "isLogin", Context.MODE_PRIVATE);
			// spf.edit().putBoolean("isLogin", false).commit();
			SharedPrefHelper spf = SharedPrefHelper.getInstance(getActivity());
			spf.setIsLogin(false);
			break;

		default:
			break;
		}
	}
}
