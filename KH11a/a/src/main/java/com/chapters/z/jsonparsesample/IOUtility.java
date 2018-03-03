package com.chapters.z.jsonparsesample;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/23.
 */

public final class IOUtility {



    public static InputStream loadFromAssets(String name,Context cxt) throws IOException {
        InputStream is = null;

        AssetManager assetManager = cxt.getResources().getAssets(); //获取管理assets目录资源的AssetManager引用
        is = assetManager.open(name); //获取资源文件的输入流
        return is;
    }


    //工具函数，将inputstream 转换成String
    public static String   inputStream2String(InputStream   is)   throws   IOException{
        ByteArrayOutputStream baos   =   new   ByteArrayOutputStream();
        int   i=-1;
        while((i=is.read())!=-1){
            baos.write(i);
        }
        return baos.toString();
    }

    // Given a string representation of a URL, sets up a connection and gets
    // an input stream.
    public static InputStream loadFromUrl(String url) throws IOException {
        URL ul = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) ul.openConnection();
        conn.setReadTimeout(100000 /* milliseconds */);
        conn.setConnectTimeout(150000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        return conn.getInputStream();
    }

    public static String getStrFromInsByCode(InputStream is, String code){
        StringBuilder builder=new StringBuilder();
        BufferedReader reader=null;


        try {
            reader = new BufferedReader(new InputStreamReader(is,code));
            String line;
            while((line=reader.readLine())!=null){
                builder.append(line+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
