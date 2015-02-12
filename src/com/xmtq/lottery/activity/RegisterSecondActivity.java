package com.xmtq.lottery.activity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lottery.R;
import com.xmtq.lottery.Consts;
import com.xmtq.lottery.bean.BaseResponse;
import com.xmtq.lottery.bean.UserBean;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.utils.StringUtil;
import com.xmtq.lottery.utils.ToastUtil;
import com.xmtq.lottery.utils.Util;

/**
 * 注册
 * 
 * @author mwz123
 * 
 */
public class RegisterSecondActivity extends BaseActivity {

	private RequestMaker mRequestMaker;
	private ImageButton btn_back;
	private TextView send_verification;
	private TextView register_commit;
	private EditText mVeriCodeView;

	private CountDown mCountDown;
	private boolean isGetCoding = false;
	private UserBean userBean;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.register_second);

	}

	@Override
	public void dealLogicBeforeInitView() {
		userBean = (UserBean) getIntent().getSerializableExtra("userBean");

		mRequestMaker = RequestMaker.getInstance();
	}

	@Override
	public void initView() {
		btn_back = (ImageButton) findViewById(R.id.back);
		register_commit = (TextView) findViewById(R.id.register_commit);
		send_verification = (TextView) findViewById(R.id.send_verification);
		mVeriCodeView = (EditText) findViewById(R.id.veri_code);
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
		String phoneNum = "";
		if (userBean != null && userBean.getPhoneNum() != null) {

			phoneNum = userBean.getPhoneNum();
		}
		if (Util.isMobileNO(phoneNum)) {
			HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
			mAsyncTask.execute(mRequestMaker.getMessageVerification(phoneNum,
					Consts.PHONE_REGISTER_VERI));
			mAsyncTask.setOnCompleteListener(mOnCodeCompleteListener);
			ToastUtil.showCenterToast(this, "验证码已发送，请注意查收");
		} else {
			ToastUtil.showCenterToast(this, "请输入正确的手机号码");
			finish();
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
					ToastUtil.showCenterToast(RegisterSecondActivity.this, result.errormsg);
				}
			} else {
				ToastUtil.showCenterToast(RegisterSecondActivity.this, "获取验证码失败！");
			}
		}
	};

	/**
	 * 提交注册
	 */
	private void commitRegister() {
		// TODO Auto-generated method stub

		String veriCode = mVeriCodeView.getText().toString().trim();

		if (StringUtil.isNullOrEmpty(veriCode)) {
			ToastUtil.showCenterToast(this, "请获取验证码");
			return;
		}

		mLoadingDialog.show("提交数据中，请稍候");
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(mRequestMaker.getUserRegister(
				userBean.getUsername(), "", userBean.getPassword(),
				userBean.getPhoneNum(), "1111111111", Consts.PHONE_REGISTER,
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
					ToastUtil.showCenterToast(RegisterSecondActivity.this, "注册成功");

					if (mCountDown != null) {
						mCountDown.cancel();
					}

					// 注册成功，返回前一个页面
					finish();
				} else {
					ToastUtil.showCenterToast(RegisterSecondActivity.this, result.errormsg);
				}
			} else {
				ToastUtil.showCenterToast(RegisterSecondActivity.this, Consts.REQUEST_ERROR);
			}

			mLoadingDialog.dismiss();
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
