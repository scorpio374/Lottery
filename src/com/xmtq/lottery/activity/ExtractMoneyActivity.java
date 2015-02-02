package com.xmtq.lottery.activity;

import java.util.List;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.AccountDetailListAdapter;
import com.xmtq.lottery.bean.AccountDetailBean;
import com.xmtq.lottery.bean.AccountDetailResponse;
import com.xmtq.lottery.bean.ExtractCashResponse;
import com.xmtq.lottery.bean.UserInfoBean;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.utils.SharedPrefHelper;
import com.xmtq.lottery.utils.StringUtil;
import com.xmtq.lottery.utils.ToastUtil;

/**
 * 提现
 * 
 * @author Administrator
 * 
 */
public class ExtractMoneyActivity extends BaseActivity {

	private ImageButton btn_back;
	// private ImageView img_checkbank;
	private TextView ectract_money_commit;
	private TextView tv_bank_name;
	private TextView bank_card_tail_num;
	private UserInfoBean userInfoBean;
	private TextView tv_balance;
	private EditText edit_extract_money;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.extract_money);

	}

	@Override
	public void dealLogicBeforeInitView() {
		userInfoBean = (UserInfoBean) getIntent().getSerializableExtra(
				"userInfoBean");
	}

	@Override
	public void initView() {
		edit_extract_money = (EditText) findViewById(R.id.edit_extract_money);
		tv_bank_name = (TextView) findViewById(R.id.bank_name);
		bank_card_tail_num = (TextView) findViewById(R.id.bank_card_tail_num);
		tv_balance = (TextView) findViewById(R.id.balance);
		String str = userInfoBean.getBankaccount();
		str = str.substring(str.length() - 4, str.length());

		tv_bank_name.setText(userInfoBean.getBankname());
		bank_card_tail_num.setText("尾号：" + str);
		tv_balance.setText(userInfoBean.getAccount());

		// img_checkbank = (ImageView) findViewById(R.id.img_checkbank);
		ectract_money_commit = (TextView) findViewById(R.id.ectract_money_commit);
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
		// img_checkbank.setOnClickListener(this);
		ectract_money_commit.setOnClickListener(this);
	}

	@Override
	public void dealLogicAfterInitView() {

	}

	@Override
	public void onClickEvent(View view) {
		Intent intent;
		switch (view.getId()) {
		case R.id.back:
			this.finish();
			break;
		// case R.id.img_checkbank:
		// intent = new Intent(ExtractMoneyActivity.this,
		// CheckBankActivity.class);
		// startActivity(intent);
		// break;
		case R.id.ectract_money_commit:

			request();

			break;
		default:
			break;
		}

	}

	private void request() {
		String drawalmoney = edit_extract_money.getText().toString().trim();
		if (TextUtils.isEmpty(drawalmoney)) {
			ToastUtil.showCenterToast(ExtractMoneyActivity.this, "请输入金额");
			return;
		}

		if (Integer.parseInt(drawalmoney) > Integer.parseInt(userInfoBean
				.getAccount())) {
			ToastUtil.showCenterToast(ExtractMoneyActivity.this, "余额不足");
			return;
		}

		String password = SharedPrefHelper.getInstance(getApplicationContext())
				.getUserPassward();
		RequestMaker mRequestMaker = RequestMaker.getInstance();
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(mRequestMaker.getExtractCash(userid, password,
				drawalmoney));
		mAsyncTask.setOnCompleteListener(mOnCompleteListener);

	}

	private OnCompleteListener<ExtractCashResponse> mOnCompleteListener = new OnCompleteListener<ExtractCashResponse>() {

		@Override
		public void onComplete(ExtractCashResponse result, String resultString) {

			if (result != null) {
				if (result.errorcode.equals("0")) {
					ExtractCashResponse mResponse = result;
					Toast.makeText(ExtractMoneyActivity.this, "提现成功", 2000)
							.show();

					Intent intent = new Intent(ExtractMoneyActivity.this,
							ExtractMoneySuccessActivity.class);
					startActivity(intent);

				} else {
					Toast.makeText(ExtractMoneyActivity.this, result.errormsg,
							2000).show();
				}
			} else {
				Toast.makeText(ExtractMoneyActivity.this, "请求错误", 2000).show();
			}
		}
	};

}
