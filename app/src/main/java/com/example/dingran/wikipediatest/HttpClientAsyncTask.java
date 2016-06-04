package com.example.dingran.wikipediatest;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;


/**
 * Created by dingran on 2016/6/1.
 */
public class HttpClientAsyncTask extends AsyncTask<String, Void, String> {

    private Context context;
    private TextView textView;

    public HttpClientAsyncTask(Context context, TextView textView) {
        super();
        this.context = context;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;

        // 初始化
        DefaultHttpClient httpClient = new DefaultHttpClient();
//        HttpClient httpClient = new HttpClient();
//        AndroidHttpClient androidHttpClient = new AndroidHttpClient();

        // 创建HttpGet
        HttpGet httpGet = new HttpGet(params[0]);

        // 创建
        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        try {
            // 获取返回的内容
            String content = httpClient.execute(httpGet, responseHandler);

            //TODO 不能在这个后台线程弹Toast！ 给出连接成功的提示
//            Toast.makeText(context, "连接维基百科成功", Toast.LENGTH_LONG).show();

            return content;

        } catch (Exception e) {
            e.printStackTrace();
//TODO 不能在这个后台线程弹Toast！            Toast.makeText(context, "连接维基百科失败", Toast.LENGTH_LONG).show();
            // 关闭连接
            httpClient.getConnectionManager().shutdown();
        }


        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        this.textView.setText(s);
    }
}
