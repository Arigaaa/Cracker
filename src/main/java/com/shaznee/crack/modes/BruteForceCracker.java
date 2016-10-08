package com.shaznee.crack.modes;

import com.shaznee.crack.cracker.CharType;
import com.shaznee.crack.cracker.CrackerImpl;
import com.shaznee.crack.exceptions.CrackerException;
import com.shaznee.crack.model.CrackResult;

import java.io.File;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class BruteForceCracker extends CrackerImpl {

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final int[] NUMBERS = {0,1,2,3,4,5,6,7,8,9};

    private CharType charType;
    private int passwordLength;

    public BruteForceCracker(File file, int passwordLength, CharType charType) {
        super(file);
        this.charType = charType;
        this.passwordLength = passwordLength;
    }

    @Override
    public CrackResult crack() throws CrackerException {
        //        final String CLEARLINE_FMT = "\r%30s\r";
//
//        try {
//            for (int i = 0; i <= 100; i++) {
//                System.out.printf("\rPercent completed: %3d%%", i);
//                Thread.sleep(500);
//            }
//            System.out.printf(CLEARLINE_FMT + "Done!\n", "");
//        } catch (InterruptedException e) {
//            System.out.printf(CLEARLINE_FMT + "Interrupted!\n", "");
//        }
        return null;
    }
}
