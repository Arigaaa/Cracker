package com.shaznee.crack.cracker;

import com.shaznee.crack.exceptions.CrackerException;
import com.shaznee.crack.model.CrackResult;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public abstract class CrackerImpl implements Cracker{

    public final String CLEARLINE_FMT = "\r%30s\r";

    protected CrackerType crackerType;

    @Override
    public void setCrackerType(CrackerType crackerType) {
        this.crackerType = crackerType;
    }

    @Override
    public CrackResult crack() throws CrackerException {
        long currentTimeMillis = System.currentTimeMillis();
        CrackResult crackResult  = run();
        crackResult.setTimeTaken(System.currentTimeMillis() - currentTimeMillis);
        return crackResult;
    }

    protected abstract CrackResult run() throws CrackerException;

}
