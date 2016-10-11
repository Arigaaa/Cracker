package com.shaznee.crack.cracker;

import com.shaznee.crack.exceptions.CrackerException;
import com.shaznee.crack.exceptions.IncorrectPasswordException;
import com.shaznee.crack.model.CrackResult;

/**
 * Created by SHAZNEE on 10-Oct-16.
 */
public interface CrackerType {

    CrackResult attempt(String password) throws IncorrectPasswordException, CrackerException;

}
