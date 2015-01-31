package com.xmtq.lottery.activity;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.AccountDetailListAdapter;
import com.xmtq.lottery.bean.AccountDetailBean;
import com.xmtq.lottery.bean.AccountDetailResponse;
import com.xmtq.lottery.bean.ExtractCashResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;

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
			request("1000", "tq222222");
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	private void request(String drawalmoney, String password) {

		RequestMaker mRequestMaker = RequestMaker.getInstance();
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(mRequestMaker.getExtractCash(userid, password,
				drawalmoney));
		mAsyncTask.setOnCompleteListener(mOnCompleteListener);
	}

	private OnCompleteListener<ExtractCashResponse> mOnCompleteListener = new OnCompleteListener<ExtractCashResponse>() {

		@Override
		public void onComplete(ExtractCashResponse result, String resultString) {

			if (result != null) {
				if (result.errorcode.equals("0")) {
					ExtractCashResponse mResponse = result;
					Toast.makeText(ExtractMoneyActivity.this,
							"AccountDetailBean获取到了数据", 2000).show();
				} else {
					Toast.makeText(ExtractMoneyActivity.this, result.errormsg,
							2000).show();
				}
			} else {
				Toast.makeText(ExtractMoneyActivity.this, "请求错误", 2000).show();
			}
		}
	};
}
