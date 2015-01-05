package com.xmtq.lottery.activity;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lottery.R;

public class MainActivity extends BaseActivity {

	private MenuDrawer mLeftMenuDrawer;
	private MenuDrawer mRightMenuDrawer;
	private ImageButton leftImageButton;
	private ImageButton rightImageButton;
	private long exitTime;
	private final static long TIME_DIFF = 2 * 1000;

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mLeftMenuDrawer.closeMenu(true);
		mRightMenuDrawer.closeMenu(true);
		super.onDestroy();
	}

	@Override
	public void setContentLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.main);
	}

	@Override
	public void dealLogicBeforeInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		leftImageButton = (ImageButton) findViewById(R.id.main_left);
		rightImageButton = (ImageButton) findViewById(R.id.main_right);
		leftImageButton.setOnClickListener(this);
		rightImageButton.setOnClickListener(this);

		initMenuDrawer();
	}

	private void initMenuDrawer() {
		mLeftMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND,
				Position.LEFT, MenuDrawer.MENU_DRAG_WINDOW);
		mLeftMenuDrawer.setMenuView(R.layout.fragment_user);
		mLeftMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_BEZEL);
		mLeftMenuDrawer.setMenuSize(getScreenWidth() / 5 * 4);

		mRightMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND,
				Position.RIGHT, MenuDrawer.MENU_DRAG_WINDOW);
		mRightMenuDrawer.setMenuView(R.layout.fragment_history);
		mRightMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_BEZEL);
		mRightMenuDrawer.setMenuSize(getScreenWidth() / 5 * 4);
	}

	@Override
	public void dealLogicAfterInitView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClickEvent(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.main_left:
			// attachFragment(mLeftMenuDrawer.getMenuContainer().getId(),
			// new UserFragment(), mCurrentFragmentMenuTag);
			// commitTransactions();
			mLeftMenuDrawer.openMenu(true);
			break;

		case R.id.main_right:
			mRightMenuDrawer.openMenu(true);
			break;

		default:
			break;
		}
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
				Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT)
						.show();
				exitTime = System.currentTimeMillis();
			} else {
				System.exit(0);
			}
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}
