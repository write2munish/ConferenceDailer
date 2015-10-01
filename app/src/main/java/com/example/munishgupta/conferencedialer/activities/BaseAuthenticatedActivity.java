package com.example.munishgupta.conferencedialer.activities;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by munishgupta on 22/09/15.
 */
public abstract class BaseAuthenticatedActivity extends BaseActivity {

    @Override
    protected final void onCreate(Bundle savedBundle){

        super.onCreate(savedBundle);

        if (application.getAuth().getUserAccount().getEmailAccount().isEmpty()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        onConferenceDialerCreate(savedBundle);
    }

    protected abstract void onConferenceDialerCreate(Bundle savedBundle);

}
