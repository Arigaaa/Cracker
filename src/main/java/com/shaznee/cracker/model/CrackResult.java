package com.shaznee.cracker.model;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class CrackResult {

    public static final long TIME_SECONDS = 1000;
    public static final long TIME_MINUTE = 1000 * 60;
    public static final long TIME_HOURS = 1000 * 60 * 60;

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

    public long getTimeTaken(long timeInUnits) {
        return timeTaken/timeInUnits;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
