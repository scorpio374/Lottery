package com.xmtq.lottery.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.fragment.BetRecordFragment;
import com.xmtq.lottery.fragment.LoginFragment;
import com.xmtq.lottery.fragment.RecomendFragment;
import com.xmtq.lottery.network.HttpRequestAsyncTask;
import com.xmtq.lottery.network.RequestMaker;
import com.xmtq.lottery.view.slidingmenu.SlidingMenu;
import com.xmtq.lottery.view.slidingmenu.app.SlidingFragmentActivity;

/**
 * 首页推荐
 * 
 * @author mwz123
 * 
 */
public class RecomendActivity extends SlidingFragmentActivity implements
		OnClickListener {

	private long exitTime;
	private final static long TIME_DIFF = 2 * 1000;
	private SlidingMenu menu;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		test();
	}
	
	private void test(){
		RequestMaker mRequestMaker = RequestMaker.getInstance("");
		HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
		mAsyncTask.execute(mRequestMaker.getCheckUser(""));
	}

	public void initView() {
		initMenuDrawer();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {

		default:
			break;
		}
	}

	private void initMenuDrawer() {
		// left sliding menu
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new LoginFragment()).commit();

		menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT_RIGHT);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		// middle view
		setContentView(R.layout.content_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, new RecomendFragment()).commit();

		// right sliding menu
		menu.setSecondaryMenu(R.layout.menu_frame_two);
		menu.setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame_two, new BetRecordFragment()).commit();
	}

	public void openLeftDrawer() {
		menu.showMenu();
	}

	public void openRightDrawer() {
		menu.showSecondaryMenu();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - exitTime) > TIME_DIFF) {
				Toast.makeText(RecomendActivity.this, "再按一次退出",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				System.exit(0);
			}
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}
