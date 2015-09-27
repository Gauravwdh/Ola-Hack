package com.travelgeeks.olahackathon.utilities;

import android.content.Context;

import com.travelgeeks.olahackathon.OlaHackathonApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gauravwadhwa on 27/09/15.
 */
public class SmsParser {

    private static SmsParser instance;

    private HashMap<String, String> map;

    private SmsParser(Context context) {
        map = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(Util.readAssetFile(context, "sms_rules.json"));
            JSONArray rules = jsonObject.getJSONArray("rules");
            for (int i = 0; i < rules.length(); i++) {
                JSONObject object = rules.getJSONObject(i);
                String key = object.getString("sender");
                String value = object.getString("regex");
                map.put(key, value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static SmsParser getInstance() {
        instance = new SmsParser(OlaHackathonApplication.getInstance());
        return instance;
    }


    public boolean matchFound(String sender, String message) {
        if (!SmsUtil.isCompanyMessage(sender)) {
            return false;
        }
        String shortName = SmsUtil.getShortNameForSender(sender);
        return map.containsKey(shortName);
    }

    public long getTime(String sender, String message) {
        if (!matchFound(sender, message)) {
            return -1;
        }
        String shortName = SmsUtil.getShortNameForSender(sender);
        Pattern pattern = Pattern.compile(map.get(shortName));
        Matcher matcher = pattern.matcher(message);
        if (!matcher.find()) {
            return -1;
        }
        String date = matcher.group(1);
        String month = matcher.group(2);
        String year = matcher.group(3);
        String hh = matcher.group(4);
        String mm = matcher.group(5);
        String dayTime = matcher.group(6);
        return getTime(date, month, year, hh, mm, dayTime);
    }


    private static long getTime(String date, String month, String year, String hh, String mm, String dayTime) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DATE, Integer.parseInt(date));
        instance.set(Calendar.MONTH, Integer.parseInt(month));
        instance.set(Calendar.YEAR, Integer.parseInt(year));
        instance.set(Calendar.HOUR, Integer.parseInt(hh));
        instance.set(Calendar.MINUTE, Integer.parseInt(mm));
        if (dayTime.equalsIgnoreCase("PM")) {
            instance.add(Calendar.HOUR, 12);
        }
        return instance.getTimeInMillis();
    }


}
