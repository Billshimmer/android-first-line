package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by bh on 2017/9/3.
 */

public class HttpUtil {

    public final static String BASE_URL = "http://127.0.0.1:8080";
    public final static String DEFAULT_RESPONSE_DATA = "";
    public final static int timeout = 5000;

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static String sendPost(String url, String params)throws IOException {
        StringBuffer res = new StringBuffer();
        URL _url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setConnectTimeout(timeout);
        conn.setReadTimeout(timeout);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        PrintWriter pw = new PrintWriter(conn.getOutputStream());
        pw.write(params);
        pw.flush();
        pw.close();

        if(200 == conn.getResponseCode()){
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = br.readLine()) != null){
                res.append(line);
            }
        }

        conn.disconnect();

        return res.toString();
    }

    public static String sendGet(String url, String params)throws IOException{
        StringBuffer res = new StringBuffer();
        URL _url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setConnectTimeout(timeout);
        conn.setReadTimeout(timeout);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
        PrintWriter pw = new PrintWriter(conn.getOutputStream());
        pw.write(params);
        pw.flush();
        pw.close();

        if(200 == conn.getResponseCode()){
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = br.readLine()) != null){
                res.append(line);
            }
        }

        conn.disconnect();

        return res.toString();
    }
}
