package com.xmtq.lottery.widget;

import java.util.Calendar;

import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.AttributeSet;

/**
 * 自定义DigitalClock输出格式
 * 
 * @author 农民伯伯
 * 
 */
public class DigitalClock extends android.widget.DigitalClock {

    Calendar mCalendar;
    private final static String m12 = "h:mm:ss aa";// h:mm:ss aa
    private final static String m24 = "k:mm:ss";// k:mm:ss
    private FormatChangeObserver mFormatChangeObserver;

    private Runnable mTicker;
    private Handler mHandler;

    private boolean mTickerStopped = false;

    String mFormat;

    public DigitalClock(Context context) {
	super(context);
	initClock(context);
    }

    public DigitalClock(Context context, AttributeSet attrs) {
	super(context, attrs);
	initClock(context);
    }

    private void initClock(Context context) {
	Resources r = context.getResources();

	if (mCalendar == null) {
	    mCalendar = Calendar.getInstance();
	}

	mFormatChangeObserver = new FormatChangeObserver();
	getContext().getContentResolver().registerContentObserver(
		Settings.System.CONTENT_URI, true, mFormatChangeObserver);

	setFormat();
    }

    @Override
    protected void onAttachedToWindow() {
	mTickerStopped = false;
	super.onAttachedToWindow();
	mHandler = new Handler();

	/**
	 * requests a tick on the next hard-second boundary
	 */
	mTicker = new Runnable() {
	    public void run() {
		if (mTickerStopped)
		    return;
		mCalendar.setTimeInMillis(System.currentTimeMillis());
		setText(DateFormat.format(mFormat, mCalendar));
		invalidate();
		long now = SystemClock.uptimeMillis();
		long next = now + (1000 - now % 1000);
		mHandler.postAtTime(mTicker, next);
	    }
	};
	mTicker.run();
    }

    @Override
    protected void onDetachedFromWindow() {
	super.onDetachedFromWindow();
	mTickerStopped = true;
    }

    private void setFormat() {

	mFormat = m24;

    }

    private class FormatChangeObserver extends ContentObserver {
	public FormatChangeObserver() {
	    super(new Handler());
	}

	@Override
	public void onChange(boolean selfChange) {
	    setFormat();
	}
    }
}
