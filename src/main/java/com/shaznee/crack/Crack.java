package com.shaznee.crack;

import com.shaznee.crack.constants.ErrorConstants;
import com.shaznee.crack.cracker.CharType;
import com.shaznee.crack.exceptions.CrackerException;
import com.shaznee.crack.model.CrackResult;
import com.shaznee.crack.cracker.Cracker;

import java.io.File;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class Crack {

    private static final String MODE_BRUTE_FORCE = "B";
    private static final String MODE_DICTIONARY = "D";

    public static final String CHAR_TYPE_ALPHA = "A";
    public static final String CHAR_TYPE_NUMERIC = "N";
    public static final String CHAR_TYPE_ALPHA_NUMERIC = "AN";

    /***
     * arg 0 : fileName
     *
     * arg 1 : mode = D/B
     *                D - Dictionary mode
     *                B - BruteForce mode
     *
     * arg 2 : dictionary text if dictionary mode
     * arg 2 : passoword length if bruteForce mode
     *
     * arg 3 : password charactor type
     *                A  - Alpha
     *                N  - Numeric
     *                AN - Alphanumeric
     */
    public static void main(String... args) {
        if (args.length > 2) {
            try {
                Cracker cracker = createCracker(args);
                CrackResult crackResult = cracker.crack();
                if (crackResult.isSuccessful()) {
                    System.out.println("Password \t: " + crackResult.getPassword());
                    System.out.println("Time Elapsed \t: " + crackResult.getTimeTaken());
                } else {
                    System.out.println("Could not crack password");
                }
            } catch (CrackerException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println(ErrorConstants.CREATE_CRACKER_USAGE);
        }
    }

    public static Cracker createCracker(String... args) throws CrackerException {

        String fileName = args[0];
        String mode = args[1];

        switch (mode) {
            case MODE_BRUTE_FORCE:
                if (args.length == 4) {
                    int passwordLength = Integer.parseInt(args[2]);
                    String charTypeArg = args[3];
                    CharType charType = null;
                    switch (charTypeArg) {
                        case CHAR_TYPE_ALPHA:
                            charType = CharType.APHA;
                            break;
                        case CHAR_TYPE_NUMERIC:
                            charType = CharType.NUMBERIC;
                            break;
                        case CHAR_TYPE_ALPHA_NUMERIC:
                            charType = CharType.ALPHA_NUMERIC;
                            break;
                    }
                    if (fileName.length() > 0 && passwordLength > 0 && charType != null) {
                        return Cracker.BRUTE_FORCE_MODE(new File(fileName), passwordLength, charType);
                    } else {
                        throw new CrackerException(ErrorConstants.CREATE_CRACKER_USAGE);
                    }
                } else {
                    throw new CrackerException(ErrorConstants.CREATE_CRACKER_USAGE);
                }

            case MODE_DICTIONARY:
                String dictionary = args[2];
                if (dictionary.length() > 0 && fileName.length() > 0) {
                    return Cracker.DICTIONARY_MODE(new File(fileName), new File(dictionary));
                }
            default:
                throw new CrackerException(ErrorConstants.CREATE_CRACKER_USAGE);
        }
    }
}
