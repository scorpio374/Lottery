package com.xmtq.lottery.activity;

import com.example.lottery.R;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * 个人资料
 * 
 * @author mwz123
 * 
 */
public class PersonDataActivity extends BaseActivity {

	private RadioGroup radiobtn_userinfo;
	private LinearLayout userdata_userinfo;
	private LinearLayout userdata_bankcard;
	private ImageView img_checkbank;
	private ImageButton btn_back;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.userdata_first);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
		radiobtn_userinfo = (RadioGroup) findViewById(R.id.userdata_radiogroup);
		img_checkbank = (ImageView) findViewById(R.id.img_checkbank);
		img_checkbank.setOnClickListener(this);
		userdata_userinfo = (LinearLayout) findViewById(R.id.userdata_userinfo);
		userdata_bankcard = (LinearLayout) findViewById(R.id.userdata_bank_card);
		radiobtn_userinfo
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						// switch (arg0.getId()) {
						// case R.id.radiobtn_userinfo:
						// userdata_userinfo.setVisibility(View.VISIBLE);
						// userdata_bankcard.setVisibility(View.GONE);
						// break;
						//
						// case R.id.userdata_bank_card:
						// userdata_bankcard.setVisibility(View.VISIBLE);
						// userdata_userinfo.setVisibility(View.GONE);
						// break;
						//
						// default:
						// break;
						// }
						if (arg1 == R.id.radiobtn_userinfo) {
							userdata_userinfo.setVisibility(View.VISIBLE);
							userdata_bankcard.setVisibility(View.GONE);
						} else if (arg1 == R.id.radiobtn_bank_card) {
							userdata_userinfo.setVisibility(View.GONE);
							userdata_bankcard.setVisibility(View.VISIBLE);
						}
					}
				});
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
		default:
			break;
		}
	}

}
