package com.xmtq.lottery.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lottery.R;
import com.xmtq.lottery.bean.GameHistoryDateBean;
import com.xmtq.lottery.bean.RecomendHistoryBean;
import com.xmtq.lottery.utils.DateUtil;

public class GameResuleDetailListAdapter extends BaseAdapter {
	private Context mContext;
	private List<RecomendHistoryBean> mHistoryBeansList;

	public GameResuleDetailListAdapter(Context c,
			List<RecomendHistoryBean> mHistoryBeansList) {
		this.mContext = c;
		this.mHistoryBeansList = mHistoryBeansList;
	}

	@Override
	public int getCount() {

		return mHistoryBeansList.size();
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
					R.layout.game_result_list_item, null);
			holder.tv_gameTime = (TextView) convertView
					.findViewById(R.id.tv_gameTime);
			holder.tv_league = (TextView) convertView
					.findViewById(R.id.tv_league);
			holder.tv_matchId = (TextView) convertView
					.findViewById(R.id.tv_matchId);
			holder.tv_matchteam = (TextView) convertView
					.findViewById(R.id.tv_matchteam);
			holder.tv_hostTeam = (TextView) convertView
					.findViewById(R.id.tv_hostTeam);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		long time = DateUtil.getMillisecondsFromString(mHistoryBeansList.get(
				arg0).getGameTime());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		String gameTime = sdf.format(new Date(time));

		holder.tv_gameTime.setText(gameTime);
		holder.tv_league.setText(mHistoryBeansList.get(arg0).getLeague());
		holder.tv_matchId.setText(mHistoryBeansList.get(arg0).getMatchId());
		holder.tv_matchteam.setText(mHistoryBeansList.get(arg0).getMatchTeam());
		holder.tv_hostTeam.setText(mHistoryBeansList.get(arg0).getHostTeam());

		// holder.tv_program_name.setText(childList.get(position).getTitle());

		return convertView;
	}

	public class Holder {
		TextView tv_league;
		TextView tv_matchId;
		TextView tv_gameTime;
		TextView tv_matchteam;
		TextView tv_hostTeam;

	}

}
