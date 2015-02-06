package com.xmtq.lottery.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lottery.R;
import com.xmtq.lottery.bean.PurchaseRecordsBean;

public class BetRecordListAdapter extends BaseAdapter {
	private Context mContext;
	private List<PurchaseRecordsBean> mRecordsBeansList;

	public BetRecordListAdapter(Context c,
			List<PurchaseRecordsBean> RecordsBeansList) {
		this.mContext = c;
		this.mRecordsBeansList = RecordsBeansList;
	}

	@Override
	public int getCount() {

		return mRecordsBeansList.size();
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
					R.layout.bet_record_list_item, null);
			holder.bet_date = (TextView) convertView
					.findViewById(R.id.bet_date);
			holder.bet_time = (TextView) convertView
					.findViewById(R.id.bet_time);
			holder.bet_style = (TextView) convertView
					.findViewById(R.id.bet_style);
			holder.bet_money = (TextView) convertView
					.findViewById(R.id.bet_money);
			holder.bet_state = (TextView) convertView
					.findViewById(R.id.bet_state);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.bet_date.setText(mRecordsBeansList.get(arg0).getAddtime());
		holder.bet_time.setText(mRecordsBeansList.get(arg0).getAddtime());
		holder.bet_state.setText(mRecordsBeansList.get(arg0).getGuoguan());
		holder.bet_style.setText(mRecordsBeansList.get(arg0).getPlaytype());
		holder.bet_money.setText(mRecordsBeansList.get(arg0).getBonusAfterfax());
		// holder.tv_program_name.setText(childList.get(position).getTitle());

		return convertView;
	}

	public class Holder {

		TextView bet_date;
		TextView bet_time;
		TextView bet_style;
		TextView bet_money;
		TextView bet_state;
	}

}
