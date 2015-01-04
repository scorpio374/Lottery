package com.xmtq.lottery.utils;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

public class Util {

	public static int getVersionCode(Context context) throws Exception {
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packInfo = packageManager.getPackageInfo(
				context.getPackageName(), 0);
		int versionCode = packInfo.versionCode;
		return versionCode;
	}

	public static void installLoadedApkFile(Context context, File file) {
		Intent installIntent = new Intent();
		installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		installIntent.setAction(android.content.Intent.ACTION_VIEW);
		installIntent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(installIntent);
	}
}
