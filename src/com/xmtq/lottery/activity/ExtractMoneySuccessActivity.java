package com.xmtq.lottery.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lottery.R;

/**
 * 提现成功
 * 
 * @author mwz123
 * 
 */
public class ExtractMoneySuccessActivity extends BaseActivity {

	private ImageButton btn_back;
	private TextView extract_money_done;
	private TextView extract_money;
	private String drawalmoney;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.extract_money_success);

	}

	@Override
	public void dealLogicBeforeInitView() {
		drawalmoney = getIntent().getStringExtra("drawalmoney");

	}

	@Override
	public void initView() {
		extract_money = (TextView) findViewById(R.id.extract_money);
		if (!TextUtils.isEmpty(drawalmoney)) {
			extract_money.setText(drawalmoney);
		}
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
		extract_money_done = (TextView) findViewById(R.id.extract_money_done);
		extract_money_done.setOnClickListener(this);
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
		case R.id.extract_money_done:
			// Intent intent = new Intent(ExtractMoneySuccessActivity.this,
			// RecomendActivity.class);
			// startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}

}
