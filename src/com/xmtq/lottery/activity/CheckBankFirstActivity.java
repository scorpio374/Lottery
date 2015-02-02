package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.BetOrderDetailListAdapter;
import com.xmtq.lottery.adapter.BetRecordListAdapter;
import com.xmtq.lottery.bean.CreateOrderBean;
import com.xmtq.lottery.utils.ToastUtil;

/**
 * 选择银行卡或者信用卡
 * 
 * @author Administrator
 * 
 */
public class CheckBankFirstActivity extends BaseActivity {

	private ImageButton btn_back;
	private ImageView iv_checkBank;
	private CreateOrderBean mCreateOrderBean;
	private LinearLayout ll_bank_c;
	private EditText check_bank;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.check_bankcard);

	}

	@Override
	public void dealLogicBeforeInitView() {
		mCreateOrderBean = (CreateOrderBean) getIntent().getSerializableExtra(
				"mCreateOrderBean");
	}

	@Override
	public void initView() {
		ll_bank_c = (LinearLayout) findViewById(R.id.ll_bank_c);

		iv_checkBank = (ImageView) findViewById(R.id.img_checkbank);
		iv_checkBank.setOnClickListener(this);
		check_bank = (EditText) findViewById(R.id.check_bank);
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
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
		case R.id.img_checkbank:
			Intent intent = new Intent(CheckBankFirstActivity.this,
					CheckBankActivity.class);
			intent.putExtra("mCreateOrderBean", mCreateOrderBean);
			startActivityForResult(intent, 0xffff);
			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 1 * 1000) {
			// mType = "Y";
			// sendMessage();
			ll_bank_c.setVisibility(View.VISIBLE);

		} else {
			ll_bank_c.setVisibility(View.GONE);
		}
		String bankName = data.getStringExtra("bankName");
		check_bank.setText(bankName);
		super.onActivityResult(requestCode, resultCode, data);
	}
}
