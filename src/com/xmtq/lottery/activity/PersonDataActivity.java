package com.xmtq.lottery.activity;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.bean.BaseResponse;
import com.xmtq.lottery.bean.ImproveUserInfoBean;
import com.xmtq.lottery.bean.ImproveUserInfoResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.utils.SharedPrefHelper;
import com.xmtq.lottery.utils.StringUtil;

/**
 * 个人资料
 * 
 * @author mwz123
 * 
 */
public class PersonDataActivity extends BaseActivity {

	private Toast toast;
	private RadioGroup radiobtn_userinfo;
	private LinearLayout userdata_userinfo;
	private LinearLayout userdata_bankcard;
	private ImageView img_checkbank;
	private ImageButton btn_back;
	private TextView userinfo_commit;

	// 个人信息
	private EditText id_card;
	private EditText real_name;

	// 银行卡信息
	private EditText bankcard_person;
	private EditText bankcard_person_id;
	private EditText bank_name;
	private EditText bankcard_id;
	private EditText bank_address;
	private EditText user_password;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.userdata_first);

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
		radiobtn_userinfo = (RadioGroup) findViewById(R.id.userdata_radiogroup);
		img_checkbank = (ImageView) findViewById(R.id.img_checkbank);
		userdata_userinfo = (LinearLayout) findViewById(R.id.userdata_userinfo);
		userdata_bankcard = (LinearLayout) findViewById(R.id.userdata_bank_card);
		userinfo_commit = (TextView) findViewById(R.id.userinfo_commit);
		id_card = (EditText) findViewById(R.id.id_card);
		real_name = (EditText) findViewById(R.id.real_name);

		bankcard_person = (EditText) findViewById(R.id.bankcard_person);
		bankcard_person_id = (EditText) findViewById(R.id.bankcard_person_id);
		bank_name = (EditText) findViewById(R.id.bank_name);
		bankcard_id = (EditText) findViewById(R.id.bankcard_id);
		bank_address = (EditText) findViewById(R.id.bank_address);
		user_password = (EditText) findViewById(R.id.password);

		btn_back.setOnClickListener(this);
		img_checkbank.setOnClickListener(this);
		userinfo_commit.setOnClickListener(this);
		radiobtn_userinfo
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						if (arg1 == R.id.radiobtn_userinfo) {
							userdata_userinfo.setVisibility(View.VISIBLE);
							userdata_bankcard.setVisibility(View.GONE);
						} else if (arg1 == R.id.radiobtn_bank_card) {
							userdata_userinfo.setVisibility(View.GONE);
							userdata_bankcard.setVisibility(View.VISIBLE);
						}
					}
				});
		
		// 获取用户数据
		getUserData();
	}

	@Override
	public void dealLogicAfterInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClickEvent(View view) {
		switch (view.getId()) {
		case R.id.img_checkbank:
			Intent intent = new Intent(PersonDataActivity.this,
					CheckBankActivity.class);
			startActivity(intent);
			break;

		case R.id.back:
			this.finish();
			break;

		case R.id.userinfo_commit:
			if (userdata_userinfo.getVisibility() == View.VISIBLE) {
				commitUserInfo();
			} else if (userdata_bankcard.getVisibility() == View.VISIBLE) {
				commitBankcard();
			}
			break;

		default:
			break;
		}
	}
	
	/**
	 * 获取用户数据
	 */
	private void getUserData(){
		id_card.setText(SharedPrefHelper.getInstance(this).getCardId());
		real_name.setText(SharedPrefHelper.getInstance(this).getRealName());
		
		bankcard_person.setText(SharedPrefHelper.getInstance(this).getRealName());
		bankcard_person_id.setText(SharedPrefHelper.getInstance(this).getCardId());
		bank_name.setText(SharedPrefHelper.getInstance(this).getBankName());
		bankcard_id.setText(SharedPrefHelper.getInstance(this).getBankCardId());
		bank_address.setText(SharedPrefHelper.getInstance(this).getBankAddress());
		user_password.setText(SharedPrefHelper.getInstance(this).getPassword());
	}
	
	/**
	 * 保存用户数据
	 */
	private void saveUserData(){
		SharedPrefHelper.getInstance(this).setRealName(bankcard_person.getText().toString().trim());
		SharedPrefHelper.getInstance(this).setCardId(bankcard_person_id.getText().toString().trim());
		SharedPrefHelper.getInstance(this).setBankName(bank_name.getText().toString().trim());
		SharedPrefHelper.getInstance(this).setBankCardId(bankcard_id.getText().toString().trim());
		SharedPrefHelper.getInstance(this).setBankAddress(bank_address.getText().toString().trim());
		SharedPrefHelper.getInstance(this).setPassword(user_password.getText().toString().trim());
	}
	

	/**
	 * 提交个人信息
	 */
	private void commitUserInfo() {
		// TODO Auto-generated method stub
		String uid = SharedPrefHelper.getInstance(this).getUid();
		String idCard = id_card.getText().toString().trim();
		String realName = real_name.getText().toString().trim();
		SharedPrefHelper.getInstance(this).setRealName(realName);
		SharedPrefHelper.getInstance(this).setCardId(idCard);
		
		if (StringUtil.isNullOrEmpty(idCard)) {
			toast.setText("请输入身份证号");
			toast.show();
			return;
		} else if (!StringUtil.matchPwd(idCard)) {
			// 判断身份证号是否合法
		}

		if (StringUtil.isNullOrEmpty(realName)) {
			toast.setText("请输入真实姓名");
			toast.show();
			return;
		}

		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(RequestMaker.getInstance().getPerfectUserInfo(uid,
				realName, idCard));
		mAsyncTask.setOnCompleteListener(mOnCompleteListener);
	}

	/**
	 * 提交银行卡信息
	 */
	private void commitBankcard() {
		// TODO Auto-generated method stub
		String uid = SharedPrefHelper.getInstance(this).getUid();
		String bankCardPerson = bankcard_person.getText().toString().trim();
		String bankCardPersonId = bankcard_person_id.getText().toString()
				.trim();
		String bankName = bank_name.getText().toString().trim();
		String bankCardId = bankcard_id.getText().toString().trim();
		String bankAddress = bank_address.getText().toString().trim();
		String password = user_password.getText().toString().trim();
		
		// 保存用户数据 仅用于测试
		saveUserData();

		if (StringUtil.isNullOrEmpty(bankCardPerson)) {
			toast.setText("请输入持卡人姓名");
			toast.show();
			return;
		}

		if (StringUtil.isNullOrEmpty(bankCardPersonId)) {
			toast.setText("请输入持卡人身份证号");
			toast.show();
			return;
		}

		if (StringUtil.isNullOrEmpty(bankName)) {
			toast.setText("请输入银行名称");
			toast.show();
			return;
		}

		if (StringUtil.isNullOrEmpty(bankCardId)) {
			toast.setText("请输入银行卡号");
			toast.show();
			return;
		}

		if (StringUtil.isNullOrEmpty(bankAddress)) {
			toast.setText("请输入开户地");
			toast.show();
			return;
		}

		if (StringUtil.isNullOrEmpty(password)) {
			toast.setText("请输入密码");
			toast.show();
			return;
		}

		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(RequestMaker.getInstance().getPerfectUserInfo(uid,
				bankCardPerson, bankCardPersonId, "", bankName, bankCardId,
				bankAddress, password));
		mAsyncTask.setOnCompleteListener(mOnCompleteListener);
	}

	/**
	 * 完善个人信息回调
	 */
	private OnCompleteListener<BaseResponse> mOnCompleteListener = new OnCompleteListener<BaseResponse>() {
		@Override
		public void onComplete(BaseResponse result, String resultString) {
			// TODO Auto-generated method stub
			if (result != null) {
				if (result.errorcode.equals("0")) {
					toast.setText("完善个人信息成功");
					toast.show();

					ImproveUserInfoResponse response = (ImproveUserInfoResponse) result;
					ImproveUserInfoBean improveUserInfoBean = response.improveUserInfoBean;
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
