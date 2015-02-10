package com.xmtq.lottery.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.lottery.R;
import com.xmtq.lottery.bean.GameCanBetBean;
import com.xmtq.lottery.bean.Odds;
import com.xmtq.lottery.utils.OddsUtil;
import com.xmtq.lottery.widget.AnalyzeDialog;
import com.xmtq.lottery.widget.DisagreeDialog;

public class RecomendListAdapter extends BaseAdapter {
	private Context mContext;
	private List<GameCanBetBean> gameCanBetBeans;
	private DisagreeDialog disagreeDialog;
	private AnalyzeDialog analyzeDialog;
	private OnClickListener onMoreListener;

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
			holder.win = (ToggleButton) convertView.findViewById(R.id.win);
			holder.draw = (ToggleButton) convertView.findViewById(R.id.draw);
			holder.lose = (ToggleButton) convertView.findViewById(R.id.lose);
			holder.analyze = (TextView) convertView.findViewById(R.id.analyze);
			holder.dis_agree = (ImageView) convertView
					.findViewById(R.id.dis_agree);
			holder.odds_more = (LinearLayout) convertView
					.findViewById(R.id.odds_more);
			if (onMoreListener != null) {
				holder.odds_more.setOnClickListener(onMoreListener);
			}
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		// 赔率详情
		holder.odds_more.setTag(position);

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
		List<Odds> spOddsList = gameCanBetBeans.get(position).getSpOddsList();
		if (spOddsList.size() > 0) {
			for (int j = 0; j < spOddsList.size(); j++) {
				Odds odds = spOddsList.get(j);
				if (odds.getResult().equals("胜")) {
					setText(holder.win, odds);
				} else if (odds.getResult().equals("平")) {
					setText(holder.draw, odds);
				} else if (odds.getResult().equals("负")) {
					setText(holder.lose, odds);
				}
			}
		}

		// 赛事分析
		if (gameCanBetBeans.get(position).getSpContent() == null) {
			holder.analyze.setVisibility(View.GONE);
		} else {
			holder.analyze.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// 这个Dialog需要传赛事分析文字
					analyzeDialog = new AnalyzeDialog(mContext,
							mAnalyzeListener);
					analyzeDialog.show();
				}
			});
		}
		holder.dis_agree.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				disagreeDialog = new DisagreeDialog(mContext, mDisAgreeListener);
				disagreeDialog.show();
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
			disagreeDialog.dismiss();
		}
	};

	private OnClickListener mAnalyzeListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			analyzeDialog.dismiss();
		}
	};

	/**
	 * 设置开关按钮的状态
	 * 
	 * @param toggleButton
	 * @param text
	 */
	private void setText(ToggleButton toggleButton, Odds odds) {
		String sOdds = odds.getOdds() + " " + odds.getResult();
		toggleButton.setText(sOdds);
		toggleButton.setTextOn(sOdds);
		toggleButton.setTextOff(sOdds);

		toggleButton.setTag(odds);
		toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				Odds odds = (Odds) arg0.getTag();
				if (arg1) {
					odds.setChecked(true);
				} else {
					odds.setChecked(false);
				}
			}
		});

		if (odds.isChecked()) {
			toggleButton.setChecked(true);
		} else {
			toggleButton.setChecked(false);
		}
	}

	/**
	 * 设置更多玩法Listener
	 * 
	 * @param onMoreListener
	 */
	public void setOnMoreListener(OnClickListener onMoreListener) {
		this.onMoreListener = onMoreListener;
	}
}
