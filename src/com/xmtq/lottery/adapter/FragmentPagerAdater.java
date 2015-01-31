package com.xmtq.lottery.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xmtq.lottery.fragment.BetRecordFragment;
import com.xmtq.lottery.fragment.RecomendFragment;

public class FragmentPagerAdater extends FragmentPagerAdapter {
	
	private RecomendFragment recomendFragment;
	private BetRecordFragment betRecordFragment;
	
	public FragmentPagerAdater(FragmentManager fm) {
		super(fm);
		recomendFragment =  new RecomendFragment();
		betRecordFragment = new BetRecordFragment();
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public Fragment getItem(int position) {
		if(position == 0){
			return recomendFragment;
		}else{
			return betRecordFragment;
		}
	}

}
