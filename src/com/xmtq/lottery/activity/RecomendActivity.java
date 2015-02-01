package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.FragmentPagerAdater;
import com.xmtq.lottery.fragment.BetRecordFragment;
import com.xmtq.lottery.fragment.LoginFragment;
import com.xmtq.lottery.fragment.RecomendFragment;
import com.xmtq.lottery.utils.SharedPrefHelper;
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
	private SharedPrefHelper spfs;
	private ViewPager vp;
	private FragmentPagerAdater fragmentPagerAdater;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		spfs = SharedPrefHelper.getInstance(this);
		initView();
		// test();
	}

	// private void test() {
	// RequestMaker mRequestMaker = RequestMaker.getInstance("");
	// HttpRequestAsyncTask mAsyncTask = new HttpRequestAsyncTask();
	// mAsyncTask.execute(mRequestMaker.getExtractCash("14244", "tq222222",
	// "100"));
	// }

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

		// boolean isLogin = false;
		// isLogin = spfs.getIsLogin();
		// if (isLogin) {
		// // left sliding menu
		// setBehindContentView(R.layout.menu_frame);
		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.menu_frame, new UserInfoFragment()).commit();
		//
		// } else {
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new LoginFragment()).commit();
		// }

		menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		// middle view
		// setContentView(R.layout.content_frame);
		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.content_frame, new RecomendFragment()).commit();
		//
		// // right sliding menu
		// menu.setSecondaryMenu(R.layout.menu_frame_two);
		// menu.setSecondaryShadowDrawable(R.drawable.shadowright);
		//
		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.menu_frame_two, new BetRecordFragment()).commit();

		vp = new ViewPager(this);
		vp.setId("VP".hashCode());
		RecomendFragment recomendFragment =  new RecomendFragment();
		BetRecordFragment betRecordFragment = new BetRecordFragment();
		List<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(recomendFragment);
		fragments.add(betRecordFragment);
		fragmentPagerAdater = new FragmentPagerAdater(getSupportFragmentManager(),fragments);
		vp.setAdapter(fragmentPagerAdater);
		setContentView(vp);

		vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_FULLSCREEN);
					break;
				default:
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_NONE);
					break;
				}
			}

		});

		vp.setCurrentItem(0);
	}

	public void openLeftDrawer() {
		menu.showMenu();
	}

	public void openRightDrawer() {
		// menu.showSecondaryMenu();
		vp.setCurrentItem(1);
		fragmentPagerAdater.notifyDataSetChanged();
	}
	
	public void closeRightDrawer() {
		// menu.showSecondaryMenu();
		vp.setCurrentItem(0);
		fragmentPagerAdater.notifyDataSetChanged();
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
