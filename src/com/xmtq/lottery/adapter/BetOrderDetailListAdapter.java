package com.xmtq.lottery.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lottery.R;
import com.xmtq.lottery.bean.BetDetailBean;

public class BetOrderDetailListAdapter extends BaseAdapter {
	private Context mContext;
	private List<BetDetailBean> mBetDetailBeans;

	public BetOrderDetailListAdapter(Context c,
			List<BetDetailBean> betDetailBeans) {
		this.mContext = c;
		this.mBetDetailBeans = betDetailBeans;
	}

	@Override
	public int getCount() {

		return mBetDetailBeans.size();
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
			holder.bet_detail_week = (TextView) convertView
					.findViewById(R.id.bet_detail_week);
			holder.bet_detail_team = (TextView) convertView
					.findViewById(R.id.bet_detail_team);
			holder.bet_detail_spf = (TextView) convertView
					.findViewById(R.id.bet_detail_spf);
			holder.bet_detail_f = (TextView) convertView
					.findViewById(R.id.bet_detail_f);
			holder.bet_detail_s = (TextView) convertView
					.findViewById(R.id.bet_detail_s);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.bet_detail_week.setText(mBetDetailBeans.get(arg0).getName()
				+ "\n" + mBetDetailBeans.get(arg0).getNumber());
		holder.bet_detail_team.setText(mBetDetailBeans.get(arg0).getMatchteam()
				+ "\nvs\n" + mBetDetailBeans.get(arg0).getHostteam());
		holder.bet_detail_spf.setText(mBetDetailBeans.get(arg0)
				.getmBetOddBeans().get(0).getPlayname());
		holder.bet_detail_s.setText(mBetDetailBeans.get(arg0).getmBetOddBeans()
				.get(0).getGameresult());
		holder.bet_detail_f.setText(mBetDetailBeans.get(arg0).getmBetOddBeans()
				.get(0).getBetinfo());
		// holder.tv_program_name.setText(childList.get(position).getTitle());

		return convertView;
	}

	public class Holder {
		TextView bet_detail_week;
		TextView bet_detail_team;
		TextView bet_detail_spf;
		TextView bet_detail_f;
		TextView bet_detail_s;

	}

}
