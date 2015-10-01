package com.example.munishgupta.conferencedialer.services;

/**
 * Created by munishgupta on 22/09/15.
 */
public class CalendarVO {
    private int calendarID;
    private String title;
    private String location;
    private String description;
    private long startTime;
    private long endTime;


//    @Override
//    public String toString() {
//        return
//                "calendarID=" + calendarID + ConfigParam.delimiter +
//                        " title='" + title + '\'' + ConfigParam.delimiter +
//                        " location='" + location + '\'' + ConfigParam.delimiter +
//                        " endTime='" + endTime + '\'' + ConfigParam.delimiter +
//                        " startTime='" + startTime + '\'' + ConfigParam.delimiter +
//                        " description='" + description + '\''
//                ;
//    }


    @Override
    public String toString() {
        return "CalendarVO{" +
                "calendarID=" + calendarID +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalendarID() {
        return calendarID;
    }

    public void setCalendarID(int calendarID) {
        this.calendarID = calendarID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
