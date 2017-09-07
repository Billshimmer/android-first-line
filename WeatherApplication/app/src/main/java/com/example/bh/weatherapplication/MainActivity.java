package com.example.bh.weatherapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.HttpUtil;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button showBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showBtn = (Button) findViewById(R.id.showCitys);
        showBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showCitys:
                try{
//                    sendRequestWithHttpUtils();
                    sendRequestWithOkhttp();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public void sendRequestWithHttpUtils() throws IOException{
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try{
                            String res = HttpUtil.sendGet("http://guolin.tech/api/china/16/116", "");
                            showResponse(res);
                            Log.d("HttpUtil", res);
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        ).start();
    }

    public void sendRequestWithOkhttp() throws IOException{
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        try{
//                            OkHttpClient client = new OkHttpClient();
//                            Request request = new Request.Builder()
//                                    .url("http://guolin.tech/api/china/16/116")
//                                    .build();
//                            Response response = client.newCall(request).execute();
//                            String Data = response.body().string();
//                            showResponse(Data);
//                            Log.d("HttpClient", Data);
//                        }catch (Exception e){
//                            e.printStackTrace();
//                            Toast.makeText(MainActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                }
//        ).start();
        new DownloadTask().execute();
    }

    private void showResponse(final String data){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView textView = (TextView) findViewById(R.id.txtOne);
                textView.setText(data);
            }
        });

    }

    private class DownloadTask extends AsyncTask<Void, Integer, String>{
        private Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... strings) {
            try{
                OkHttpClient client  = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://guolin.tech/api/china/16/116")
                        .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                return "";
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            showResponse(s);
        }
    }
}
