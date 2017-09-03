package com.example.bh.weatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button showBtn;

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
                    sendRequestWithOkhttp();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    public void sendRequestWithOkhttp() throws IOException{
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url("http://guolin.tech/api/china/16/116")
                                    .build();
                            Response response = client.newCall(request).execute();
                            String Data = response.body().string();
                            showResponse(Data);
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        ).start();
    }

    private void showResponse(final String data){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView textView = (TextView) findViewById(R.id.txtOne);
                textView.setText(data);
            }
        });

    }
}
