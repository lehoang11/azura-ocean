package com.azura.common.utils;

import com.azura.common.contants.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class CommonUtils {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZzyxwvutsrqponmlkjihgfedcba";

    public static String convertDateToString(Long unixTimestamp, String format) {
        if(unixTimestamp == null)
            return Constants.EMPTY_STRING;
        Date date = new Date(unixTimestamp*1000);
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String convertDateToString(Date date, String format) {
        if(date == null)
            return Constants.EMPTY_STRING;
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String convertToUtf8(String input) {
        try {
            return URLEncoder.encode(input, "UTF-8").replaceAll(Constants.SPACE_STRING, "%20");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    public static String covertStringToURL(String str) {
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getExtensionMedia(String urlStr) {

        String fileExtension = urlStr.substring(urlStr.lastIndexOf(".") + 1);

        return fileExtension.toLowerCase();

    }

    public static String getTypeMedia(String mediaType){
        if(Constants.mediaVideo.contains(mediaType)) return "VIDEO";

        if(Constants.mediaMp3.contains(mediaType)) return "MP3";

        return "NONE";

    }

    public static String getTuListCode(){
        String s = "";
        double d;
        for (int i = 1; i <= 16; i++) {
            d = Math.random() * 10;
            s = s + ((int)d);
            if (i % 4 == 0 && i != 16) {
                s = s + "-";
            }
        }
        return s;
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        String dateFormat = formatter.format(cal.getTime()).concat("-");
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return dateFormat.concat(builder.toString());
    }

}
