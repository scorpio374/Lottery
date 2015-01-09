package com.xmtq.lottery.activity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.lottery.R;

/**
 * 充值
 * 
 * @author Administrator
 * 
 */
public class RechargeMoneyActivity extends BaseActivity {

	private ImageButton btn_back;
	private LinearLayout check_bank;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.recharge);

	}

	@Override
	public void dealLogicBeforeInitView() {

	}

	@Override
	public void initView() {
		check_bank = (LinearLayout) findViewById(R.id.check_bank);
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
		// 选择银行卡
		case R.id.check_bank:
			// 首次
			
			break;
		default:
			break;
		}

	}

}
