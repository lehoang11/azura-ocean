package com.azura.lisa.utils;

import com.azura.lisa.common.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CommonUtils {
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

}
