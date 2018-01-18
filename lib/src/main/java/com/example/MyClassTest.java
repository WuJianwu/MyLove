package com.example;

import java.io.UnsupportedEncodingException;

public class MyClassTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String source = "1234567.785";
        String dest = "1234567.785";
        String result = null;

        if(source.equals(".") && dest.toString().length() == 0){
            result = "0.";
        }

        if(dest.toString().contains(".")){
            int index = dest.toString().indexOf(".");
            int lengthAfter = dest.substring(index).length();
            int lengthBefore = dest.substring(0,index).length();
            System.out.println("length:  " + lengthAfter);
            System.out.println("length:  " + lengthBefore);
            if (lengthBefore > 6){
                System.out.println("length超过长度" );
            }else if (lengthAfter > 3){
                result = dest.substring(0,lengthBefore+3);
            }

        }

        System.out.println("addr333:  " + result);

    }

}
