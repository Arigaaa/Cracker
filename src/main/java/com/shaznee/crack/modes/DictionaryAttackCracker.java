package com.shaznee.crack.modes;

import com.shaznee.crack.cracker.CrackerImpl;
import com.shaznee.crack.exceptions.CrackerException;
import com.shaznee.crack.model.CrackResult;

import java.io.File;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class DictionaryAttackCracker extends CrackerImpl {

    public DictionaryAttackCracker(File file, File dictionary) {
        super(file);
    }

    @Override
    public CrackResult crack() throws CrackerException {
        return null;
    }
}
