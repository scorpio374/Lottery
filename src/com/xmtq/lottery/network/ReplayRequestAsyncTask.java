package com.xmtq.lottery.network;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

import com.xmtq.lottery.utils.LogUtil;

public class ReplayRequestAsyncTask extends
		AsyncTask<String, Void, String> {

	private String resultString;
	private OnCompleteListener onCompleteListener;

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {

		try {
			/**
			 * 获取BaseUrl
			 */
			String urlString = params[0];
			LogUtil.log(urlString);
			if (urlString == null) {
				return null;
			}

			HttpResponse httpResponse;
			HttpGet httpGet = null;

			/**
			 * POST请求方式
			 */

			httpGet = new HttpGet(urlString);
//			httpPost.setEntity(new StringEntity(request.getBody()));
			HttpManager.shortTimeOut();
			httpResponse = HttpManager.execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				LogUtil.log("返回getStatusCode="
						+ httpResponse.getStatusLine().getStatusCode());
				if (null != httpGet) {
					httpGet.abort();
				}

			} else {
				resultString = EntityUtils.toString(httpResponse.getEntity(),
						"UTF-8");
			}
		} catch (ClientProtocolException e) {
			resultString = "";
			e.printStackTrace();
		} catch (ParseException e) {
			resultString = "";
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultString;

	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (null != onCompleteListener) {
			// if (null == result) {
			// onCompleteListener.onComplete(null, null);
			// } else {
			onCompleteListener.onComplete(resultString);
			// }
		}
	}

	public interface OnCompleteListener{
		public void onComplete(String resultString);
	}

	public OnCompleteListener getOnCompleteListener() {
		return onCompleteListener;
	}

	public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
		this.onCompleteListener = onCompleteListener;
	}
}
