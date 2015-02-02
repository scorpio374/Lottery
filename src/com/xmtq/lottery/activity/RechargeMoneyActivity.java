package com.xmtq.lottery.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.utils.SharedPrefHelper;

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

	@Override
	public void setContentLayout() {
		setContentView(R.layout.recharge);

	}

	@Override
	public void dealLogicBeforeInitView() {
		spfs = SharedPrefHelper.getInstance(this);
	}

	@Override
	public void initView() {
		check_bank = (LinearLayout) findViewById(R.id.check_bank);
		ImageButton back = (ImageButton) findViewById(R.id.back);
		back.setOnClickListener(this);

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
			if (search_edit.length() == 0) {
				Toast.makeText(RechargeMoneyActivity.this, "请输入充值金额", 2000)
						.show();
				return;
			} else {
				// 首次

				// if(spfs.getFirstLogin()){
				// Intent i=new Intent(RechargeMoneyActivity.this, cls)
				// }
			}

			break;
		default:
			break;
		}

	}
}
