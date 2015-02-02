package com.xmtq.lottery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.activity.AccountDetailActivity;
import com.xmtq.lottery.activity.BetRecordActivity;
import com.xmtq.lottery.activity.ExtractMoneyActivity;
import com.xmtq.lottery.activity.ModifiPasswordActivity;
import com.xmtq.lottery.activity.PersonDataActivity;
import com.xmtq.lottery.activity.RechargeMoneyActivity;
import com.xmtq.lottery.activity.RecomendActivity;
import com.xmtq.lottery.bean.BaseResponse;
import com.xmtq.lottery.bean.NewUserLoginBean;
import com.xmtq.lottery.bean.UserInfoBean;
import com.xmtq.lottery.bean.UserInfoResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.utils.SharedPrefHelper;
import com.xmtq.lottery.utils.ToastUtil;

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
	private Toast toast;

	private UserInfoBean userInfoBean;
	private boolean isAddUserInfo = false;
	private boolean isAddBankInfo = false;

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
		requestUserData();
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

	/**
	 * 从其他Fragment传送过来的用户数据
	 */
	private void initData() {
		try {
			NewUserLoginBean newUserLoginBean = (NewUserLoginBean) getArguments()
					.getSerializable("newUserLoginBean");
			if (newUserLoginBean != null) {
				user_name.setText(newUserLoginBean.getUsername());
				account_balance.setText(newUserLoginBean.getMoney());

				SharedPrefHelper.getInstance(getActivity()).setUserName(
						newUserLoginBean.getUsername());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户信息
	 */
	private void requestUserData() {
		toast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);

		String uid = SharedPrefHelper.getInstance(getActivity()).getUid();
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(RequestMaker.getInstance().getUserInfo(uid));
		mAsyncTask.setOnCompleteListener(mOnCompleteListener);
	}

	/**
	 * 获取用户信息
	 */
	private OnCompleteListener<BaseResponse> mOnCompleteListener = new OnCompleteListener<BaseResponse>() {
		@Override
		public void onComplete(BaseResponse result, String resultString) {
			// TODO Auto-generated method stub
			if (result != null) {
				if (result.errorcode.equals("0")) {

					onSuccess(result);
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

	private void onSuccess(BaseResponse result) {
		UserInfoResponse response = (UserInfoResponse) result;
		userInfoBean = response.userInfoBean;
		checkUserInfo(userInfoBean);
		checkBankInfo(userInfoBean);
		// SharedPrefHelper.getInstance(getActivity()).setRealName(userInfoBean.getRealname());
		// SharedPrefHelper.getInstance(getActivity()).setCardId(userInfoBean.getCardid());
	}

	@Override
	public void onClickEvent(View v) {
		Intent intent;
		switch (v.getId()) {
		// 投注记录
		case R.id.rl_bet_record:
			// intent = new Intent(getActivity(), BetRecordActivity.class);
			// 测试推荐历史
			intent = new Intent(getActivity(), BetRecordActivity.class);
			startActivity(intent);

			break;
		// 个人资料
		case R.id.rl_userinfo:
			intent = new Intent(getActivity(), PersonDataActivity.class);
			intent.putExtra("userInfoBean", userInfoBean);
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
			if (isAddBankInfo && isAddUserInfo) {
				intent = new Intent(getActivity(), ExtractMoneyActivity.class);
				intent.putExtra("userInfoBean", userInfoBean);
				startActivity(intent);
			} else {
				ToastUtil.showCenterToast(getActivity(), "请完善个人信息");
				intent = new Intent(getActivity(), PersonDataActivity.class);
				startActivity(intent);
			}

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

	/**
	 * 判断是否添加用户信息
	 */
	private void checkUserInfo(UserInfoBean userInfoBean) {
		String realName = userInfoBean.getRealname();
		String cardId = userInfoBean.getCardid();

		if (isValidValue(realName) && isValidValue(cardId)) {
			isAddUserInfo = true;
		}
	}

	/**
	 * 判断是否添加银行卡信息
	 */
	private void checkBankInfo(UserInfoBean userInfoBean) {
		String bankName = userInfoBean.getBankname();
		String bankAcount = userInfoBean.getBankaccount();
		String bankAddress = userInfoBean.getBankaddress();
		String acount = userInfoBean.getAccount(); // 帐户余额

		if (isValidValue(bankName) && isValidValue(bankAcount)
				&& isValidValue(bankAddress)) {
			isAddBankInfo = true;
		}
	}

	public boolean isValidValue(String vaule) {
		if (TextUtils.isEmpty(vaule) || vaule.equals("*")) {
			return false;
		}
		return true;
	}
}
