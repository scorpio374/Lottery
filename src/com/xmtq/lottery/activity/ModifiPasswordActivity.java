package com.xmtq.lottery.activity;

import com.example.lottery.R;

import android.view.View;
import android.widget.ImageButton;

public class ModifiPasswordActivity extends BaseActivity {
	private ImageButton btn_back;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.repassword);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);

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

		default:
			break;
		}

	}

}
