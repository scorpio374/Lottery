package com.xmtq.lottery.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.activity.RecomendActivity;
import com.xmtq.lottery.adapter.RecomendListAdapter;
import com.xmtq.lottery.bean.BaseResponse;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.HttpRequestAsyncTask.OnCompleteListener;
import com.xmtq.lottery.network.RequestMaker;

/**
 * 首页推荐
 * 
 * @author mwz123
 * 
 */
public class RecomendFragment extends BaseFragment {

	private ImageButton imgBtnLeft, imgBtnRight;
	private ListView recomend_list;
	private Toast toast;
	private String pagenum = "1";
	private String pagesize = "10";
	
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
		mAsyncTask.execute(RequestMaker.getInstance().getGameCanBet(pagenum,
				pagesize));
		mAsyncTask.setOnCompleteListener(mOnCompleteListener);
	}

	public void initView(View v) {
		imgBtnLeft = (ImageButton) v.findViewById(R.id.recomend_left);
		imgBtnRight = (ImageButton) v.findViewById(R.id.recomend_right);
		imgBtnLeft.setOnClickListener(this);
		imgBtnRight.setOnClickListener(this);
		recomend_list = (ListView) v.findViewById(R.id.recomend_list);

		dealLogicAfterInitView();
	}

	public void dealLogicAfterInitView() {
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		RecomendListAdapter mAdapter = new RecomendListAdapter(getActivity(),
				mList);
		recomend_list.setAdapter(mAdapter);
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

		default:
			break;
		}
	}
	
	/**
	 * 用户登陆回调处理
	 */
	private OnCompleteListener<BaseResponse> mOnCompleteListener = new OnCompleteListener<BaseResponse>() {
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
		toast.setText("请求成功");
		toast.show();
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

}
