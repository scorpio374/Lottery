package com.xmtq.lottery.activity;

import java.util.List;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.OddsGridviewAdapter;
import com.xmtq.lottery.bean.GameCanBetBean;
import com.xmtq.lottery.bean.Odds;
import com.xmtq.lottery.bean.SpOdds;
import com.xmtq.lottery.utils.OddsUtil;
import com.xmtq.lottery.widget.MyGridView;

/**
 * 赔率详情
 * 
 * @author Administrator
 * 
 */
public class OddsDetailActivity extends BaseActivity {

	private TextView match_team;
	private TextView host_team;
	private GameCanBetBean gameCanBetBean;
	private ToggleButton sp_odds_win;
	private ToggleButton sp_odds_draw;
	private ToggleButton sp_odds_lose;
	private ToggleButton rq_odds_win;
	private ToggleButton rq_odds_draw;
	private ToggleButton rq_odds_lose;
	private MyGridView bfGridView;
	private MyGridView jqGridView;
	private MyGridView bqGridview;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.odds_detail);

	}

	@Override
	public void dealLogicBeforeInitView() {
		gameCanBetBean = (GameCanBetBean) getIntent().getSerializableExtra(
				"GameCanBetBean");
	}

	@Override
	public void initView() {
		match_team = (TextView) findViewById(R.id.match_team);
		host_team = (TextView) findViewById(R.id.host_team);
		sp_odds_win = (ToggleButton) findViewById(R.id.sp_odds_win);
		sp_odds_draw = (ToggleButton) findViewById(R.id.sp_odds_draw);
		sp_odds_lose = (ToggleButton) findViewById(R.id.sp_odds_lose);
		rq_odds_win = (ToggleButton) findViewById(R.id.rq_odds_win);
		rq_odds_draw = (ToggleButton) findViewById(R.id.rq_odds_draw);
		rq_odds_lose = (ToggleButton) findViewById(R.id.rq_odds_lose);
		bfGridView = (MyGridView)findViewById(R.id.bf_gridview);
		jqGridView = (MyGridView)findViewById(R.id.jq_gridview);
		bqGridview = (MyGridView)findViewById(R.id.bq_gridview);

		if (gameCanBetBean != null) {
			match_team.setText(gameCanBetBean.getMatchTeam() + "(主)");
			host_team.setText(gameCanBetBean.getHostTeam() + "(客)");

			// 胜负平
			String spOddsData = gameCanBetBean.getSpOdds();
			if (!TextUtils.isEmpty(spOddsData)) {
				SpOdds spOdds = OddsUtil.getSpOdds(spOddsData);
				setText(sp_odds_win, "胜 " + spOdds.getWinOdds());
				setText(sp_odds_draw, "平 " + spOdds.getDrawOdds());
				setText(sp_odds_lose, "负 " + spOdds.getLoseOdds());
			}

			// 让球胜负平
			String rqOddsData = gameCanBetBean.getRqOdds();
			if (!TextUtils.isEmpty(rqOddsData)) {
				SpOdds rqOdds = OddsUtil.getSpOdds(spOddsData);
				setText(rq_odds_win, "胜 " + rqOdds.getWinOdds());
				setText(rq_odds_draw, "平 " + rqOdds.getDrawOdds());
				setText(rq_odds_lose, "负 " + rqOdds.getLoseOdds());
			}

			// 比分
			String bfOddsData = gameCanBetBean.getBfOdds();
			if (!TextUtils.isEmpty(bfOddsData)) {
				List<Odds> oddsList = OddsUtil.getOdds(bfOddsData);
				OddsGridviewAdapter adapter = new OddsGridviewAdapter(this, oddsList);
				bfGridView.setAdapter(adapter);
			}
			
			// 进球
			String jqOddsData = gameCanBetBean.getJqOdds();
			if (!TextUtils.isEmpty(jqOddsData)) {
				List<Odds> oddsList = OddsUtil.getOdds(jqOddsData);
				OddsGridviewAdapter adapter = new OddsGridviewAdapter(this, oddsList);
				jqGridView.setAdapter(adapter);
			}
			
			// 半全场
			String bqOddsData = gameCanBetBean.getJqOdds();
			if (!TextUtils.isEmpty(bqOddsData)) {
				List<Odds> oddsList = OddsUtil.getOdds(bqOddsData);
				OddsGridviewAdapter adapter = new OddsGridviewAdapter(this, oddsList);
				bqGridview.setAdapter(adapter);
			}
		}
	}

	@Override
	public void dealLogicAfterInitView() {

	}

	@Override
	public void onClickEvent(View view) {
		switch (view.getId()) {
		case R.id.back:
			this.finish();
			break;

		default:
			break;
		}
	}

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
