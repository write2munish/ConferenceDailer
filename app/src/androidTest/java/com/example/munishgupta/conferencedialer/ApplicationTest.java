package com.example.munishgupta.conferencedialer;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.example.munishgupta.conferencedialer.infrastructure.Helper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

        Helper.parseMessageForMeetingID("Bridge Id: 45782  PassCode:12345 ");


    }
}