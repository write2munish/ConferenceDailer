package com.example.munishgupta.conferencedialer.infrastructure;

import android.content.Context;

/**
 * Created by munishgupta on 22/09/15.
 */
public class Auth {

    private final Context context;
    private UserAccount userAccount;
    private MyBridgeNumber bridgeNumber;

    public Auth(Context context) {
        this.context = context;
        userAccount = new UserAccount();
        bridgeNumber = new MyBridgeNumber();
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public MyBridgeNumber getBridgeNumber() {return bridgeNumber;}
}
