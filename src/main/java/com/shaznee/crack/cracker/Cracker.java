package com.shaznee.crack.cracker;

import com.shaznee.crack.exceptions.CrackerException;
import com.shaznee.crack.model.CrackResult;
import com.shaznee.crack.modes.BruteForceCracker;
import com.shaznee.crack.modes.DictionaryAttackCracker;

import java.io.File;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public interface Cracker {

    static Cracker BRUTE_FORCE_MODE(File file, int passwordLength, CharType charType) {
        return new BruteForceCracker(file, passwordLength, charType);
    }

    static Cracker DICTIONARY_MODE(File file, File dictionary) {
        return new DictionaryAttackCracker(file, dictionary);
    }

    CrackResult crack() throws CrackerException;

}
