package com.xmtq.lottery.fragment;

import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.xmtq.lottery.activity.RecomendActivity;
import com.xmtq.lottery.adapter.RecomendListAdapter;
import com.xmtq.lottery.bean.BaseResponse;
import com.xmtq.lottery.bean.GameCanBetBean;
import com.xmtq.lottery.bean.GameCanBetResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.utils.DateUtil;
import com.xmtq.lottery.widget.CheckChuanGuanDialog;
import com.xmtq.lottery.widget.ChuanGuanDialog;
import com.xmtq.lottery.widget.CustomPullListView;
import com.xmtq.lottery.widget.CustomPullListView.OnLoadMoreListener;
import com.xmtq.lottery.widget.CustomPullListView.OnRefreshListener;

/**
 * 首页推荐
 * 
 * @author mwz123
 * 
 */
public class RecomendFragment extends BaseFragment {

	private ImageButton imgBtnLeft, imgBtnRight;
	private CustomPullListView recomend_list;
	private TextView recomend_lottery_times;
	private TextView recomend_date;
	private TextView recomend_week;

	private Toast toast;
	private int pagenum = 1;
	private int pagesize = 10;
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

	private void request() {
		// TODO Auto-generated method stub
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(RequestMaker.getInstance().getGameCanBet(
				"" + pagenum, "" + pagesize));
		mAsyncTask.setOnCompleteListener(mOnGameCompleteListener);
	}

	public void initView(View v) {
		imgBtnLeft = (ImageButton) v.findViewById(R.id.recomend_left);
		imgBtnRight = (ImageButton) v.findViewById(R.id.recomend_right);
		chuan_guan = (RadioButton) v.findViewById(R.id.chuan_guan);
		check_chuan_guan = (RadioButton) v.findViewById(R.id.check_chuan_guan);
		check_chuan_guan.setOnClickListener(this);
		chuan_guan.setOnClickListener(this);
		imgBtnLeft.setOnClickListener(this);
		imgBtnRight.setOnClickListener(this);
		recomend_list = (CustomPullListView) v.findViewById(R.id.recomend_list);
		recomend_list.setCanRefresh(true);
		recomend_list.setCanLoadMore(true);

		recomend_lottery_times = (TextView) v
				.findViewById(R.id.recomend_lottery_times);
		recomend_date = (TextView) v.findViewById(R.id.recomend_date);
		recomend_week = (TextView) v.findViewById(R.id.recomend_week);

		dealLogicAfterInitView();
	}

	public void dealLogicAfterInitView() {
		Date date = new Date(System.currentTimeMillis());
		String time = DateUtil.getyyyy_MM_ddTime(date);
		String week = DateUtil.getWeek(date);
		recomend_date.setText(time);
		recomend_week.setText(week);

		// List<String> mList = new ArrayList<String>();
		// for (int i = 0; i < 10; i++) {
		// mList.add(i + "");
		// }
		// RecomendListAdapter mAdapter = new RecomendListAdapter(getActivity(),
		// mList);
		// recomend_list.setAdapter(mAdapter);
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
			mCheckChuanGuanDialog = new CheckChuanGuanDialog(getActivity(),
					mSureListener);
			mCheckChuanGuanDialog.show();
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
	 * 用户登陆回调处理
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
	 * 请求成功
	 */
	private void onSuccess(BaseResponse result) {
		GameCanBetResponse gameCanBetResponse = (GameCanBetResponse) result;

		recomend_lottery_times.setText("推荐" + gameCanBetResponse.count + "场比赛");
		gameCanBetBeans = gameCanBetResponse.gameCanBetBeans;
		mAdapter = new RecomendListAdapter(getActivity(), gameCanBetBeans);
		recomend_list.setAdapter(mAdapter);

		// 下拉刷新
		recomend_list.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessageDelayed(REFRESH_DATA_FINISH, 1000);
			}
		});

		// 上拉加载
		recomend_list.setOnLoadListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessageDelayed(LOAD_DATA_FINISH, 1000);
			}
		});
		mAdapter.notifyDataSetChanged();
	}

	/**
	 * 请求失败
	 * 
	 * @param msg
	 */
	private void onFailure(String msg) {
		toast.setText(msg);
		toast.show();
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
				mAdapter.notifyDataSetChanged();
				recomend_list.onLoadMoreComplete();
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
	private CheckChuanGuanDialog mCheckChuanGuanDialog;

}
