package com.xmtq.lottery.activity;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.bean.BaseResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.utils.SharedPrefHelper;
import com.xmtq.lottery.utils.StringUtil;

/**
 * 登录
 * 
 * @author mwz123
 * 
 */
public class LoginActivity extends BaseActivity {

	private TextView find_password;
	private TextView register;
	private TextView login;
	private ImageButton btn_back;
	private SharedPrefHelper spfs;
	private CheckBox remember_passwod;
	private EditText user_name;
	private EditText password;

	private Toast toast;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.login);

	}

	@Override
	public void dealLogicBeforeInitView() {
		spfs = SharedPrefHelper.getInstance(LoginActivity.this);
		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
	}

	@Override
	public void initView() {
		find_password = (TextView) findViewById(R.id.find_password);
		register = (TextView) findViewById(R.id.register);
		login = (TextView) findViewById(R.id.login);
		btn_back = (ImageButton) findViewById(R.id.back);
		remember_passwod = (CheckBox) findViewById(R.id.remember_passwod);
		user_name = (EditText) findViewById(R.id.user_name);
		password = (EditText) findViewById(R.id.password);

		find_password.setOnClickListener(this);
		register.setOnClickListener(this);
		login.setOnClickListener(this);
		btn_back.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {
		// TODO Auto-generated method stub

	}

	/**
	 * 用户登陆
	 */
	private void login() {
		String userName = user_name.getText().toString().trim();
		String passward = password.getText().toString().trim();

		if (StringUtil.isNullOrEmpty(userName)) {
			toast.setText("请输入用户名");
			toast.show();
			return;
		}

		if (StringUtil.isNullOrEmpty(passward)) {
			toast.setText("请输入密码");
			toast.show();
			return;

		} else if (!StringUtil.matchPwd(passward)) {
			toast.setText("请输入6-16位密码,密码必须包含数字和字母");
			toast.show();
			return;
		}

		toast.setText("登陆中，请稍候...");
		toast.show();
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(RequestMaker.getInstance().getUserLogin(userName,
				passward));
		mAsyncTask.setOnCompleteListener(mOnLoginCompleteListener);
	}

	/**
	 * 提交注册回调处理
	 */
	private OnCompleteListener<BaseResponse> mOnLoginCompleteListener = new OnCompleteListener<BaseResponse>() {
		@Override
		public void onComplete(BaseResponse result, String resultString) {
			// TODO Auto-generated method stub
			if (result != null) {
				if (result.errorcode.equals("0")) {
					toast.setText("登陆成功");
					toast.show();

					// 登陆成功，跳转另一个页面
				} else {
					toast.setText(result.errormsg);
					toast.show();
				}
			} else {
				toast.setText("请求错误");
				toast.show();
			}
		}
	};

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
			login();
			break;
		default:
			break;
		}
	}
}
