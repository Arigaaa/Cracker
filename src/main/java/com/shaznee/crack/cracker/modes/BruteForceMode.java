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

    public static final String ALPHA_CASE_INSENSITIVE = "abcdefghijklmnopqrstuvwxyz";
    public static final String ALPHA_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA_CASE_SENSITIVE = ALPHA_CASE_INSENSITIVE + ALPHA_UPPER;
    public static final String NUMERIC = "0123456789";
    public static final String ALPHA_NUM_CASE_INSENSITIVE = ALPHA_CASE_INSENSITIVE + NUMERIC;
    public static final String ALPHA_NUM_CASE_SENSITIVE = ALPHA_CASE_INSENSITIVE + ALPHA_UPPER + NUMERIC;

    private char beginning_char;
    private char ending_char;
    private int passwordLength;
    private long maxCombinations;

    private String charLimit;

    private char[] current;

    public BruteForceMode(int passwordLength, CharType charType) {
        this.passwordLength = passwordLength;
        init(charType);
    }

    private void init(CharType charType) {
        switch (charType) {
            case ALPHA_UPPER:
                initCracker(ALPHA_UPPER);
                break;
            case ALPHA_CASE_INSENSITIVE:
                initCracker(ALPHA_CASE_INSENSITIVE);
                break;
            case ALPHA_CASE_SENSITIVE:
                initCracker(ALPHA_CASE_SENSITIVE);
                break;
            case NUMERIC:
                initCracker(NUMERIC);
                break;
            case ALPHA_NUM_CASE_INSENSITIVE:
                initCracker(ALPHA_NUM_CASE_INSENSITIVE);
                break;
            case ALPHA_NUM_CASE_SENSITIVE:
                initCracker(ALPHA_NUM_CASE_SENSITIVE);
                break;
        }
    }

    private void initCracker(String charSequence) {

        charLimit = charSequence;

        current = new char[passwordLength];

        this.beginning_char = charSequence.charAt(0);
        this.ending_char = charSequence.charAt(charSequence.length() - 1);

        maxCombinations = (long) Math.pow(charSequence.length(), passwordLength);

        for (int i = 0; i < passwordLength; i++) {
            current[i] = beginning_char;
        }

    }

    @Override
    protected CrackResult run() throws CrackerException {
        for (long i = 0; i < maxCombinations; i++) {
            try {
                CrackResult crackResult = crackerType.attempt(getCurrent());
                if (crackResult.isSuccessful()) {
                    return crackResult;
                }
            } catch (IncorrectPasswordException e) {
                continue;
            } finally {
                generate();
            }
        }
        return new CrackResult(false, "Not Found");
    }

    private String getCurrent() {
        return new String(current);
    }

    private void generate() {
        for (int i = 0; i < passwordLength; i++) {
            if (current[i] == ending_char) {
                continue;
            }
            current[i] = charLimit.charAt(charLimit.indexOf(current[i]) + 1);
            if (i > 0)
                for (int z = 0; z < i; z++){
                    current[z] = beginning_char;
                }
            break;
        }
    }

}
