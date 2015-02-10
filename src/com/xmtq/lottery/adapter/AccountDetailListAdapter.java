package com.xmtq.lottery.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lottery.R;
import com.xmtq.lottery.bean.AccountDetailBean;

public class AccountDetailListAdapter extends BaseAdapter {
	private Context mContext;
	private List<AccountDetailBean> mList;

	public AccountDetailListAdapter(Context c,
			List<AccountDetailBean> historyBeansList) {
		this.mContext = c;
		this.mList = historyBeansList;
	}

	@Override
	public int getCount() {

		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		Holder holder = null;
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.account_detail_list_item, null);
			holder.bet_count = (TextView) convertView
					.findViewById(R.id.bet_count);
			holder.bet_time = (TextView) convertView
					.findViewById(R.id.bet_time);
			holder.bet_style = (TextView) convertView
					.findViewById(R.id.bet_style);
			holder.bet_date = (TextView) convertView
					.findViewById(R.id.bet_date);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		String date = "";
		String[] d = mList.get(arg0).getEntertime().split("-");
		date = d[1] + "/" + d[2];

		String style = "";
		String money = "";
		if (mList.size() > 0) {
			if (mList.get(arg0).getMflag().equals("5")) {
				style = "银行提款";
				money = "- " + mList.get(arg0).getMoney() + ".00 元";
			} else if (mList.get(arg0).getMflag().equals("93")) {
				style = "快捷支付";
				money = "+ " + mList.get(arg0).getMoney() + ".00 元";
			}
			holder.bet_count.setText(money);
			holder.bet_date.setText(date);
			holder.bet_style.setText(style);

			if (arg0 > 0) {
				if (mList.get(arg0).getEntertime()
						.equals(mList.get(arg0 - 1).getEntertime())) {
					holder.bet_date.setVisibility(View.INVISIBLE);
				}
			}

			holder.bet_time.setText(date);
			holder.bet_time.setVisibility(View.GONE);
		}

		return convertView;
	}

	public class Holder {

		TextView bet_date;
		TextView bet_time;
		TextView bet_style;
		TextView bet_count;
	}

}
