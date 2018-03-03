package com.chapters.z.jsonparsesample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.StaticLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import java.util.List;
import java.util.jar.Attributes;


/**
 * Created by Administrator on 2016/11/27.
 */

public class JsonParseHelper {
    private Context _context;
    static String Name = new String();
    static String Age = new String();

    public JsonParseHelper(Context cxt){
        _context=cxt;
    }

    //将json字符串解析为Book实体
    public List jsonToBooks(String jsonstring){
        List ls=new ArrayList();
        try {
            JSONObject booklist=new JSONObject(jsonstring);
            Name = booklist.getString("Name");
            Age = booklist.getString("Age");
            JSONArray list=booklist.getJSONArray("PlayerList");
            for (int i=0;i<=list.length()-1;i++) {
                JSONObject book=list.getJSONObject(i);
                Book bk=new Book();
                bk.set_username(book.getString("username"));
                bk.set_userpass(book.getString("userpass"));
                bk.set_sex(book.getString("sex"));
                ls.add(bk);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ls;
    }

////解析json中"authors 节点的值"
//    private List getAuthors(JSONArray authors){
//        List ls=new ArrayList();
//        for (int i=0;i<=authors.length()-1;i++) {
//            try {
//                String author=authors.getString(i);
//                ls.add(author);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return ls;
//    }


//实体转换为json字符串
    public String booksToJson(List<Book> books) throws JSONException {
        String jsonstring="";
        JSONObject booklist=new JSONObject(); //最外围对象
        JSONArray booklistvalue=new JSONArray();//"booklist"的值

       // String jsonstring = new JSONStringer().object().key("title").value("android开发教程").endObject().toString();
       for(int i=0;i<=books.size()-1;i++){
           JSONObject book=new JSONObject();
           book.put("username",books.get(i).get_username());
           book.put("userpass",books.get(i).get_userpass());
           book.put("sex",books.get(i).get_sex());
           booklistvalue.put(book);
       }
       booklist.put("booklist",booklistvalue);
        jsonstring=booklist.toString();
        return jsonstring;
    }

//    private JSONArray authorsToJSONArray(List<String> authorsstring){
//        JSONArray ja=new JSONArray();
//        for(int i=0;i<=authorsstring.size()-1;i++){
//            ja.put(authorsstring.get(i));
//        }
//        return ja;
//    }


}
//package com.chapters.z.jsonparsesample;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.util.ArrayList;
//
//import java.util.List;
//
//
///**
// * Created by Administrator on 2016/11/27.
// */
//
//public class JsonParseHelper {
//    private Context _context;
//
//
//    public JsonParseHelper(Context cxt){
//        _context=cxt;
//    }
//
//    //将json字符串解析为Book实体
//    public List jsonToBooks(String jsonstring){
//        List ls=new ArrayList();
//        try {
//            JSONObject booklist=new JSONObject(jsonstring);
//            JSONArray list=booklist.getJSONArray("booklist");
//            for (int i=0;i<=list.length()-1;i++) {
//                JSONObject book=list.getJSONObject(i);
//                Book bk=new Book();
//                bk.set_title(book.getString("title"));
//                JSONArray authors=book.getJSONArray("authors");
//                bk.set_authors((List<String>) getAuthors(authors));
//                ls.add(bk);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return ls;
//    }
//
//    //解析json中"authors 节点的值"
//    private List getAuthors(JSONArray authors){
//        List ls=new ArrayList();
//        for (int i=0;i<=authors.length()-1;i++) {
//            try {
//                String author=authors.getString(i);
//                ls.add(author);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return ls;
//    }
//
//
//    //实体转换为json字符串
//    public String booksToJson(List<Book> books) throws JSONException {
//        String jsonstring="";
//        JSONObject booklist=new JSONObject(); //最外围对象
//        JSONArray booklistvalue=new JSONArray();//"booklist"的值
//
//        // String jsonstring = new JSONStringer().object().key("title").value("android开发教程").endObject().toString();
//        for(int i=0;i<=books.size()-1;i++){
//            JSONObject book=new JSONObject();
//            book.put("title",books.get(i).get_title());
//            book.put("authors",authorsToJSONArray(books.get(i).get_authors()));
//            booklistvalue.put(book);
//        }
//        booklist.put("booklist",booklistvalue);
//        jsonstring=booklist.toString();
//        return jsonstring;
//    }
//
//    private JSONArray authorsToJSONArray(List<String> authorsstring){
//        JSONArray ja=new JSONArray();
//        for(int i=0;i<=authorsstring.size()-1;i++){
//            ja.put(authorsstring.get(i));
//        }
//        return ja;
//    }
//
//
//}