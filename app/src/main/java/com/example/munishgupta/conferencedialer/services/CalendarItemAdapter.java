package com.example.munishgupta.conferencedialer.services;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.munishgupta.conferencedialer.R;
import com.example.munishgupta.conferencedialer.infrastructure.Helper;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CalendarItemAdapter extends ArrayAdapter<CalendarVO> {

    private final Context context;
    private final ArrayList<CalendarVO> calendarList;


    public CalendarItemAdapter(Context context, CalendarVO[] objects) {
        super(context, R.layout.list_calendar_row, objects);
        this.context = context;
        this.calendarList = new ArrayList<>(Arrays.asList(objects));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("CalendarItemAdapter:getView:position", Integer.toString(position));

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflator.inflate(R.layout.list_calendar_row,parent,false);
        TextView meetingTime =(TextView) rowView.findViewById(R.id.meetingTime);
        TextView meetingTitle =(TextView) rowView.findViewById(R.id.meetingTitle);

        meetingTime.setText(convertTime(calendarList.get(position).getStartTime()) + " - " + convertTime(calendarList.get(position).getEndTime()));
        meetingTitle.setText(calendarList.get(position).getTitle()+"\n" + Helper.getString(calendarList.get(position).getLocation()) + "\n\n" + calendarList.get(position).getDescription());

        if(position % 2 == 0){
            rowView.findViewById(R.id.calendarRow).setBackgroundColor(context.getResources().getColor(R.color.rowlistcolor));
        }

        return rowView;
    }

    public String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

}
