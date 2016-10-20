package com.shaznee.cracker.core;

import com.shaznee.cracker.exceptions.CrackerException;
import com.shaznee.cracker.exceptions.IncorrectPasswordException;
import com.shaznee.cracker.model.CrackResult;

/**
 * Created by SHAZNEE on 10-Oct-16.
 */
public interface CrackerType {

    CrackResult attempt(String password) throws IncorrectPasswordException, CrackerException;

}
