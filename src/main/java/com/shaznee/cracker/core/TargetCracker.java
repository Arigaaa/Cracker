package com.shaznee.cracker.core;

import com.shaznee.cracker.exceptions.CrackerException;
import com.shaznee.cracker.exceptions.IncorrectPasswordException;
import com.shaznee.cracker.core.model.CrackResult;

/**
 * Created by SHAZNEE on 10-Oct-16.
 */
public interface TargetCracker {

    CrackResult attempt(String password) throws IncorrectPasswordException, CrackerException;

}
