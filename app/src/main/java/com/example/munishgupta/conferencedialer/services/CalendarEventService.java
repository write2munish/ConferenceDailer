package com.example.munishgupta.conferencedialer.services;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import com.example.munishgupta.conferencedialer.infrastructure.ConferenceDialerApplication;

/**
 * Created by munishgupta on 24/09/15.
 */
public class CalendarEventService {

    public static CalendarVO getCalendarEventDetails(Context context, Application app, int id){

        ConferenceDialerApplication application = (ConferenceDialerApplication) app;
        ContentResolver contentResolver = context.getContentResolver();
        String accountType = application.getAuth().getUserAccount().getAccountType();

        Log.i("getCalendarEventDetails",accountType);

        String selection = CalendarContract.Calendars.ACCOUNT_TYPE + " = ? AND " + CalendarContract.Events.CALENDAR_ID + " = ? ";
        String[] selectionArgs = new String[]{accountType,Integer.toString(id)};


        Cursor cursor = contentResolver.query(Uri.parse("content://com.android.calendar/events"),
                new String[]{"calendar_id", "title", "description", "dtstart", "dtend", "eventLocation"},
                selection, selectionArgs, null);

        CalendarVO calendarVO = CalendarEventService.getCalenderEventData(cursor);

        return calendarVO;
    }

    private static CalendarVO getCalenderEventData(Cursor cursor) {
        CalendarVO calendarVO = new CalendarVO();
        try {
            // If there are more than 0 calendars, continue
            if (cursor.getCount() > 0) {
                // Loop to set the id for all of the calendars
                while (cursor.moveToNext()) {
                    calendarVO.setCalendarID(cursor.getInt(0));
                    calendarVO.setTitle(cursor.getString(1));
                    calendarVO.setLocation(cursor.getString(5));
                    calendarVO.setDescription(cursor.getString(2));
                    calendarVO.setStartTime(cursor.getLong(3));
                    calendarVO.setEndTime(cursor.getLong(4));

                    Log.i("CalendarEventService:getCalenderIds:", calendarVO.toString());
                    break;
                }
            } else
                Log.i("CalendarEventService:getCalenderIds:", "No calendar entries found");

        } catch (Exception e) {
            Log.i("Exception", "Exception:" + e);
        } finally {
            cursor.close();
        }
        return calendarVO;

    }
}
