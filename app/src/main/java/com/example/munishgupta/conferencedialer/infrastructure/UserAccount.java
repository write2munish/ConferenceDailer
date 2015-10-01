package com.example.munishgupta.conferencedialer.infrastructure;

/**
 * Created by munishgupta on 22/09/15.
 */
public class UserAccount {

    private String emailAccount;
    private String accountType;
    private String accountOwner;

    @Override
    public String toString() {
        return "UserAccount{" +
                "emailAccount='" + emailAccount + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountOwner='" + accountOwner + '\'' +
                '}';
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }
}
