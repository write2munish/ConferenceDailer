package com.example.munishgupta.conferencedialer.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.munishgupta.conferencedialer.R;
import com.example.munishgupta.conferencedialer.infrastructure.ConferenceDialerApplication;
import com.example.munishgupta.conferencedialer.infrastructure.ConfigParam;
import com.example.munishgupta.conferencedialer.infrastructure.Helper;
import com.example.munishgupta.conferencedialer.infrastructure.MyBridgeNumber;
import com.example.munishgupta.conferencedialer.services.CalendarVO;

import java.util.ArrayList;
import java.util.List;


public class CalendarItemDialActivity extends BaseAuthenticatedActivity {


    private TextView meetingTime, meetingDetails, errorTextView;
    private EditText meetingID, meetingPassCode;
    private Spinner meetingBridge;
    private ArrayAdapter<String> adapter;
    private List bridgeList = new ArrayList<String>();

    @Override
    protected void onConferenceDialerCreate(Bundle savedBundle) {

        setContentView(R.layout.activity_calendar_item_dialer3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MyBridgeNumber myBridgeNumber = ((ConferenceDialerApplication) getApplication()).getAuth().getBridgeNumber();


        Intent intent = getIntent();
        String message = intent.getStringExtra(ConfigParam.CALENDAR_ITEM);
        CalendarVO selectedVO = Helper.createObjectFromSerializedMessage(message);

        String searchCriteria = selectedVO.getTitle() + "\n" + selectedVO.getLocation() + "\n" + selectedVO.getDescription();

        meetingTime = (TextView) findViewById(R.id.meetingTime2);
        meetingDetails = (TextView) findViewById(R.id.meetingTitle2);
        errorTextView = (TextView) findViewById(R.id.errorMsgText);
        meetingBridge = (Spinner) findViewById(R.id.bridgeNumber);

        meetingID = (EditText) findViewById(R.id.meetingID);
        meetingPassCode = (EditText) findViewById(R.id.meetingCode);

        meetingTime.setText(Helper.convertTime(selectedVO.getStartTime()) + "-" + Helper.convertTime(selectedVO.getEndTime()));
        meetingDetails.setText(searchCriteria);

        //Log.i("MeetingCode",Helper.parseMessageForMeetingCode(searchCriteria));

        String meetingIdStr = Helper.parseMessageForMeetingID(searchCriteria);
        String meetingPassCodeStr = Helper.parseMessageForMeetingCode(searchCriteria);
        if (meetingIdStr != null) {
            meetingID.setText(meetingIdStr);
        }
        if (meetingPassCodeStr != null) {
            meetingPassCode.setText(meetingPassCodeStr);
        }

        if (!myBridgeNumber.getMyPrimaryBridgeNumber().isEmpty())
            bridgeList.add(myBridgeNumber.getMyPrimaryBridgeNumber());

        if (!myBridgeNumber.getMySecondaryBridgeNumber().isEmpty())
            bridgeList.add(myBridgeNumber.getMySecondaryBridgeNumber());


        adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_item, bridgeList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub

                View view = super.getView(position, convertView, parent);

                TextView text = (TextView) view.findViewById(R.id.text1);
                text.setTextColor(Color.BLACK);

                return view;

            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub

                View view = super.getView(position, convertView, parent);

                TextView text = (TextView) view.findViewById(R.id.text1);
                text.setTextColor(Color.BLACK);

                return view;

            }

        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meetingBridge.setAdapter(adapter);


    }

    public void dialNumber(View view) {

        if (meetingBridge.getSelectedItem() == null) {
            errorTextView.setText("Please set the Primary Bridge # in Setting Option");
            return;
        }
        String dialString = Helper.createDialNumber(meetingBridge.getSelectedItem().toString(), meetingID.getText().toString(), meetingPassCode.getText().toString());

    //    Log.i("dialNumber", dialString);
        Toast.makeText(getApplicationContext(),
                "Dialing : " + dialString, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse(dialString));
        //intent.setData();
        CalendarItemDialActivity.this.startActivity(intent);

    }
}
