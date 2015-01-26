package com.xmtq.lottery.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
	private TextView ectract_money_commit;

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
		ectract_money_commit = (TextView) findViewById(R.id.ectract_money_commit);
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
		img_checkbank.setOnClickListener(this);
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
		case R.id.img_checkbank:
			intent = new Intent(ExtractMoneyActivity.this,
					CheckBankActivity.class);
			startActivity(intent);
			break;
		case R.id.ectract_money_commit:
			intent = new Intent(ExtractMoneyActivity.this,
					ExtractMoneySuccessActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

}
