package com.example.shimmerbjh.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnShow;
    private Button btnLayout_1;
    private Button btnLayout_2;
    private Button btnLayout_3;
    private Button btnLayout_4;
    private Button btnLayout_5;
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow = (Button) findViewById(R.id.button);btnShow.setOnClickListener(this);
        btnLayout_1 = (Button) findViewById(R.id.button_layout_1); btnLayout_1.setOnClickListener(this);
        btnLayout_2 = (Button) findViewById(R.id.button_layout_2); btnLayout_2.setOnClickListener(this);
        btnLayout_3 = (Button) findViewById(R.id.button_layout_3); btnLayout_3.setOnClickListener(this);
        btnLayout_4 = (Button) findViewById(R.id.button_layout_4); btnLayout_4.setOnClickListener(this);
        btnLayout_5 = (Button) findViewById(R.id.button_layout_5); btnLayout_5.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView) findViewById(R.id.img);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    class  NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "networks changes", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        Toast.makeText(this, "应用已经展现", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "clicked 添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "clicked 删除", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                String text = editText.getText().toString();
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                imageView.setImageResource(R.drawable.img_2);
                progressBar.setProgress(progressBar.getProgress() + 10);
                final AlertDialog.Builder dialog = new AlertDialog.Builder (MainActivity.this);
                dialog.setTitle("这是一个对话框").setMessage("something need confirm").setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.setNegativeButton("Cancal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.button_layout_1:
                Intent intent1 = new Intent(MainActivity.this, LinearLayoutActivity.class);
                startActivity(intent1);
                break;
            case R.id.button_layout_2:
                Intent intent2 = new Intent(MainActivity.this, RelativeActivity.class);
                startActivity(intent2);
                break;
            case R.id.button_layout_3:
                Intent intent3 = new Intent(MainActivity.this, AbsoluteActivity.class);
                startActivity(intent3);
                break;
            case R.id.button_layout_4:
                Intent intent4 = new Intent(MainActivity.this, FrameActivity.class);
                startActivity(intent4);
                break;
            case R.id.button_layout_5:
                Intent intent5 = new Intent(MainActivity.this, TableActivity.class);
                startActivity(intent5);
                break;
            default:
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        Toast.makeText(this, "应用删除", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }
}
