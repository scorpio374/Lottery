package com.xmtq.lottery.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ToggleButton;

import com.example.lottery.R;
import com.xmtq.lottery.bean.Odds;

public class OddsGridviewAdapter extends BaseAdapter {

	private Context context;
	private List<Odds> oddsList;

	public OddsGridviewAdapter(Context context, List<Odds> oddsList) {
		this.context = context;
		this.oddsList = oddsList;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return oddsList.size();
	}

	@Override
	public Odds getItem(int arg0) {
		// TODO Auto-generated method stub
		return oddsList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(context).inflate(
				R.layout.odds_gridview_item, null);
		ToggleButton toggleButton = (ToggleButton)convertView.findViewById(R.id.odds_button);
		String odds = oddsList.get(position).getResult()+"\n"+oddsList.get(position).getOdds();
		setText(toggleButton, odds);
		return convertView;
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
