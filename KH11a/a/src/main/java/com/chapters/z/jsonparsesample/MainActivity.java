package com.chapters.z.jsonparsesample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.chapters.z.jsonparsesample.IOUtility.getStrFromInsByCode;
import static com.chapters.z.jsonparsesample.IOUtility.inputStream2String;


public class MainActivity extends AppCompatActivity {
    static final String URL="http://booktest.16mb.com/players.json";
    final static String JSONASSET="players.json";
    static String[] BKS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadXmlTask dt=new DownloadXmlTask();
        dt.execute(URL);
//        String[] city = {"广州","深圳","北京","上海","香港","澳门","天津"};
//        ListView tx2=(ListView) findViewById(R.id.tv2);
//        ArrayAdapter<String> arrayAdapter;
//        arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, city);
//        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,BKS);
//        tx2.setAdapter(arrayAdapter);
    }

    private class DownloadXmlTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressBar);
            progressBar.setProgress((values[0].intValue()+1));
        }

        @Override
        protected String doInBackground(String... urls) {
            String str="";
            try {
                //InputStream is = loadFromUrl(urls[0]);  //xml文档来自web
//                InputStream is =IOUtility.loadFromAssets(JSONASSET,getApplication()); //xml文档来自assets
                InputStream is =IOUtility.loadFromUrl(URL);
                str=getStrFromInsByCode(is,"GBK");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        @Override
        protected void onPostExecute(String result) {
            ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);

            //开始解析，将结果保存为一个对象数组
            JsonParseHelper xts=new JsonParseHelper(getApplicationContext());
            List<Book> ls=(List<Book>) xts.jsonToBooks(result);//0 asset as xmlsource  ;2  String as source

/*
//        以下用于检测实体转为json字符函数
            try {
                result=xts.booksToJson(ls);
            } catch (JSONException e) {
                e.printStackTrace();
            }
           ls=(List<Book>) xts.jsonToBooks(result);//0 asset as xmlsource  ;2  String as source
*/

            //提取对象数组，检验解析是否正确
            String[] bks = new String[ls.size()];

            for(int i=0;i<=ls.size()-1;i++){
                Book bk=(Book)ls.get(i);
                bks[i]=" username= "+bk.get_username()+"\n userpass= "+bk.get_userpass()+"\n sex= "+bk.get_sex();
            }
            BKS=bks;

            TextView tx=(TextView) findViewById(R.id.tv);

            tx.setText("Name= "+JsonParseHelper.Name+" "+"Age= "+JsonParseHelper.Age);

            ListView tx2=(ListView) findViewById(R.id.tv2);
            ArrayAdapter<String> arrayAdapter;
            arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,bks);
            tx2.setAdapter(arrayAdapter);

        }

    }
}

//package com.chapters.z.jsonparsesample;
//
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import org.json.JSONException;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//import static com.chapters.z.jsonparsesample.IOUtility.inputStream2String;
//
//
//public class MainActivity extends AppCompatActivity {
//    static final    String URL="http://192.168.100.103:8080/booksheet.json";
//    final static String JSONASSET="booksheet.json";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        DownloadXmlTask dt=new DownloadXmlTask();
//        dt.execute(URL);
//    }
//
//    private class DownloadXmlTask extends AsyncTask<String, Integer, String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressBar);
//            progressBar.setVisibility(View.VISIBLE);
//
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressBar);
//            progressBar.setProgress((values[0].intValue()+1));
//        }
//
//        @Override
//        protected String doInBackground(String... urls) {
//            String str="";
//            try {
//                //InputStream is = loadFromUrl(urls[0]);  //xml文档来自web
////                InputStream is =IOUtility.loadFromAssets(JSONASSET,getApplication()); //xml文档来自assets
//                InputStream is =IOUtility.loadFromUrl(URL); //xml文档来自assets
//                str=inputStream2String(is);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return str;
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressBar);
//            progressBar.setVisibility(View.GONE);
//
//            //开始解析，将结果保存为一个对象数组
//            JsonParseHelper xts=new JsonParseHelper(getApplicationContext());
//            List<Book> ls=(List<Book>) xts.jsonToBooks(result);//0 asset as xmlsource  ;2  String as source
//
///*
////        以下用于检测实体转为json字符函数
//            try {
//                result=xts.booksToJson(ls);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//           ls=(List<Book>) xts.jsonToBooks(result);//0 asset as xmlsource  ;2  String as source
//*/
//
//            //提取对象数组，检验解析是否正确
//            StringBuilder bks=new StringBuilder();
//
//            for(int i=0;i<=ls.size()-1;i++){
//                Book bk=(Book)ls.get(i);
//                bks.append("book"+(i+1)+":title= ");
//                bks.append(bk.get_title()+";作者= ");
//                List<String> auths=bk.get_authors();
//                for (int j=0;j<=auths.size()-1;j++) {
//                    bks.append(auths.get(j)+" ");
//                }
//                bks.append("\n");
//            }
//            TextView tx=(TextView) findViewById(R.id.tv);
//            tx.setText(bks.toString());
//
//
//
//        }
//
//
//    }
//}