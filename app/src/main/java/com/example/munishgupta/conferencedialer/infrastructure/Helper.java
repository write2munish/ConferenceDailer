package com.example.munishgupta.conferencedialer.infrastructure;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.example.munishgupta.conferencedialer.services.CalendarVO;
import com.google.gson.Gson;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by munishgupta on 25/09/15.
 */
public class Helper {

    private static Gson gson = new Gson();

    public static String parseMessageForMeetingCode(String message) {
        String[] searchPattern = new String[]{
                "[Pp]ass[\\D]*[0-9\\s]{6,}",
                "[Pp][Ii][Nn][\\D]*[0-9]{6,}",
                "[Pp]arti[\\D]*[0-9]{6,}",
                "[Pp][wW][\\D]*[0-9]{6,}"};


        String result = searchForPattern(message, searchPattern[0]);
        if (result == null) {
            int count = 1;
            while (count < searchPattern.length) {
                result = searchForPattern(message, searchPattern[count]);
                if (result != null) break;
                count++;
            }
        }
        if (result != null) {
            if (!result.isEmpty()) {
                return getNumber(result);
            }
        }
        return "";
    }


    public static String parseMessageForMeetingID(String message) {
        String[] searchPattern = new String[]{
                "[Mm]eeti[\\D]*[0-9\\s]{6,}",
                "[Bb]rid[\\D]*[0-9]{6,}",
                "[Cc]onf[\\D]*[0-9]{6,}",
                "[Pp]arti[\\D]*[0-9]{6,}",
//                "[Ii][dD]*[0-9\\s]{6,}",
                "[Ee]ven[\\D]*[0-9\\s]{6,}"};


        String result = Helper.searchForPattern(message, searchPattern[0]);
        if (result != null)
            Log.i("searchForPattern", result);
        if (result == null) {
            int count = 1;
            while (count < searchPattern.length) {
                result = searchForPattern(message, searchPattern[count]);
                if (result != null) break;
                count++;
            }
        }
        if (result != null) {
            if (!result.isEmpty()) {
                return getNumber(result);
            }
        }
        return "";
    }


    public static String searchForPattern(String message, String pattern) {

        Pattern first = Pattern.compile(pattern);
        Matcher match = first.matcher(message);
        //Log.i("parseMessageForMeetingID",Integer.toString(match.groupCount()));

        if (match.find()) {
            String str = match.group(0);
            Log.i("searchForPattern", str);
            return str;
        }
        Log.i("searchForPattern", "no match found");
        return null;
    }

    public static String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static String getNumber(String input) {
        if (input == null) return "";
        String str = input.replaceAll("\\s+", "");
        Pattern number = Pattern.compile("[0-9]+");
        Matcher matcher1 = number.matcher(str);
        if (matcher1.find())
            return matcher1.group(0);
        return "";
    }


    public static String convertDate(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("EEE MMM d, yyyy");
        return format.format(date);
    }

    public static String getString(String msg) {
        if (msg == null) return "";
        return msg;
    }

    public static String removeQuote(String input) {
        return input.replace("\'", "");
    }

    public static void saveDataOffLine(Activity activity, String name, String value) {
        SharedPreferences settings = activity.getSharedPreferences(ConfigParam.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static String getSavedDataOffLine(Application app, String name) {
        SharedPreferences settings = app.getSharedPreferences(ConfigParam.PREFS_NAME, 0);
        return settings.getString(name, "");
    }


    public static String createDialNumber(String bridge, String host, String code) {

        String dialString = "tel:";
        dialString = dialString + bridge + Uri.parse(",") + Uri.parse(",");
        if (host != null && !host.isEmpty()) {
            dialString = dialString + host + Uri.parse("#");
        }
        if (code != null && !code.isEmpty()) {
            dialString = dialString + Uri.parse(",") + code + Uri.parse("#");
        }
        return dialString;
    }

    public static String getSerializedString(CalendarVO calendarVO) {
        return gson.toJson(calendarVO);
    }

    public static CalendarVO createObjectFromSerializedMessage(String message) {
        return gson.fromJson(message, CalendarVO.class);
    }

}
