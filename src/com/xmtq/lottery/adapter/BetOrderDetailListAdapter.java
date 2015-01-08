package com.xmtq.lottery.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.lottery.R;

public class BetOrderDetailListAdapter extends BaseAdapter {
	private Context mContext;
	private List<String> mList;

	public BetOrderDetailListAdapter(Context c, List<String> mList) {
		this.mContext = c;
		this.mList = mList;
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
					R.layout.bet_detail_list_item, null);

		} else {
			holder = (Holder) convertView.getTag();
		}

		// holder.tv_program_name.setText(childList.get(position).getTitle());

		return convertView;
	}

	public class Holder {

	}

}
