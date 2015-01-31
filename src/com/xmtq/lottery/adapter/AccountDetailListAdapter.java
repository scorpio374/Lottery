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
		if (mList.size() > 0) {
			holder.bet_count.setText(mList.get(arg0).getEntertime());
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
