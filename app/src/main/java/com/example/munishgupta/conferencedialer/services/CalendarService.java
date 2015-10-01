package com.example.munishgupta.conferencedialer.services;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import com.example.munishgupta.conferencedialer.infrastructure.ConferenceDialerApplication;

import java.util.Calendar;
import java.util.HashSet;

/**
 * Created by munishgupta on 23/09/15.
 */
public class CalendarService {

    ConferenceDialerApplication application = null;

    // Default constructor
    public CalendarVO[] readCalendar(Context context, Application app, int day, int month, int year) {
        application = (ConferenceDialerApplication) app;
        return readCalendarData(context, day, month, year);
    }

    // Use to specify specific the time span
    private CalendarVO[] readCalendarData(Context context, int day, int month, int year) {

//        Log.i("CalendarService:readCalendarData:day",Integer.toString(day));
//        Log.i("CalendarService:readCalendarData:month",Integer.toString(month));
//        Log.i("CalendarService:readCalendarData:year",Integer.toString(year));

        ContentResolver contentResolver = context.getContentResolver();

        //set the day start and end time
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        long startDay = calendar.getTimeInMillis();
        calendar.set(year, month, day, 23, 59, 59);
        long endDay = calendar.getTimeInMillis();

        String accountType = application.getAuth().getUserAccount().getAccountType();
//        Log.i("CalendarService:readCalendarData:accountType",accountType);

        String selection = CalendarContract.Calendars.ACCOUNT_TYPE + " = ? AND " + CalendarContract.Events.DTSTART + " >= ? AND " + CalendarContract.Events.DTSTART + "<= ?";
        String[] selectionArgs = new String[]{accountType, Long.toString(startDay), Long.toString(endDay)};
        String sortOrder = "dtstart DESC";

        Cursor cursor = contentResolver.query(Uri.parse("content://com.android.calendar/events"),
                new String[]{"calendar_id", "title", "description", "dtstart", "dtend", "eventLocation"},
                selection, selectionArgs, sortOrder);


        // Create a set containing all of the CalendarDisplayActivity IDs available on the phone
        return getCalenderIds(cursor);

    }

    // Creates the list of CalendarDisplayActivity ids and returns it in a set
    private CalendarVO[] getCalenderIds(Cursor cursor) {

        HashSet<CalendarVO> calendarVO = new HashSet<>();

        try {
            // If there are more than 0 calendars, continue
            if (cursor.getCount() > 0) {
                // Loop to set the id for all of the calendars
                while (cursor.moveToNext()) {
                    CalendarVO calendar = new CalendarVO();

                    calendar.setCalendarID(cursor.getInt(0));
                    calendar.setTitle(cursor.getString(1));
                    calendar.setLocation(cursor.getString(5));
                    calendar.setDescription(cursor.getString(2));
                    calendar.setStartTime(cursor.getLong(3));
                    calendar.setEndTime(cursor.getLong(4));
                    calendarVO.add(calendar);

                }
            } else
                Log.i("CalendarService:getCalenderIds:", "No calendar entries found");

        } catch (Exception e) {
            Log.i("Exception", "Exception:" + e);
        } finally {
            if(cursor != null) {
                cursor.close();
            }
        }
        return calendarVO.toArray(new CalendarVO[calendarVO.size()]);

    }
}
