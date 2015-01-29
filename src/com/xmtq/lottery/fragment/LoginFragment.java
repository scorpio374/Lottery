package com.xmtq.lottery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.activity.FindPasswordActivity;
import com.xmtq.lottery.activity.RegisterActivity;
import com.xmtq.lottery.bean.BaseResponse;
import com.xmtq.lottery.bean.NewUserLoginBean;
import com.xmtq.lottery.bean.NewUserLoginResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.utils.SharedPrefHelper;
import com.xmtq.lottery.utils.StringUtil;

/**
 * 登录
 * 
 * @author mwz123
 * 
 */
public class LoginFragment extends BaseFragment {

	private TextView find_password;
	private TextView register;
	private TextView login;
	private SharedPrefHelper spfs;
	private CheckBox remember_passwod;
	private EditText user_name;
	private EditText password;
	private Toast toast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.login, container, false);
		dealLogicBeforeInitView();
		initView(view);
		return view;
	}

	public void dealLogicBeforeInitView() {
		spfs = SharedPrefHelper.getInstance(getActivity());
		toast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
	}

	public void initView(View v) {
		find_password = (TextView) v.findViewById(R.id.find_password);
		register = (TextView) v.findViewById(R.id.register);
		login = (TextView) v.findViewById(R.id.login);
		remember_passwod = (CheckBox) v.findViewById(R.id.remember_passwod);
		user_name = (EditText) v.findViewById(R.id.user_name);
		password = (EditText) v.findViewById(R.id.password);

		find_password.setOnClickListener(this);
		register.setOnClickListener(this);
		login.setOnClickListener(this);
		remember_passwod.setOnCheckedChangeListener(mOnCheckedChangeListener);
		if (spfs.getIsRememberPwd()) {
			user_name.setText(spfs.getUserName());
			password.setText(spfs.getUserPassward());
			remember_passwod.setChecked(true);
		}
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
		if (spfs.getIsRememberPwd()) {
			spfs.setUserPassward(passward);
			spfs.setUserName(userName);
		}

		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(RequestMaker.getInstance().getUserLogin(userName,
				passward));
		mAsyncTask.setOnCompleteListener(mOnLoginCompleteListener);
	}

	/**
	 * 用户登陆回调处理
	 */
	private OnCompleteListener<BaseResponse> mOnLoginCompleteListener = new OnCompleteListener<BaseResponse>() {
		@Override
		public void onComplete(BaseResponse result, String resultString) {
			// TODO Auto-generated method stub
			if (result != null) {
				NewUserLoginResponse response = (NewUserLoginResponse) result;
				NewUserLoginBean newUserLoginBean = response.newUserLoginBean;
				if (result.errorcode.equals("0")) {
					toast.setText("登陆成功");
					toast.show();

					// 登陆成功，跳转另一个页面
					spfs.setIsLogin(true);

					UserInfoFragment fragment = new UserInfoFragment();
					Bundle b = new Bundle();
					b.putSerializable("newUserLoginBean", newUserLoginBean);
					fragment.setArguments(b);
					getActivity().getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.menu_frame, fragment).commit();
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
	 * 是否记住密码
	 */
	private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			// TODO Auto-generated method stub
			if (arg1) {
				spfs.setIsRememberPwd(true);
			} else {
				spfs.setIsRememberPwd(false);
				spfs.cleanUserName();
				spfs.cleanUserPassward();
			}
		}
	};

	@Override
	public void onClickEvent(View view) {
		Intent intent;
		switch (view.getId()) {
		case R.id.find_password:
			intent = new Intent(getActivity(), FindPasswordActivity.class);
			startActivity(intent);
			break;
		case R.id.register:
			intent = new Intent(getActivity(), RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.login:
			login();

			// SharedPrefHelper spf =
			// SharedPrefHelper.getInstance(getActivity());
			// spf.setIsLogin(true);
			// intent = new Intent(getActivity(), RecomendActivity.class);
			// startActivity(intent);
			break;
		default:
			break;
		}
	}

}
