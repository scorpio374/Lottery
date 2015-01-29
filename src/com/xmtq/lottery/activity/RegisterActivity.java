package com.xmtq.lottery.activity;

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
	private TextView send_verification;
	private TextView register_commit;
	private EditText mPhoneView;
	private EditText mPasswordView;
	private EditText mVeriCodeView;
	private EditText mUserNameView;

	private Toast toast;
	private CountDown mCountDown;
	private boolean isGetCoding = false;

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
		send_verification = (TextView) findViewById(R.id.send_verification);
		mPhoneView = (EditText) findViewById(R.id.phone_number);
		mPasswordView = (EditText) findViewById(R.id.password);
		mVeriCodeView = (EditText) findViewById(R.id.veri_code);
		mUserNameView = (EditText) findViewById(R.id.user_name);

		btn_back.setOnClickListener(this);
		register_commit.setOnClickListener(this);
		send_verification.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (mCountDown != null) {
			mCountDown.cancel();
		}
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

		// 发送验证码
		case R.id.send_verification:
			getVeriCode();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 获取验证码
	 */
	public void getVeriCode() {
		if (isGetCoding) {
			return;
		}

		String phoneNum = mPhoneView.getText().toString().trim();
		if (Util.isMobileNO(phoneNum)) {
			HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
			mAsyncTask.execute(mRequestMaker.getMessageVerification(phoneNum,
					Consts.PHONE_REGISTER_VERI));
			mAsyncTask.setOnCompleteListener(mOnCodeCompleteListener);
			toast.setText("验证码已发送，请注意查收");
			toast.show();
		} else {
			toast.setText("请输入手机号码");
			toast.show();
		}
	}

	/**
	 * 获取验证码回调处理
	 */
	private OnCompleteListener<BaseResponse> mOnCodeCompleteListener = new OnCompleteListener<BaseResponse>() {
		@Override
		public void onComplete(BaseResponse result, String resultString) {
			// TODO Auto-generated method stub
			if (result != null) {
				if (result.errorcode.equals("0")) {
					isGetCoding = true;
					mCountDown = new CountDown(60000, 1000);
					mCountDown.start();
				} else {
					toast.setText(result.errormsg);
					toast.show();
				}
			} else {
				toast.setText("获取验证码失败！");
				toast.show();
			}
		}
	};

	/**
	 * 提交注册
	 */
	private void commitRegister() {
		// TODO Auto-generated method stub

		String userName = mUserNameView.getText().toString().trim();
		String phoneNum = mPhoneView.getText().toString().trim();
		String password = mPasswordView.getText().toString().trim();
		String veriCode = mVeriCodeView.getText().toString().trim();
		// String userImei = VersionUtil.getIMEI(this);//手机唯一识别码

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

		if (StringUtil.isNullOrEmpty(veriCode)) {
			toast.setText("请获取验证码");
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

		toast.setText("注册中，请稍候...");
		toast.show();
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(mRequestMaker.getUserRegister(userName, "",
				password, phoneNum, "1111111111", Consts.PHONE_REGISTER,
				veriCode));
		mAsyncTask.setOnCompleteListener(mOnRegisterCompleteListener);
	}

	/**
	 * 提交注册回调处理
	 */
	private OnCompleteListener<BaseResponse> mOnRegisterCompleteListener = new OnCompleteListener<BaseResponse>() {
		@Override
		public void onComplete(BaseResponse result, String resultString) {
			// TODO Auto-generated method stub
			if (result != null) {
				if (result.errorcode.equals("0")) {
					isGetCoding = true;
					toast.setText("注册成功");
					toast.show();

					if (mCountDown != null) {
						mCountDown.cancel();
					}

					// 注册成功，返回前一个页面
					finish();
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

	/**
	 * 获取验证码时间计时
	 */
	private class CountDown extends CountDownTimer {

		public CountDown(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			send_verification.setText("重获验证码("
					+ StringUtil.getTime(millisUntilFinished) + ")"); // 将剩余的时间
			send_verification.setBackgroundResource(R.color.black);
			send_verification.setClickable(false);
			// 显示出来
		}

		@Override
		public void onFinish() {
			send_verification.setText("发送验证码");
			send_verification.setClickable(true);
			send_verification.setBackgroundResource(R.color.green);
			isGetCoding = false;
		}
	}
}
