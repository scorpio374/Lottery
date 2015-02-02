package com.xmtq.lottery.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.bean.BaseResponse;
import com.xmtq.lottery.bean.CreateOrderBean;
import com.xmtq.lottery.bean.CreateOrderResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.utils.SharedPrefHelper;
import com.xmtq.lottery.utils.StringUtil;
import com.xmtq.lottery.utils.ToastUtil;

/**
 * 充值
 * 
 * @author Administrator
 * 
 */
public class RechargeMoneyActivity extends BaseActivity {

	private LinearLayout check_bank;
	private EditText search_edit;
	private SharedPrefHelper spfs;
	private LinearLayout recharge_bank;
	private CreateOrderBean mCreateOrderBean;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.recharge);

	}

	@Override
	public void dealLogicBeforeInitView() {
		request("10");

		spfs = SharedPrefHelper.getInstance(this);
	}

	@Override
	public void initView() {

		recharge_bank = (LinearLayout) findViewById(R.id.recharge_bank);

		check_bank = (LinearLayout) findViewById(R.id.check_bank);
		ImageButton back = (ImageButton) findViewById(R.id.back);
		back.setOnClickListener(this);
		check_bank.setOnClickListener(this);
		search_edit = (EditText) findViewById(R.id.search_edit);
		RadioGroup check_money = (RadioGroup) findViewById(R.id.check_money);

		check_money.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (checkedId == R.id.rec_ten) {
					search_edit.setText("10");
				} else if (checkedId == R.id.rec_fifty) {
					search_edit.setText("50");
				} else if (checkedId == R.id.rec_hundred) {
					search_edit.setText("100");
				} else if (checkedId == R.id.rec_five_hundred) {
					search_edit.setText("500");
				}
			}
		});

	}

	@Override
	public void dealLogicAfterInitView() {

	}

	@Override
	public void onClickEvent(View view) {
		switch (view.getId()) {
		case R.id.back:
			this.finish();
			break;
		// 选择银行卡
		case R.id.check_bank:
			if (StringUtil.isNullOrEmpty(search_edit.getText().toString())) {
				Toast.makeText(RechargeMoneyActivity.this, "请输入充值金额", 2000)
						.show();
				return;
			}
			Intent intent = new Intent(RechargeMoneyActivity.this,
					CheckBankFirstActivity.class);
			intent.putExtra("mCreateOrderBean", mCreateOrderBean);
			startActivity(intent);

			break;
		default:
			break;
		}

	}

	/**
	 * 创建支付订单
	 */
	private void request(String totalPrice) {
		String userid = SharedPrefHelper.getInstance(this).getUid();
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(RequestMaker.getInstance().getFengPay(userid,
				totalPrice));
		mAsyncTask.setOnCompleteListener(mOnCompleteListener);

	}

	private OnCompleteListener<BaseResponse> mOnCompleteListener = new OnCompleteListener<BaseResponse>() {
		@Override
		public void onComplete(BaseResponse result, String resultString) {
			// TODO Auto-generated method stub
			if (result != null) {
				if (result.errorcode.equals("0")) {
					CreateOrderResponse mOrderResponse = (CreateOrderResponse) result;
					mCreateOrderBean = mOrderResponse.createOrderBean;
					if (mCreateOrderBean.getUserBankList().size() > 0) {

						recharge_bank.setVisibility(View.VISIBLE);

					}
				} else {
					ToastUtil.showCenterToast(RechargeMoneyActivity.this,
							result.errormsg);
					recharge_bank.setVisibility(View.GONE);
				}
			} else {
				ToastUtil.showCenterToast(RechargeMoneyActivity.this, "请求失败");
				recharge_bank.setVisibility(View.GONE);
			}
		}
	};

}
