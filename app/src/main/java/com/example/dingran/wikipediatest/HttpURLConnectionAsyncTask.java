package com.example.dingran.wikipediatest;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dingran on 2016/6/1.
 */
public class HttpURLConnectionAsyncTask extends AsyncTask<String, Void, String> {

    private Context context;
    private TextView textView;


    public HttpURLConnectionAsyncTask(Context context, TextView textView) {
        super();
        this.context = context;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;

        try {
            // 初始化URL
            URL url = new URL(params[0]);
            // 创建HttpURLConnection,并打开连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // 判断获取的应答码是否正常
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //TODO 不能在这个后台线程弹Toast！ 给出连接成功的提示
//                Toast.makeText(context, "连接维基百科成功", Toast.LENGTH_LONG).show();
                // 创建InputStreamReader,设置字符编码为UTF-8
                InputStreamReader inputStreamReader =
                        new InputStreamReader(httpURLConnection.getInputStream(), "utf-8");

                int i;
                String content = "";
                // 读消息到content中
                while ((i = inputStreamReader.read()) != -1) {
                    content = content + (char) i;
                }
                inputStreamReader.close();
                Log.d("HttpURLConnection", content);
                result = content;

//                char[] buffer = new char[500];
//                inputStreamReader.read(buffer);
//                inputStreamReader.close();
//                return new String(buffer);
            }
            // 断开连接
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
//        TODO 不能在这个后台线程弹Toast！    Toast.makeText(context, "连接维基百科失败", Toast.LENGTH_LONG).show();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        this.textView.setText(s);
    }
}
