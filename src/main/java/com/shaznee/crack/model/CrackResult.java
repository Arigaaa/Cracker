package com.shaznee.crack.model;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class CrackResult {

    private String password;
    private long timeTaken;
    private boolean isSuccessful = false;

    public CrackResult(boolean isSuccessful, String password, long timeTaken) {
        this(isSuccessful);
        this.password = password;
        this.timeTaken = timeTaken;
    }

    public CrackResult(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getPassword() {
        return password;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
