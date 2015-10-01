package com.example.munishgupta.conferencedialer.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBarActivity;

import com.example.munishgupta.conferencedialer.infrastructure.ConferenceDialerApplication;

/**
 * Created by munishgupta on 22/09/15.
 */
public abstract class BaseActivity extends ActionBarActivity {
    protected ConferenceDialerApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (ConferenceDialerApplication) getApplication();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        super.setContentView(layoutResId);
    }
}

