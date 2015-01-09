package com.xmtq.lottery.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.lottery.R;

/**
 * 提现
 * 
 * @author Administrator
 * 
 */
public class ExtractMoneyActivity extends BaseActivity {

	private ImageButton btn_back;
	private ImageView img_checkbank;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.extract_money);

	}

	@Override
	public void dealLogicBeforeInitView() {

	}

	@Override
	public void initView() {
		img_checkbank = (ImageView) findViewById(R.id.img_checkbank);
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
		img_checkbank.setOnClickListener(this);
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
			Intent intent = new Intent(ExtractMoneyActivity.this,
					CheckBankActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

}
