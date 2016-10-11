package com.shaznee.crack.cracker;

import com.shaznee.crack.constants.ErrorConstants;
import com.shaznee.crack.exceptions.CrackerException;
import com.shaznee.crack.model.CrackResult;
import com.shaznee.crack.cracker.modes.BruteForceMode;
import com.shaznee.crack.cracker.modes.DictionaryMode;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public interface Cracker {

    String MODE_BRUTE_FORCE = "B";
    String MODE_DICTIONARY = "D";

    String CHAR_TYPE_ALPHA = "A";
    String CHAR_TYPE_NUMERIC = "N";
    String CHAR_TYPE_ALPHA_NUMERIC = "AN";

    void setCrackerType(CrackerType crackerType);

    CrackResult crack() throws CrackerException;

    static Cracker createCracker(String... args) throws CrackerException {

        String mode = args[1];

        switch (mode) {
            case MODE_BRUTE_FORCE:

                if (args.length < 4) {
                    throw new CrackerException(ErrorConstants.CREATE_CRACKER_USAGE);
                }

                int passwordLength = Integer.parseInt(args[2]);
                String charTypeArg = args[3];
                CharType charType;

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
                    default:
                        throw new CrackerException(ErrorConstants.CREATE_CRACKER_USAGE);
                }

                if (passwordLength > 0 && charType != null) {
                    return new BruteForceMode(passwordLength, charType);
                } else {
                    throw new CrackerException(ErrorConstants.CREATE_CRACKER_USAGE);
                }

            case MODE_DICTIONARY:

                if (args.length > 3) {
                    throw new CrackerException(ErrorConstants.CREATE_CRACKER_USAGE);
                }

                String dictionary = args[2];
                if (dictionary.length() > 0) {
                    return new DictionaryMode(dictionary);
                }

            default:
                throw new CrackerException(ErrorConstants.CREATE_CRACKER_USAGE);
        }
    }

}
