package com.shaznee.crack.model;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class CrackResult {

    private String password;
    private long timeTaken;
    private boolean isSuccessful = false;

    public CrackResult(boolean isSuccessful, String password) {
        this.isSuccessful = isSuccessful;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
