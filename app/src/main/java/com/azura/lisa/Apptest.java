package com.azura.lisa;


import com.azura.common.utils.CommonUtils;

public class Apptest {
    public static void main(String[] args) {
       // String ul = "http://171.244.4.46:8000/static/image/20190715/1563123695396_nhung-hinh-anh-dep-14.jpg";
       // String url = CommonUtils.getExtensionMedia(ul);
        //System.out.println("Filename: " + url );


            String s = "";
            double d;
            for (int i = 1; i <= 16; i++) {
                d = Math.random() * 10;
                s = s + ((int)d);
                if (i % 4 == 0 && i != 16) {
                    s = s + "-";
                }
            }

            System.out.println(s);

    }
}
