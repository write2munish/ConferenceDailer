package com.example.munishgupta.conferencedialer.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.munishgupta.conferencedialer.R;
import com.example.munishgupta.conferencedialer.infrastructure.ConferenceDialerApplication;
import com.example.munishgupta.conferencedialer.infrastructure.ConfigParam;
import com.example.munishgupta.conferencedialer.infrastructure.Helper;
import com.example.munishgupta.conferencedialer.infrastructure.MyBridgeNumber;
import com.example.munishgupta.conferencedialer.services.CalendarItemAdapter;
import com.example.munishgupta.conferencedialer.services.CalendarService;
import com.example.munishgupta.conferencedialer.services.CalendarVO;

import java.util.Calendar;

/**
 * Created by munishgupta on 22/09/15.
 */
public class MainActivity extends BaseAuthenticatedActivity {

    private CalendarService calendarService = new CalendarService();
    private CalendarItemAdapter adapter;
    private ListView list;
    private TextView currentDate;
    private TextView errorText;
    private Intent intent;// = new Intent(this,CalendarItemDialActivity.class);


    @Override
    protected void onConferenceDialerCreate(Bundle savedBundle) {

        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listView);
        errorText = (TextView) findViewById(R.id.errorMsgText);

        intent = new Intent(this, CalendarItemDialActivity.class);

        currentDate = (TextView) findViewById(R.id.currentDate);
        currentDate.setText(Helper.convertDate(Calendar.getInstance().getTime().getTime()));

        Calendar cal = Calendar.getInstance();

        CalendarVO[] calenderList = calendarService.readCalendar(getApplicationContext(), getApplication(), cal.get(Calendar.DATE), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));

        adapter = new CalendarItemAdapter(getApplicationContext(), calenderList);

        // Here, you set the data in your ListView
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //String selectedItem = list.getItemAtPosition(i).toString();
                String selectedItem = Helper.getSerializedString((CalendarVO) list.getItemAtPosition(i));

                intent.putExtra(ConfigParam.CALENDAR_ITEM, selectedItem);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                startSettings();
                return true;
            case R.id.favorites:
                showFavorites();
                return true;
            case R.id.myBridge:
                dialMyBridge();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showFavorites() {

        Toast.makeText(getApplicationContext(),
                "Coming Soon", Toast.LENGTH_LONG).show();
    }

    private void dialMyBridge() {
        MyBridgeNumber bridgeNumber = ((ConferenceDialerApplication)getApplication()).getAuth().getBridgeNumber();

        if(bridgeNumber.getMyPrimaryBridgeNumber().isEmpty() || bridgeNumber.getMyBridgeCode().isEmpty()){
            errorText.setText("Missing Bridge Details");
            return;
        }

        String dialString = Helper.createDialNumber(bridgeNumber.getMyPrimaryBridgeNumber(),bridgeNumber.getMyBridgeCode(),bridgeNumber.getMyHostCode());

        //Log.i("dialNumber", dialString);

        Toast.makeText(getApplicationContext(),
                "Dialing : " + dialString, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(dialString));
        startActivity(intent);

    }

    private void startSettings() {
        Intent intent = new Intent (this,SettingActivity.class);
        startActivity(intent);
    }

}
