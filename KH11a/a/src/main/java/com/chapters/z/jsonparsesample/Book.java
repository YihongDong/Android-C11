package com.chapters.z.jsonparsesample;

import java.util.List;

/**
 * Created by Administrator on 2016/10/30.
 */

public class Book {
    private String username;
    private String userpass;
    private String sex;

    public void set_username(String str){
        username=str;
    }
    public String get_username(){
        return username;
    }

    public void set_userpass(String str){
        userpass=str;
    }
    public String get_userpass(){
        return userpass;
    }

    public void set_sex(String str){
        sex=str;
    }
    public String get_sex(){
        return sex;
    }


}
//package com.chapters.z.jsonparsesample;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2016/10/30.
// */
//
//public class Book {
//    private String _title;
//    private List<String> authors;
//
//    public void set_title(String str){
//        _title=str;
//    }
//    public String get_title(){
//        return _title;
//    }
//
//    public void set_authors(List<String> ls){
//        authors=ls;
//    }
//    public List get_authors(){
//        return authors;
//    }
//
//}