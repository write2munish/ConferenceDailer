package com.example.munishgupta.conferencedialer.infrastructure;

import android.app.Application;

/**
 * Created by munishgupta on 22/09/15.
 */
public class ConferenceDialerApplication extends Application {

    private Auth auth;


    @Override
    public void onCreate(){
        super.onCreate();
        auth = new Auth(this);

        loadAuthData(auth);
        loadBridgeData(auth);
    }

    private void loadBridgeData(Auth auth) {

        String primaryBridge = Helper.getSavedDataOffLine(this,ConfigParam.PRIMARY_BRIDGE_NUMBER);
        String secondaryBridge= Helper.getSavedDataOffLine(this, ConfigParam.SECONDARY_BRIDGE_NUMBER);
        String myBridgeCode = Helper.getSavedDataOffLine(this, ConfigParam.MY_BRIDGE_CODE);
        String myBridgeHost = Helper.getSavedDataOffLine(this, ConfigParam.MY_BRIDGE_HOST);

        auth.getBridgeNumber().setMyPrimaryBridgeNumber(primaryBridge);
        auth.getBridgeNumber().setMySecondaryBridgeNumber(secondaryBridge);
        auth.getBridgeNumber().setMyBridgeCode(myBridgeCode);
        auth.getBridgeNumber().setMyHostCode(myBridgeHost);
    }

    private void loadAuthData(Auth auth) {
        //load the existing Auth privileges for the user

        //SharedPreferences settings = getSharedPreferences(ConfigParam.PREFS_NAME, 0);
        String primaryEmail = Helper.getSavedDataOffLine(this,ConfigParam.PRIMARY_EMAIL);
        String primaryAccountOwner= Helper.getSavedDataOffLine(this, ConfigParam.PRIMARY_ACCOUNT_OWNER);
        String primaryAccountType = Helper.getSavedDataOffLine(this, ConfigParam.PRIMARY_ACCOUNT_TYPE);



        //        settings.getString(ConfigParam.PRIMARY_EMAIL, "");
//        String primaryAccountOwner= settings.getString(ConfigParam.PRIMARY_ACCOUNT_OWNER, "");
//        String primaryAccountType = settings.getString(ConfigParam.PRIMARY_ACCOUNT_TYPE, "");

        auth.getUserAccount().setEmailAccount(primaryEmail);
        auth.getUserAccount().setAccountType(primaryAccountType);
        auth.getUserAccount().setAccountOwner(primaryAccountOwner);

    }

    public Auth getAuth() {
        return auth;
    }
}
