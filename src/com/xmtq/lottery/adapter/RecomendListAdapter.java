package com.xmtq.lottery.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lottery.R;
import com.xmtq.lottery.bean.GameCanBetBean;
import com.xmtq.lottery.bean.SpOdds;
import com.xmtq.lottery.utils.OddsUtil;

public class RecomendListAdapter extends BaseAdapter {
	private Context mContext;
	private List<GameCanBetBean> gameCanBetBeans;

	public RecomendListAdapter(Context c, List<GameCanBetBean> gameCanBetBeans) {
		this.mContext = c;
		this.gameCanBetBeans = gameCanBetBeans;
	}

	@Override
	public int getCount() {
		return gameCanBetBeans.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return gameCanBetBeans.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		Holder holder = null;
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.recomend_list_item, null);
			holder.game_time = (TextView) convertView
					.findViewById(R.id.game_time);
			holder.match_team = (TextView) convertView
					.findViewById(R.id.match_team);
			holder.league = (TextView) convertView.findViewById(R.id.league);
			holder.host_team = (TextView) convertView
					.findViewById(R.id.host_team);
			holder.play_type = (TextView) convertView
					.findViewById(R.id.play_type);
			holder.win = (TextView) convertView.findViewById(R.id.win);
			holder.draw = (TextView) convertView.findViewById(R.id.draw);
			holder.lose = (TextView) convertView.findViewById(R.id.lose);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		// holder.game_time.setText(gameCanBetBeans.get(position).getGameTime());
		String gameTimeData = gameCanBetBeans.get(position).getGameTime();
		if (!TextUtils.isEmpty(gameTimeData)) {
			String gameTime = OddsUtil.getGameTime(gameTimeData);
			if (!TextUtils.isEmpty(gameTime)) {
				holder.game_time.setText(gameTime);
			}
		}

		holder.match_team.setText(gameCanBetBeans.get(position).getMatchTeam());
		holder.league.setText(gameCanBetBeans.get(position).getLeague());
		holder.host_team.setText(gameCanBetBeans.get(position).getHostTeam());
		holder.play_type.setText("胜负平");

		String spOddsData = gameCanBetBeans.get(position).getSpOdds();
		if (!TextUtils.isEmpty(spOddsData)) {
			SpOdds spOdds = OddsUtil.getSpOdds(spOddsData);
			holder.win.setText("胜 " + spOdds.getWinOdds());
			holder.draw.setText("平 " + spOdds.getDrawOdds());
			holder.lose.setText("负 " + spOdds.getLoseOdds());
		}
		return convertView;
	}

	public class Holder {
		TextView game_time;
		TextView match_team;
		TextView league;
		TextView host_team;
		TextView play_type;
		TextView win;
		TextView draw;
		TextView lose;
	}
}
