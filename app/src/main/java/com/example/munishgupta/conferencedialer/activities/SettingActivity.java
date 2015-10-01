package com.example.munishgupta.conferencedialer.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.munishgupta.conferencedialer.R;
import com.example.munishgupta.conferencedialer.infrastructure.ConferenceDialerApplication;
import com.example.munishgupta.conferencedialer.infrastructure.ConfigParam;
import com.example.munishgupta.conferencedialer.infrastructure.Helper;

/**
 * Created by munishgupta on 28/09/15.
 */
public class SettingActivity extends BaseAuthenticatedActivity {


    TextView primaryBridge;
    TextView secondaryBridge;
    TextView myBridge;
    TextView myHostCode;
    TextView msgText;

    @Override
    protected void onConferenceDialerCreate(Bundle savedBundle) {
        setContentView(R.layout.activity_settings);

//        ActionBar actionBar;
//        actionBar = getSupportActionBar();

//        if (actionBar != null) {
//            // enabling action bar app icon and behaving it as toggle button
////            actionBar.setIcon(R.drawable.icon_settings);
////            actionBar.setI
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.setDisplayShowHomeEnabled(true);
//        }


        primaryBridge = (TextView) findViewById(R.id.primaryBridgeNumber);
        secondaryBridge = (TextView) findViewById(R.id.secondaryBridgeNumber);
        myBridge = (TextView) findViewById(R.id.myBridgeNumber);
        myHostCode = (TextView) findViewById(R.id.hostCode);
        msgText = (TextView) findViewById(R.id.mesgBox);

        primaryBridge.setText(Helper.getSavedDataOffLine(getApplication(), ConfigParam.PRIMARY_BRIDGE_NUMBER));
        secondaryBridge.setText(Helper.getSavedDataOffLine(getApplication(), ConfigParam.SECONDARY_BRIDGE_NUMBER));
        myBridge.setText(Helper.getSavedDataOffLine(getApplication(), ConfigParam.MY_BRIDGE_CODE));
        myHostCode.setText(Helper.getSavedDataOffLine(getApplication(), ConfigParam.MY_BRIDGE_HOST));


        final Button button = (Button) findViewById(R.id.saveSettings);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                if (primaryBridge.getText().toString().isEmpty()) {
                    msgText.setText("Primary Bridge Number is Mandatory");
                    primaryBridge.setCursorVisible(true);
                    return;
                }


                //store the data across the sessions
                Helper.saveDataOffLine(SettingActivity.this, ConfigParam.PRIMARY_BRIDGE_NUMBER, primaryBridge.getText().toString());
                ((ConferenceDialerApplication) getApplication()).getAuth().getBridgeNumber().setMyPrimaryBridgeNumber(primaryBridge.getText().toString());

                Helper.saveDataOffLine(SettingActivity.this, ConfigParam.SECONDARY_BRIDGE_NUMBER, secondaryBridge.getText().toString());
                ((ConferenceDialerApplication) getApplication()).getAuth().getBridgeNumber().setMySecondaryBridgeNumber(secondaryBridge.getText().toString());
                Helper.saveDataOffLine(SettingActivity.this, ConfigParam.MY_BRIDGE_HOST, myBridge.getText().toString());
                ((ConferenceDialerApplication) getApplication()).getAuth().getBridgeNumber().setMyBridgeCode(myBridge.getText().toString());
                Helper.saveDataOffLine(SettingActivity.this, ConfigParam.MY_BRIDGE_CODE, myHostCode.getText().toString());
                ((ConferenceDialerApplication) getApplication()).getAuth().getBridgeNumber().setMyHostCode(myHostCode.getText().toString());

                SettingActivity.this.finish();

            }

        });
    }


}
