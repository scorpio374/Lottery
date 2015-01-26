package com.xmtq.lottery.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lottery.R;
import com.xmtq.lottery.utils.SharedPrefHelper;

/**
 * 登录
 * 
 * @author mwz123
 * 
 */
public class LoginActivity extends BaseActivity {

	private TextView find_password;
	private TextView register;
	private ImageButton btn_back;
	private SharedPrefHelper spfs;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.login);

	}

	@Override
	public void dealLogicBeforeInitView() {
		spfs = SharedPrefHelper.getInstance(LoginActivity.this);

	}

	@Override
	public void initView() {
		find_password = (TextView) findViewById(R.id.find_password);
		find_password.setOnClickListener(this);
		register = (TextView) findViewById(R.id.register);
		register.setOnClickListener(this);

		TextView login = (TextView) findViewById(R.id.login);
		login.setOnClickListener(this);

		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClickEvent(View view) {
		Intent intent;
		switch (view.getId()) {
		case R.id.back:
			this.finish();
			break;
		case R.id.find_password:
			intent = new Intent(LoginActivity.this, FindPasswordActivity.class);
			startActivity(intent);
			break;
		case R.id.register:
			intent = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.login:

			// SharedPreferences spf = getSharedPreferences("isLogin",
			// Context.MODE_PRIVATE);
			// spf.edit().putBoolean("isLogin", true).commit();

			spfs.setIsLogin(true);
			intent = new Intent(LoginActivity.this, UserInfoActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
