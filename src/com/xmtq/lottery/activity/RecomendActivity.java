package com.xmtq.lottery.activity;

import java.util.ArrayList;
import java.util.List;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lottery.R;
import com.xmtq.lottery.adapter.RecomendListAdapter;
import com.xmtq.lottery.fragment.UserInfoFragment;

/**
 * 首页推荐
 * 
 * @author mwz123
 * 
 */
public class RecomendActivity extends BaseActivity {

	private ImageButton imgBtnLeft, imgBtnRight;
	private ListView recomend_list;

	private MenuDrawer mLeftMenuDrawer;
	private MenuDrawer mRightMenuDrawer;
	private long exitTime;
	private final static long TIME_DIFF = 2 * 1000;

	@Override
	public void setContentLayout() {
		setContentView(R.layout.recomend);
	}

	@Override
	public void dealLogicBeforeInitView() {

	}

	@Override
	public void initView() {
		imgBtnLeft = (ImageButton) findViewById(R.id.recomend_left);
		imgBtnRight = (ImageButton) findViewById(R.id.recomend_right);
		imgBtnLeft.setOnClickListener(this);
		imgBtnRight.setOnClickListener(this);
		recomend_list = (ListView) findViewById(R.id.recomend_list);

		initMenuDrawer();
	}

	@Override
	public void dealLogicAfterInitView() {
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(i + "");
		}
		RecomendListAdapter mAdapter = new RecomendListAdapter(
				RecomendActivity.this, mList);
		recomend_list.setAdapter(mAdapter);
	}

	@Override
	public void onClickEvent(View view) {
		switch (view.getId()) {
		case R.id.recomend_left:

			mLeftMenuDrawer.openMenu(true);
			break;

		case R.id.recomend_right:
			mRightMenuDrawer.openMenu(true);
			break;

		default:
			break;
		}
	}

	private void initMenuDrawer() {
		mFragmentManager = getSupportFragmentManager();

		mLeftMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND,
				Position.LEFT, MenuDrawer.MENU_DRAG_WINDOW);
		mLeftMenuDrawer.setMenuView(R.layout.userinfo);
		mLeftMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_BEZEL);
		mLeftMenuDrawer.setMenuSize(getScreenWidth() / 5 * 4);

		attachFragment(mLeftMenuDrawer.getMenuContainer().getId(),
				new UserInfoFragment(), mCurrentFragmentMenuTag);
		commitTransactions();

		mRightMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND,
				Position.RIGHT, MenuDrawer.MENU_DRAG_WINDOW);
		mRightMenuDrawer.setMenuView(R.layout.fragment_history);
		mRightMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_BEZEL);
		mRightMenuDrawer.setMenuSize(getScreenWidth() / 5 * 4);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mLeftMenuDrawer.closeMenu(true);
		mRightMenuDrawer.closeMenu(true);
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			final int drawerState = mLeftMenuDrawer.getDrawerState();
			if (drawerState == MenuDrawer.STATE_OPEN
					|| drawerState == MenuDrawer.STATE_OPENING) {
				mLeftMenuDrawer.closeMenu();
				mRightMenuDrawer.closeMenu();
				return true;
			}
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
