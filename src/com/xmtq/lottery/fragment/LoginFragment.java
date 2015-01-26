package com.xmtq.lottery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lottery.R;
import com.xmtq.lottery.activity.FindPasswordActivity;
import com.xmtq.lottery.activity.RecomendActivity;
import com.xmtq.lottery.activity.RegisterActivity;
import com.xmtq.lottery.utils.SharedPrefHelper;

/**
 * 登录
 * 
 * @author mwz123
 * 
 */
public class LoginFragment extends BaseFragment {

	private TextView find_password;
	private TextView register;
	private ImageButton btn_back;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.login, container, false);
		initView(view);
		return view;
	}

	public void initView(View v) {
		find_password = (TextView) v.findViewById(R.id.find_password);
		find_password.setOnClickListener(this);
		register = (TextView) v.findViewById(R.id.register);
		register.setOnClickListener(this);

		TextView login = (TextView) v.findViewById(R.id.login);
		login.setOnClickListener(this);

		// btn_back = (ImageButton) v.findViewById(R.id.back);
		// btn_back.setOnClickListener(this);
	}

	@Override
	public void onClickEvent(View view) {
		Intent intent;
		switch (view.getId()) {
		// case R.id.back:
		// getActivity().finish();
		// break;
		case R.id.find_password:
			intent = new Intent(getActivity(), FindPasswordActivity.class);
			startActivity(intent);
			break;
		case R.id.register:
			intent = new Intent(getActivity(), RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.login:

			SharedPrefHelper spf = SharedPrefHelper.getInstance(getActivity());
			spf.setIsLogin(true);

			intent = new Intent(getActivity(), RecomendActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
