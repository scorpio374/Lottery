package com.xmtq.lottery.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.Consts;
import com.xmtq.lottery.bean.CheckUserResponse;
import com.xmtq.lottery.bean.UserBean;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.utils.StringUtil;
import com.xmtq.lottery.utils.ToastUtil;
import com.xmtq.lottery.utils.Util;

/**
 * 注册
 * 
 * @author mwz123
 * 
 */
public class RegisterActivity extends BaseActivity {

	private ImageButton btn_back;
	private TextView register_commit;
	private EditText mPhoneView;
	private EditText mPasswordView;
	private EditText mUserNameView;
	private UserBean userBean;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.register);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub
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
			ToastUtil.showCenterToast(this, "请输入用户名");
			return;
		}

		if (!Util.isMobileNO(phoneNum)) {
			ToastUtil.showCenterToast(this, "请输入正确的手机号码");
			return;
		}

		if (StringUtil.isNullOrEmpty(password)) {
			ToastUtil.showCenterToast(this, "请输入密码");
			return;
		} else if (!StringUtil.matchPwd(password)) {
			ToastUtil.showCenterToast(this, "请输入6-15位密码,密码必须包含数字和字母");
			return;
		}

		requestCheckUser(userName, phoneNum);

		userBean = new UserBean();
		userBean.setPassword(password);
		userBean.setPhoneNum(phoneNum);
		userBean.setUsername(userName);

	}

	/**
	 * 测试检查用户名是否存在
	 * 
	 * @param username
	 * @param phoneNum
	 */
	private void requestCheckUser(String username, String phoneNum) {
		//
		RequestMaker mRequestMaker = RequestMaker.getInstance();
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(mRequestMaker.getCheckUser(username, phoneNum));
		mAsyncTask.setOnCompleteListener(mtestOnCompleteListener);
	}

	// 测试检查用户名是否存在
	private OnCompleteListener<CheckUserResponse> mtestOnCompleteListener = new OnCompleteListener<CheckUserResponse>() {

		@Override
		public void onComplete(CheckUserResponse result, String resultString) {

			if (result != null) {
				if (result.errorcode.equals("0")) {
					// PurchaseRecordsResponse mResponse = result;
					CheckUserResponse mResponse = result;

					if (mResponse.checkUserBean.getUstate().equals("1")) {
						Toast.makeText(RegisterActivity.this, "用户名已经存在", 2000)
								.show();
						return;
					}

					if (mResponse.checkUserBean.getPstate().equals("1")) {
						Toast.makeText(RegisterActivity.this, "手机号码已经存在", 2000)
								.show();
						return;
					}

					Intent intent = new Intent(RegisterActivity.this,
							RegisterSecondActivity.class);
					intent.putExtra("userBean", userBean);
					startActivity(intent);

					//
					// List<PurchaseRecordsBean> mRecordsBeansList =
					// mResponse.purchaseRecordsBeans;
					// // List<String> mList = new ArrayList<String>();
					// // for (int i = 0; i < 10; i++) {
					// // mList.add(i + "");
					// // }
					// BetRecordListAdapter mAdapter = new BetRecordListAdapter(
					// BetRecordActivity.this, mRecordsBeansList);
					// bet_record_all.setAdapter(mAdapter);
					// bet_record_wait.setAdapter(mAdapter);
					// bet_record_win.setAdapter(mAdapter);

				} else {
					Toast.makeText(RegisterActivity.this, result.errormsg, 2000)
							.show();
				}
			} else {
				Toast.makeText(RegisterActivity.this, Consts.REQUEST_ERROR,
						2000).show();
			}
		}
	};

}
