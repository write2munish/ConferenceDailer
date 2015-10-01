package com.example.munishgupta.conferencedialer.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.munishgupta.conferencedialer.R;
import com.example.munishgupta.conferencedialer.infrastructure.ConferenceDialerApplication;
import com.example.munishgupta.conferencedialer.infrastructure.ConfigParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by munishgupta on 22/09/15.
 */
public class LoginActivity extends BaseActivity {

    //private String popUpContents[];
    private ListView listView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.activity_login);

        listView = (ListView) findViewById(R.id.listView);

        String [] popUpContents =   getEmailAccounts();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, popUpContents);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemValue = (String) listView.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),
                        "Primary Account : " + itemValue + " stored", Toast.LENGTH_LONG).show();


                List<String> dataList = Arrays.asList(itemValue.split(","));

                //store the data across the sessions
                SharedPreferences settings = getSharedPreferences(ConfigParam.PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(ConfigParam.PRIMARY_EMAIL, dataList.get(0));
                editor.putString(ConfigParam.PRIMARY_ACCOUNT_TYPE, dataList.get(1));
                // Commit the edits!
                editor.commit();

                //set the data in the local context
                ((ConferenceDialerApplication)getApplication()).getAuth().getUserAccount().setAccountType(dataList.get(1));
                ((ConferenceDialerApplication)getApplication()).getAuth().getUserAccount().setEmailAccount(dataList.get(0));

                //go back to the main activity
                Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                LoginActivity.this.startActivity(mainActivityIntent);

                LoginActivity.this.finish();
            }
        });
    }

    private String[] getEmailAccounts() {

        List<String> emailList = new ArrayList<>();
        String [] popUpContents;
        popUpContents = null;
        try {

            Account[] accounts = AccountManager.get(this).getAccounts();
            for (Account account : accounts) {
                System.out.println(account.name + "," + account.type);
                if (account.type.contains("google") || account.type.contains("exchange") || account.type.contains("office")) {
                    if(account.name.contains("@"))
                        emailList.add(account.name + "," + account.type);
                }
            }
        } catch (Exception e) {
            //Log.i("Exception", "Exception:" + e);
        }

        if (emailList.size() > 0) {
            popUpContents = new String[emailList.size()];
            emailList.toArray(popUpContents);
        }
        return popUpContents;
    }
}
