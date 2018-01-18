package com.example;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClass {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String str1 = "/storage/name/map.png";
//        String str2 = "/storage/name/";
//        System.out.println(str1.contains(str2));
//        int insert[] = {5,2,4,6,1,3};
//        for (int i = 0; i < insert.length ; i++) {
//
//        }

//        Stack<String> strings = new Stack<>();
//        strings.push("123");
//        System.out.println("first one: " + strings);
//        strings.push("456");
//        System.out.println("first two: " + strings);
//        strings.pop();
//        System.out.println("second one: " + strings);
//
//        Queue<String> stringQueue = new SynchronousQueue<>();
//        stringQueue.add("abc");
//        stringQueue.add("def");
//        System.out.println("first two: " + stringQueue);
//        stringQueue.remove();
//        stringQueue.peek();
//        stringQueue.poll();

        String addr = "广东省深圳市南山区粤海街道铜鼓路40号大冲国际中心";
        String addr333 = "2017.01.01-2035.01.01";
        byte[] b = addr.getBytes("GB2312");
        System.out.println("addr:  " + b);
        String test = new String(b,"GB2312");
        System.out.println("addr111:  " + addr333.substring(0,10));
        System.out.println("addr222:  " + addr333.substring(11));
        System.out.println("addr333:  " + addr333.substring(11,addr333.length()));
//
////        String addr2 = "广东省深圳市南山区粤海街道铜鼓路40号大冲国际中心";
//        String[] a = getResolvedAddress("广东省深圳市南山区啊啊啊");
//        for (int i = 0; i < a.length; i++) {
//            System.out.println("addr:index " + a[i]);
//        }
//        String addr3 = new String(addr2.getBytes("gbk"),"gbk");
//        String a2 = new String("省".getBytes("gbk"),"gbk");
//        for (int i = 0; i < addr3.length(); i++) {
//            String index = String.valueOf(addr3.charAt(i));
//            System.out.println("addr:index " + index);
//            System.out.println("addr:a2 " + a2);
//            if (index.equals(a2)){
//                System.out.println("addr: 55555555555555 " + addr2.indexOf(index));
//            }
//        }
//        try {
//            String a = "省";
//            String addr3 = new String(addr2.getBytes("gbk"),"gbk");
//            String a2 = new String(a.getBytes("gbk"),"gbk");
//
//
//
//            Map<String, String> map = new HashMap<>();
//            int shengIndex = addr3.indexOf(a2, 0);
//            int shiIndex = addr3.indexOf("市", 0);
//            int quIndex = addr3.indexOf("区", 0);
//            System.out.println("addr: " + addr3);
//            System.out.println("addr: " + a2);
//            System.out.println("addr: " + shengIndex);
//            System.out.println("addr: " + shiIndex);
//            System.out.println("addr: " + quIndex);
//
//            String s = "12345#aaa#bbb#67890";
//            int n = s.indexOf("#");
//            System.out.println("addr: " + n);
//
//            String str = "yulv # 123456 # yulv@21cn.com";
//            Matcher matcher = Pattern.compile(a).matcher(addr3);
//            if (matcher.find()) {
//                System.out.println(matcher.start());
//            } else {
//                System.out.println("null");
//            }
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

//        String occupationNo1 = addr.substring();
//        map.put("occupationNo1","03");
//        map.put("occupationNo2","03");
//        map.put("occupationNo3","03");
//        map.put("occupationNo4","03");

//        System.out.println("second one: " + map.toString());

    }

    public static String[] getResolvedAddress(String address) {

        String[] res = new String[4];
        String provice = "";
        String city = "";
        String block = "";
        String addr = "";
        Pattern pBlock = Pattern.compile("(.*?)(?:省|自治区|.*?)(.*)市(.*?)区(.*?)");
        if (address.contains("省") || address.contains("自治区")) {
            pBlock = Pattern.compile("(.*?)(?:省|自治区)(.*)市(.*?)区(.*?)");
        }

        Matcher mBlock = pBlock.matcher(address);
        while (mBlock.find()) {
            provice = mBlock.group(1);
            city = mBlock.group(2);
            block = mBlock.group(3);
            if (address.contains("自治区")) {
                addr = address.split("自治区")[1].split("区")[1];
            } else {
                addr = address.split("区")[1];
            }
        }

        if (city.equals("北京") || city.equals("上海") || city.equals("天津") || city.equals("重庆")) {
            res[0] = city + "市";
        } else if (provice.equals
                ("内蒙古") || provice.equals("新疆维吾尔") || provice.equals("宁夏回族") || provice.equals("广西壮族") || provice.equals("西藏")) {
            res[0] = provice + "自治区";
        } else if (provice != null && provice.length() > 0) {
            res[0] = provice + "省";
        } else {
            res[0] = "";
        }

        if (city != null && city.length() > 0) {
            res[1] = city + "市";
        } else {
            res[1] = "";
        }
        if (block != null && block.length() > 0) {
            res[2] = block + "区";
        } else {
            res[2] = "";
        }

        if (addr != null && addr.length() > 0) {
            res[3] = addr;
        } else {
            res[3] = "";
        }

        for (String s : res) {
            System.out.println(s);
        }

        return res;
    }

}
