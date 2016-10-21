package com.shaznee.cracker.modes.bruteforce;

import com.shaznee.cracker.core.CrackerImpl;
import com.shaznee.cracker.exceptions.CrackerException;
import com.shaznee.cracker.exceptions.IncorrectPasswordException;
import com.shaznee.cracker.core.model.CrackResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class BruteForceMode extends CrackerImpl {

    private static final String ALPHA_CASE_INSENSITIVE = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHA_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA_CASE_SENSITIVE = ALPHA_CASE_INSENSITIVE + ALPHA_UPPER;
    private static final String NUMERIC = "0123456789";
    private static final String ALPHA_NUM_CASE_INSENSITIVE = ALPHA_CASE_INSENSITIVE + NUMERIC;
    private static final String ALPHA_NUM_CASE_SENSITIVE = ALPHA_CASE_INSENSITIVE + ALPHA_UPPER + NUMERIC;

    private char beginning_char;
    private char ending_char;
    private char[] currentPassword;

    private String charSequence;

    private int passwordLength;
    private long maxCombinations;

    private boolean isAlphaNumericCaseSensitive = false;
    private Set<String> generatedSet;

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
                isAlphaNumericCaseSensitive = true;
                initCracker(ALPHA_NUM_CASE_SENSITIVE);
                break;
        }
    }

    private void initCracker(String charSequence) {

        this.charSequence = charSequence;
        beginning_char = this.charSequence.charAt(0);
        ending_char = this.charSequence.charAt(charSequence.length() - 1);
        currentPassword = new char[passwordLength];

        maxCombinations = (long) Math.pow(this.charSequence.length(), passwordLength);

        if (isAlphaNumericCaseSensitive) {
            generatedSet = new HashSet<>(this.charSequence.length() * passwordLength);
        }

        for (int i = 0; i < passwordLength; i++) {
            currentPassword[i] = beginning_char;
        }
    }

    @Override
    protected CrackResult run() throws CrackerException {
        for (long i = 0; i < maxCombinations; i++) {
            try {
                String currentPassword = getCurrentPassword();
                if (isAlphaNumericCaseSensitive) {
                    if (generatedSet.contains(currentPassword)) {
                        continue;
                    } else {
                        List<String> stringsWithCase = new ArrayList<>(3);
                        stringsWithCase.add(currentPassword);
                        stringsWithCase.add(currentPassword.toLowerCase());
                        stringsWithCase.add(currentPassword.toUpperCase());

                        for (String password : stringsWithCase) {
                            try {
                                CrackResult crackResult = crackerType.attempt(password);
                                if (crackResult.isSuccessful()) {
                                    return crackResult;
                                }
                            } catch (IncorrectPasswordException e) {
                                continue;
                            } finally {
                                generatedSet.add(password);
                            }
                        }
                    }
                } else {
                    CrackResult crackResult = crackerType.attempt(currentPassword);
                    if (crackResult.isSuccessful()) {
                        return crackResult;
                    }
                }
            } catch (IncorrectPasswordException e) {
                continue;
            } finally {
                generateDescending();
            }
        }
        return new CrackResult(false, "Not Found");
    }

    private String getCurrentPassword() {
        return new String(currentPassword);
    }

    private void generateAscending() {
        for (int i = 0; i < passwordLength; i++) {
            if (currentPassword[i] == ending_char) {
                continue;
            }
            currentPassword[i] = charSequence.charAt(charSequence.indexOf(currentPassword[i]) + 1);
            for (int z = 0; z < i; z++) {
                currentPassword[z] = beginning_char;
            }
            break;
        }
    }

    private void generateDescending() {
        for (int i = passwordLength - 1; i > 0; i--) {
            if (currentPassword[i] == ending_char) {
                currentPassword[i] = beginning_char;
                continue;
            }
            currentPassword[i] = charSequence.charAt(charSequence.indexOf(currentPassword[i]) + 1);
            if (i == 0) {
                currentPassword[i] = beginning_char;
            }
            break;
        }
    }
}
