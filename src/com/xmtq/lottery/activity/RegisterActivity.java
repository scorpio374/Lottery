package com.xmtq.lottery.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.Consts;
import com.xmtq.lottery.bean.BaseResponse;
import com.xmtq.lottery.bean.UserBean;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.utils.StringUtil;
import com.xmtq.lottery.utils.Util;

/**
 * 注册
 * 
 * @author mwz123
 * 
 */
public class RegisterActivity extends BaseActivity {

	private RequestMaker mRequestMaker;
	private ImageButton btn_back;
	private TextView register_commit;
	private EditText mPhoneView;
	private EditText mPasswordView;
	private EditText mUserNameView;

	private Toast toast;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.register);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub
		mRequestMaker = RequestMaker.getInstance();
		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
	}

	@Override
	public void initView() {
		btn_back = (ImageButton) findViewById(R.id.back);
		register_commit = (TextView) findViewById(R.id.register_commit);
		mPhoneView = (EditText) findViewById(R.id.phone_number);
		mPasswordView = (EditText) findViewById(R.id.password);
		mUserNameView = (EditText) findViewById(R.id.user_name);

		btn_back.setOnClickListener(this);
		register_commit.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClickEvent(View view) {
		switch (view.getId()) {
		case R.id.back:
			this.finish();
			break;

		// 注册提交
		case R.id.register_commit:
			commitRegister();
			break;

		default:
			break;
		}
	}
	

	/**
	 * 提交注册
	 */
	private void commitRegister() {
		// TODO Auto-generated method stub

		String userName = mUserNameView.getText().toString().trim();
		String phoneNum = mPhoneView.getText().toString().trim();
		String password = mPasswordView.getText().toString().trim();

		if (StringUtil.isNullOrEmpty(userName)) {
			toast.setText("请输入用户名");
			toast.show();
			return;
		}

		if (!Util.isMobileNO(phoneNum)) {
			toast.setText("请输入正确的手机号码");
			toast.show();
			return;
		}


		if (StringUtil.isNullOrEmpty(password)) {
			toast.setText("请输入密码");
			toast.show();
			return;

		} else if (!StringUtil.matchPwd(password)) {
			toast.setText("请输入6-16位密码,密码必须包含数字和字母");
			toast.show();
			return;
		}
		
		UserBean userBean=new UserBean();
		userBean.setPassword(password);
		userBean.setPhoneNum(phoneNum);
		userBean.setUsername(userName);
		
		
		Intent intent=new Intent(RegisterActivity.this, RegisterSecondActivity.class);
		intent.putExtra("userBean", userBean);
		startActivity(intent);
	}

}
