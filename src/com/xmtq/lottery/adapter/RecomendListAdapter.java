package com.xmtq.lottery.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.lottery.R;
import com.xmtq.lottery.activity.OddsDetailActivity;
import com.xmtq.lottery.bean.GameCanBetBean;
import com.xmtq.lottery.bean.SpOdds;
import com.xmtq.lottery.utils.OddsUtil;
import com.xmtq.lottery.widget.DisagreeDialog;

public class RecomendListAdapter extends BaseAdapter {
	private Context mContext;
	private List<GameCanBetBean> gameCanBetBeans;
	private DisagreeDialog dialog;

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
		// if (convertView == null) {
		holder = new Holder();
		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.recomend_list_item, null);
		holder.game_time = (TextView) convertView.findViewById(R.id.game_time);
		holder.match_team = (TextView) convertView
				.findViewById(R.id.match_team);
		holder.league = (TextView) convertView.findViewById(R.id.league);
		holder.host_team = (TextView) convertView.findViewById(R.id.host_team);
		holder.play_type = (TextView) convertView.findViewById(R.id.play_type);
		holder.win = (ToggleButton) convertView.findViewById(R.id.win);
		holder.draw = (ToggleButton) convertView.findViewById(R.id.draw);
		holder.lose = (ToggleButton) convertView.findViewById(R.id.lose);
		holder.analyze = (TextView) convertView.findViewById(R.id.analyze);
		holder.dis_agree = (ImageView) convertView.findViewById(R.id.dis_agree);
		holder.odds_more = (LinearLayout) convertView
				.findViewById(R.id.odds_more);
		holder.odds_more.setTag(position);
		convertView.setTag(holder);
		// } else {
		// holder = (Holder) convertView.getTag();
		// }

		// 赔率详情
		holder.odds_more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				int position = (Integer) view.getTag();
				Intent intent = new Intent(mContext, OddsDetailActivity.class);
				intent.putExtra("GameCanBetBean", gameCanBetBeans.get(position));
				mContext.startActivity(intent);
			}
		});

		// 比赛时间
		String gameTimeData = gameCanBetBeans.get(position).getGameTime();
		if (!TextUtils.isEmpty(gameTimeData)) {
			String gameTime = OddsUtil.getGameTime(gameTimeData);
			if (!TextUtils.isEmpty(gameTime)) {
				holder.game_time.setText(gameTime);
			}
		}

		// 比赛队伍
		holder.match_team.setText(gameCanBetBeans.get(position).getMatchTeam());
		holder.league.setText(gameCanBetBeans.get(position).getLeague());
		holder.host_team.setText(gameCanBetBeans.get(position).getHostTeam());

		// 胜负平赔率
		holder.play_type.setText("胜负平");
		String spOddsData = gameCanBetBeans.get(position).getSpOdds();
		if (!TextUtils.isEmpty(spOddsData)) {
			SpOdds spOdds = OddsUtil.getSpOdds(spOddsData);
			setText(holder.win,"胜 " + spOdds.getWinOdds());
			setText(holder.draw,"平 " + spOdds.getDrawOdds());
			setText(holder.lose,"负 " + spOdds.getLoseOdds());
		}

		// 赛事分析
		if (gameCanBetBeans.get(position).getSpContent() == null) {
			holder.analyze.setVisibility(View.GONE);
		}
		holder.dis_agree.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog = new DisagreeDialog(mContext, mDisAgreeListener);
				dialog.show();
			}
		});

		return convertView;
	}

	public class Holder {
		LinearLayout odds_more;
		TextView game_time;
		TextView match_team;
		TextView league;
		TextView host_team;
		TextView play_type;
		ToggleButton win;
		ToggleButton draw;
		ToggleButton lose;
		TextView analyze;
		ImageView dis_agree;
	}
	
	private OnClickListener mDisAgreeListener = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			dialog.dismiss();
		}
	};
	
	/**
	 * 设置开关按钮的文字
	 * 
	 * @param toggleButton
	 * @param text
	 */
	private void setText(ToggleButton toggleButton, String text) {
		toggleButton.setText(text);
		toggleButton.setTextOn(text);
		toggleButton.setTextOff(text);
	}
}
