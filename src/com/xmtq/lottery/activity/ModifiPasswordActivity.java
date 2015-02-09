package com.xmtq.lottery.activity;

import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.bean.BaseResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.utils.SharedPrefHelper;
import com.xmtq.lottery.utils.StringUtil;

public class ModifiPasswordActivity extends BaseActivity {
	private ImageButton btn_back;
	private TextView repassword_commit;
	private EditText old_password;
	private EditText new_password;
	private EditText confirm_password;

	private Toast toast;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.repassword);
	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub
		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
	}

	@Override
	public void initView() {
		btn_back = (ImageButton) findViewById(R.id.back);
		repassword_commit = (TextView) findViewById(R.id.repassword_commit);
		old_password = (EditText) findViewById(R.id.old_password);
		new_password = (EditText) findViewById(R.id.new_password);
		confirm_password = (EditText) findViewById(R.id.confirm_password);

		btn_back.setOnClickListener(this);
		repassword_commit.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClickEvent(View view) {
		switch (view.getId()) {
		case R.id.back:
			this.finish();
			break;
		case R.id.repassword_commit:
			commit();
			break;
		default:
			break;
		}

	}
	
	/**
	 * 修改密码
	 */
	private void commit() {
		// TODO Auto-generated method stub
		String uid = SharedPrefHelper.getInstance(this).getUid();
		String oldPassword = old_password.getText().toString().trim();
		String newPassword = new_password.getText().toString().trim();
		String confirmPassword = confirm_password.getText().toString().trim();

		if (StringUtil.isNullOrEmpty(oldPassword)) {
			toast.setText("请输入原密码");
			toast.show();
			return;
		}

		if (StringUtil.isNullOrEmpty(newPassword)) {
			toast.setText("请输入新密码");
			toast.show();
			return;
		} else if (!StringUtil.matchPwd(newPassword)) {
			// toast.setText("请输入6-16位密码,新密码必须包含数字和字母");
			// toast.show();
			// return;
		}

		if (StringUtil.isNullOrEmpty(confirmPassword)
				|| !confirmPassword.equals(newPassword)) {
			toast.setText("两次输入新密码不一致，请重新输入确认");
			toast.show();
			return;
		}
		
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(RequestMaker.getInstance().getModifyPassword(uid, oldPassword, newPassword));
		mAsyncTask.setOnCompleteListener(mOnCompleteListener);
	}
	
	/**
	 * 修改密码回调处理
	 */
	private OnCompleteListener<BaseResponse> mOnCompleteListener = new OnCompleteListener<BaseResponse>() {
		@Override
		public void onComplete(BaseResponse result, String resultString) {
			// TODO Auto-generated method stub
			if (result != null) {
				if (result.errorcode.equals("0")) {
					toast.setText("密码修改成功");
					toast.show();

					// 修改成功，返回前一个页面
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

}
