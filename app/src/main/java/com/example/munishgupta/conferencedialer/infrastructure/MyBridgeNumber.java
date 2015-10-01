package com.example.munishgupta.conferencedialer.infrastructure;

/**
 * Created by munishgupta on 29/09/15.
 */
public class MyBridgeNumber {
    private String myPrimaryBridgeNumber = "";
    private String mySecondaryBridgeNumber ="";
    private String myBridgeCode = "";
    private String myHostCode = "";

    public String getMyPrimaryBridgeNumber() {
        return myPrimaryBridgeNumber;
    }

    public void setMyPrimaryBridgeNumber(String myPrimaryBridgeNumber) {
        this.myPrimaryBridgeNumber = myPrimaryBridgeNumber;
    }

    public String getMySecondaryBridgeNumber() {
        return mySecondaryBridgeNumber;
    }

    public void setMySecondaryBridgeNumber(String mySecondaryBridgeNumber) {
        this.mySecondaryBridgeNumber = mySecondaryBridgeNumber;
    }

    public String getMyBridgeCode() {
        return myBridgeCode;
    }

    public void setMyBridgeCode(String myBridgeCode) {
        this.myBridgeCode = myBridgeCode;
    }

    public String getMyHostCode() {
        return myHostCode;
    }

    public void setMyHostCode(String myHostCode) {
        this.myHostCode = myHostCode;
    }
}
