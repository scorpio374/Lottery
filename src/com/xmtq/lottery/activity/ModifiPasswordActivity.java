package com.xmtq.lottery.activity;

import com.example.lottery.R;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ModifiPasswordActivity extends BaseActivity {
	private ImageButton btn_back;
	private TextView repassword_commit;

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

		repassword_commit = (TextView) findViewById(R.id.repassword_commit);
		repassword_commit.setOnClickListener(this);
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
		case R.id.repassword_commit:
			Intent intent = new Intent(ModifiPasswordActivity.this,
					RecomendActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

}
