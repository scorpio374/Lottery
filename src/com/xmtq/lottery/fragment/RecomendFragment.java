package com.xmtq.lottery.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottery.R;
import com.universe.lottery.util.SplitLotteryJCZC;
import com.xmtq.lottery.activity.OddsDetailActivity;
import com.xmtq.lottery.activity.RecomendActivity;
import com.xmtq.lottery.adapter.RecomendListAdapter;
import com.xmtq.lottery.bean.BaseResponse;
import com.xmtq.lottery.bean.GameCanBetBean;
import com.xmtq.lottery.bean.GameCanBetResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.utils.DateUtil;
import com.xmtq.lottery.utils.LogUtil;
import com.xmtq.lottery.utils.OddsUtil;
import com.xmtq.lottery.utils.OnRefreshListener;
import com.xmtq.lottery.utils.SharedPrefHelper;
import com.xmtq.lottery.widget.CheckChuanGuanDialog;
import com.xmtq.lottery.widget.ChuanGuanDialog;
import com.xmtq.lottery.widget.CustomPullListView;
import com.xmtq.lottery.widget.CustomPullListView.OnLoadMoreListener;

/**
 * 首页推荐
 * 
 * @author mwz123
 * 
 */
public class RecomendFragment extends BaseFragment {

	/**
	 * 更多竞猜玩法
	 */
	private final static int ODDS_REQUEST_CODE = 1;

	/**
	 * 拼接投注串
	 */
	private final static int TYPE_BETTING = 0;

	/**
	 * 计算注数
	 */
	private final static int TYPE_VOTENUMS = 1;

	private ImageButton imgBtnLeft, imgBtnRight;
	private ImageButton recomend_refresh;
	private CustomPullListView recomend_list;
	private TextView recomend_lottery_times;
	private TextView recomend_date;
	private TextView recomend_week;
	private TextView recomend_commit;
	private LinearLayout betting_info;
	private TextView betting_votenums;
	private TextView betting_multiple;
	private TextView betting_buymoney;

	private Toast toast;
	private int currentPageNum = 1;
	private int pageSize = 10;
	private int count = 0;
	private List<GameCanBetBean> gameCanBetBeans;
	private RecomendListAdapter mAdapter;

	private static final int LOAD_DATA_FINISH = 10;// 上拉刷新
	private static final int REFRESH_DATA_FINISH = 11;// 下拉刷新
	private RadioButton chuan_guan;

	private GridView chuanguan_more;
	private boolean isShowMore = false;
	private ChuanGuanDialog mChuanGuanDialog;
	private RadioButton check_chuan_guan;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		toast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.recomend, container, false);
		initView(view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		request();
		super.onActivityCreated(savedInstanceState);
	}

	/**
	 * 请求可投注的场次
	 */
	private void request() {
		// TODO Auto-generated method stub
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(RequestMaker.getInstance().getGameCanBet(
				"" + currentPageNum, "" + pageSize));
		mAsyncTask.setOnCompleteListener(mOnGameCompleteListener);
	}

	/**
	 * 投注
	 */
	private void requestBetting() {
		String uid = SharedPrefHelper.getInstance(getActivity()).getUid();
		String lotteryid = "136";
		String votetype = "2";
		String votenums = "1";
		String multiple = "1";
		String totalmoney = "2";
		String playtype = "6";
		String passtype = "2*1";
		String buymoney = "2";
		String protype = "1";
		// String voteinfo = "HT@62959|RQ=3&62960|RQ=3@2*1@1";
		String voteinfo = getOddsData(TYPE_BETTING, passtype, multiple);
		LogUtil.log("voteinfo:" + voteinfo);
		// LogUtil.log("votenums:"
		// + SplitLotteryJCZC.exeNum(getOddsData(TYPE_VOTENUMS)));

		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(RequestMaker.getInstance().getBettingBusiness(uid,
				lotteryid, votetype, votenums, multiple, voteinfo, totalmoney,
				playtype, passtype, buymoney, protype));
		mAsyncTask.setOnCompleteListener(mOnBettingCompleteListener);
	}

	public void initView(View v) {
		imgBtnLeft = (ImageButton) v.findViewById(R.id.recomend_left);
		imgBtnRight = (ImageButton) v.findViewById(R.id.recomend_right);
		recomend_refresh = (ImageButton) v.findViewById(R.id.recomend_refresh);
		chuan_guan = (RadioButton) v.findViewById(R.id.chuan_guan);
		chuanguan_more = (GridView) v.findViewById(R.id.chuanguan_more);
		check_chuan_guan = (RadioButton) v.findViewById(R.id.check_chuan_guan);
		check_chuan_guan.setOnClickListener(this);
		chuan_guan.setOnClickListener(this);
		imgBtnLeft.setOnClickListener(this);
		imgBtnRight.setOnClickListener(this);
		recomend_refresh.setOnClickListener(this);
		recomend_list = (CustomPullListView) v.findViewById(R.id.recomend_list);
		// recomend_list.setCanRefresh(true);
		recomend_list.setCanLoadMore(true);

		recomend_lottery_times = (TextView) v
				.findViewById(R.id.recomend_lottery_times);
		recomend_date = (TextView) v.findViewById(R.id.recomend_date);
		recomend_week = (TextView) v.findViewById(R.id.recomend_week);
		recomend_commit = (TextView) v.findViewById(R.id.recomend_commit);
		recomend_commit.setOnClickListener(this);
		betting_info = (LinearLayout) v.findViewById(R.id.betting_info);
		betting_votenums = (TextView) v.findViewById(R.id.betting_votenums);
		betting_multiple = (TextView) v.findViewById(R.id.betting_multiple);
		betting_buymoney = (TextView) v.findViewById(R.id.betting_buymoney);

		dealLogicAfterInitView();
	}

	public void dealLogicAfterInitView() {
		Date date = new Date(System.currentTimeMillis());
		String time = DateUtil.getyyyy_MM_ddTime(date);
		String week = DateUtil.getWeek(date);
		recomend_date.setText(time);
		recomend_week.setText(week);
	}

	@Override
	public void onClickEvent(View v) {
		switch (v.getId()) {
		case R.id.recomend_left:
			((RecomendActivity) getActivity()).openLeftDrawer();
			break;

		case R.id.recomend_right:
			((RecomendActivity) getActivity()).openRightDrawer();
			break;

		case R.id.chuan_guan:
			mChuanGuanDialog = new ChuanGuanDialog(getActivity(),
					mCancelListener, mCommitListener);
			mChuanGuanDialog.show();
			break;

		case R.id.check_chuan_guan:
			mCheckChuanGuanDialog = new CheckChuanGuanDialog(getActivity());
			mCheckChuanGuanDialog
					.setOnCompleteListener(new CheckChuanGuanDialog.OnCompleteListener() {

						@Override
						public void onComplete(String resultString) {
							// TODO Auto-generated method stub
							if (resultString != null) {
								check_chuan_guan.setText(resultString + "倍");
								onRefreshListener.onRefresh();
							}
						}
					});
			mCheckChuanGuanDialog.show();
			break;

		case R.id.recomend_commit:
			toast.setText("正在投注...");
			toast.show();
			requestBetting();
			break;

		default:
			break;
		}
	}

	// 弹窗选择监听
	private OnClickListener mCancelListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mChuanGuanDialog.dismiss();
		}
	};
	private OnClickListener mCommitListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mChuanGuanDialog.dismiss();
		}
	};

	// 输入选择dialog点击监听
	private OnClickListener mSureListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mCheckChuanGuanDialog.dismiss();
		}
	};

	/**
	 * 可竞猜场次回调处理
	 */
	private OnCompleteListener<BaseResponse> mOnGameCompleteListener = new OnCompleteListener<BaseResponse>() {
		@Override
		public void onComplete(BaseResponse result, String resultString) {
			// TODO Auto-generated method stub
			if (result != null) {
				if (result.errorcode.equals("0")) {
					onSuccess(result);
				} else {
					onFailure(result.errormsg);
				}
			} else {
				onFailure("请求错误");
			}
		}
	};

	/**
	 * 投注回调处理
	 */
	private OnCompleteListener<BaseResponse> mOnBettingCompleteListener = new OnCompleteListener<BaseResponse>() {
		@Override
		public void onComplete(BaseResponse result, String resultString) {
			// TODO Auto-generated method stub
			if (result != null) {
				if (result.errorcode.equals("0")) {

				} else {
					onFailure(result.errormsg);
				}
			} else {
				onFailure("请求错误");
			}
		}
	};

	/**
	 * 请求成功
	 */
	private void onSuccess(BaseResponse result) {
		GameCanBetResponse gameCanBetResponse = (GameCanBetResponse) result;

		if (currentPageNum == 1) {
			count = Integer.parseInt(gameCanBetResponse.count);
			recomend_lottery_times.setText("推荐" + count + "场比赛");
			gameCanBetBeans = gameCanBetResponse.gameCanBetBeans;
			mAdapter = new RecomendListAdapter(getActivity(), gameCanBetBeans);
			mAdapter.setOnMoreListener(onMoreListener);
			mAdapter.setOnRefreshListener(onRefreshListener);
			recomend_list.setAdapter(mAdapter);

			// // 下拉刷新
			// recomend_list.setOnRefreshListener(new OnRefreshListener() {
			//
			// @Override
			// public void onRefresh() {
			// // TODO Auto-generated method stub
			// mHandler.sendEmptyMessageDelayed(REFRESH_DATA_FINISH, 2000);
			// }
			// });

			// 上拉加载
			recomend_list.setOnLoadListener(new OnLoadMoreListener() {

				@Override
				public void onLoadMore() {
					// TODO Auto-generated method stub
					if (currentPageNum * pageSize > count) {
						toast.setText("已获取全部数据");
						toast.show();
						mHandler.sendEmptyMessage(LOAD_DATA_FINISH);
						return;
					}
					currentPageNum++;
					request();
				}
			});
		} else {
			gameCanBetBeans.addAll(gameCanBetResponse.gameCanBetBeans);
			mHandler.sendEmptyMessage(LOAD_DATA_FINISH);
		}
	}

	/**
	 * 请求失败
	 * 
	 * @param msg
	 */
	private void onFailure(String msg) {
		toast.setText(msg);
		toast.show();
		mHandler.sendEmptyMessage(LOAD_DATA_FINISH);
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case REFRESH_DATA_FINISH:
				mAdapter.notifyDataSetChanged();
				recomend_list.onRefreshComplete();
				break;
			case LOAD_DATA_FINISH:
				if (mAdapter != null) {
					mAdapter.notifyDataSetChanged();
					recomend_list.onLoadMoreComplete();
				}
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
	private CheckChuanGuanDialog mCheckChuanGuanDialog;

	/**
	 * 处理回传数据
	 * 
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	public void setIntentResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == getActivity().RESULT_OK) {
			GameCanBetBean gameCanBetBean = (GameCanBetBean) data
					.getSerializableExtra("GameCanBetBean");
			int position = data.getIntExtra("position", 0);
			gameCanBetBeans.set(position, gameCanBetBean);
			mAdapter.notifyDataSetChanged();
			onRefreshListener.onRefresh();
		}
	}

	/**
	 * 更多玩法点击Listener 说明：Adapter的context没有startActivityForResult方法
	 */
	private OnClickListener onMoreListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			int position = (Integer) view.getTag();
			Intent intent = new Intent(getActivity(), OddsDetailActivity.class);
			intent.putExtra("GameCanBetBean", gameCanBetBeans.get(position));
			intent.putExtra("position", position);
			getActivity().startActivityForResult(intent, ODDS_REQUEST_CODE);
		}
	};

	/**
	 * 刷新界面投注信息
	 */
	private OnRefreshListener onRefreshListener = new OnRefreshListener() {

		@Override
		public void onRefresh() {
			// TODO Auto-generated method stub
			int votenums = 0;
			int multiple = getMultiple(check_chuan_guan.getText().toString());
			String passType = "1*1";
			String voteinfo = getOddsData(TYPE_VOTENUMS, passType,
					String.valueOf(multiple));
			if (!TextUtils.isEmpty(voteinfo)) {
				votenums = SplitLotteryJCZC.exeNum(voteinfo);
			}
			int buymoney = votenums * multiple * 2;

			betting_votenums.setText(votenums + "注");
			betting_multiple.setText(multiple + "倍");
			betting_buymoney.setText(buymoney + "");
		}
	};

	/**
	 * 解决倍数
	 * 
	 * @param multiple
	 * @return
	 */
	private int getMultiple(String multiple) {
		int num = 1;
		if (multiple.equals("倍数")) {
			num = 1;
		} else {
			multiple = multiple.replace("倍", "");
			try {
				num = Integer.parseInt(multiple);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
		return num;
	}

	/**
	 * 竞猜数据拼接 type 0：投注串(每场比赛之间用"_"连接) 1：计算注数(每场比赛之间用"&"连接)
	 */
	private String getOddsData(int type, String passType, String multiple) {
		if (gameCanBetBeans == null || gameCanBetBeans.size() == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("HT");
		sb.append("@");
		List<String> gameCheckList = new ArrayList<String>();
		for (GameCanBetBean gameCanBetBean : gameCanBetBeans) {
			List<String> gameOddsList = new ArrayList<String>();
			StringBuilder gameSb = new StringBuilder();

			// 胜负平拼接
			String spOddsData = OddsUtil.getSpOddsData(gameCanBetBean
					.getSpOddsList());
			if (!TextUtils.isEmpty(spOddsData)) {
				gameOddsList.add(spOddsData);
			}

			// 让球胜负平拼接
			String rqOddsData = OddsUtil.getRqOddsData(gameCanBetBean
					.getRqOddsList());
			if (!TextUtils.isEmpty(rqOddsData)) {
				gameOddsList.add(rqOddsData);
			}

			// 比分拼接
			String bfOddsData = OddsUtil.getBfOddsData(gameCanBetBean
					.getBfOddsList());
			if (!TextUtils.isEmpty(bfOddsData)) {
				gameOddsList.add(bfOddsData);
			}

			// 进球拼接
			String jqOddsData = OddsUtil.getJqOddsData(gameCanBetBean
					.getJqOddsList());
			if (!TextUtils.isEmpty(jqOddsData)) {
				gameOddsList.add(jqOddsData);
			}

			// 半全场拼接
			String bqOddsData = OddsUtil.getBqOddsData(gameCanBetBean
					.getBqOddsList());
			if (!TextUtils.isEmpty(bqOddsData)) {
				gameOddsList.add(bqOddsData);
			}

			// 本场比赛所有玩法拼接
			if (gameOddsList.size() > 0) {
				gameSb.append(gameCanBetBean.getMatchId() + "|");
				for (int i = 0; i < gameOddsList.size(); i++) {
					if (i == 0) {
						gameSb.append(gameOddsList.get(i));
					} else {
						gameSb.append("," + gameOddsList.get(i));
					}
				}
			}

			// 保存本场比赛所有投注数据
			if (!TextUtils.isEmpty(gameSb)) {
				gameCheckList.add(gameSb.toString());
			}
		}

		// 所有比赛投注拼接
		if (gameCheckList.size() == 0) {
			return null;
		} else {
			for (int i = 0; i < gameCheckList.size(); i++) {
				if (i == 0) {
					sb.append(gameCheckList.get(i));
				} else {
					if (type == 0) {
						sb.append("_" + gameCheckList.get(i));
					} else if (type == 1) {
						sb.append("&" + gameCheckList.get(i));
					}
				}
			}
		}

		// 串关
		sb.append("@");
		sb.append(passType);

		// 倍数
		sb.append("@");
		sb.append(multiple);
		
		LogUtil.log("refreshData:" + sb.toString());
		return sb.toString();
	}

}
