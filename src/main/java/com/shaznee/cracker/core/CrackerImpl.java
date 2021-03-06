package com.shaznee.cracker.core;

import com.shaznee.cracker.exceptions.CrackerException;
import com.shaznee.cracker.core.model.CrackResult;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public abstract class CrackerImpl implements Cracker{

    protected TargetCracker targetCracker;

    protected CrackerImpl(TargetCracker targetCracker) {
        this.targetCracker = targetCracker;
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
