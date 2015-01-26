package com.xmtq.lottery.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lottery.R;

/**
 * 注册
 * 
 * @author mwz123
 * 
 */
public class RegisterActivity extends BaseActivity {

	private ImageButton btn_back;
	private TextView register_commit;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.register);

	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		btn_back = (ImageButton) findViewById(R.id.back);
		btn_back.setOnClickListener(this);
		register_commit = (TextView) findViewById(R.id.register_commit);
		register_commit.setOnClickListener(this);
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
		case R.id.register_commit:
			Intent intent = new Intent(RegisterActivity.this,
					RecomendActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
