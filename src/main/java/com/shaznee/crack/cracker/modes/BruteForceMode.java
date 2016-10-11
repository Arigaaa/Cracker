package com.shaznee.crack.cracker.modes;

import com.shaznee.crack.cracker.CharType;
import com.shaznee.crack.cracker.CrackerImpl;
import com.shaznee.crack.exceptions.CrackerException;
import com.shaznee.crack.exceptions.IncorrectPasswordException;
import com.shaznee.crack.model.CrackResult;

import java.util.Arrays;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class BruteForceMode extends CrackerImpl {

    private static final String ALPHA_NUMERIC = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private static final String CHARS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private static final String NUMBERS = "0123456789";

    private int min;
    private int max;
    private int passwordLength;

    private int[] current;

    public BruteForceMode(int passwordLength, CharType charType) {
        this.passwordLength = passwordLength;
        init(charType);
    }

    private void init(CharType charType) {
        switch (charType) {
            case NUMBERIC:
                initCracker(NUMBERS.toCharArray());
                break;
            case APHA:
                initCracker(CHARS.toCharArray());
                break;
            case ALPHA_NUMERIC:
                initCracker(ALPHA_NUMERIC.toCharArray());
                break;
        }
    }

    private void initCracker(char[] charSequence) {

        this.min = charSequence[0];
        this.max = charSequence[charSequence.length-1];

        current = new int[passwordLength + 1];
        Arrays.fill(current, 1, current.length, min);

    }

    @Override
    protected CrackResult run() throws CrackerException {
        while (current[0] == 0) {
            try {
                CrackResult crackResult = crackerType.attempt(generate());
                if (crackResult.isSuccessful()) {
                    return crackResult;
                }
            } catch (IncorrectPasswordException e) {
                continue;
            } finally {
                increase();
            }
        }
        return new CrackResult(false, "Not Found");
    }

    private String generate() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < current.length; i++) {
            stringBuilder.append((char) current[i]);
        }
        return stringBuilder.toString();
    }

    private void increase() {
        for (int i = current.length - 1; i >= 0; i--) {
            if (current[i] < max) {
                current[i]++;
                return;
            }
            current[i] = min;
        }
    }

    //        try {
//            for (int i = 0; i <= 100; i++) {
//                System.out.printf("\rPercent completed: %3d%%", i);
//                Thread.sleep(500);
//            }
//            System.out.printf(CLEARLINE_FMT + "Done!\n", "");
//        } catch (InterruptedException e) {
//            System.out.printf(CLEARLINE_FMT + "Interrupted!\n", "");
//        }
}
