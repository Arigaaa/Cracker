package com.shaznee.cracker.core;

import com.shaznee.cracker.modes.bruteforce.BruteForceMode;
import com.shaznee.cracker.modes.dictionary.DictionaryMode;
import com.shaznee.cracker.modes.bruteforce.CharType;
import com.shaznee.cracker.exceptions.CrackerException;
import com.shaznee.cracker.core.model.CrackResult;

import static com.shaznee.cracker.constants.CrackerConstants.CREATE_CRACKER_USAGE;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public interface Cracker {

    String MODE_BRUTE_FORCE = "-B";
    String MODE_DICTIONARY = "-D";

    String ORDER_ASCENDING = "-OA";
    String ORDER_DESCENDING = "-OD";

    String CHAR_TYPE_ALPHA_CASE_INSENSITIVE = "-A";
    String CHAR_TYPE_ALPHA_UPPER = "-AU";
    String CHAR_TYPE_ALPHA_ALPHA_CASE_SENSITIVE = "-AC";
    String CHAR_TYPE_NUMERIC = "-N";
    String CHAR_TYPE_ALPHA_NUM_CASE_INSENSITIVE = "-AN";
    String CHAR_TYPE_ALPHA_NUM_CASE_SENSITIVE = "-ANC";

    void setCrackerType(CrackerType crackerType);

    CrackResult crack() throws CrackerException;

    static Cracker createCracker(String... args) throws CrackerException {

        String mode = args[1];

        switch (mode) {
            case MODE_BRUTE_FORCE:

                if (args.length < 5) {
                    throw new CrackerException(CREATE_CRACKER_USAGE);
                }

                int passwordLength = Integer.parseInt(args[2]);
                String charTypeArg = args[3];
                CharType charType;

                switch (charTypeArg) {
                    case CHAR_TYPE_ALPHA_UPPER:
                        charType = CharType.ALPHA_UPPER;
                        break;
                    case CHAR_TYPE_ALPHA_CASE_INSENSITIVE:
                        charType = CharType.ALPHA_CASE_INSENSITIVE;
                        break;
                    case CHAR_TYPE_ALPHA_ALPHA_CASE_SENSITIVE:
                        charType = CharType.ALPHA_CASE_SENSITIVE;
                        break;
                    case CHAR_TYPE_NUMERIC:
                        charType = CharType.NUMERIC;
                        break;
                    case CHAR_TYPE_ALPHA_NUM_CASE_INSENSITIVE:
                        charType = CharType.ALPHA_NUM_CASE_INSENSITIVE;
                        break;
                    case CHAR_TYPE_ALPHA_NUM_CASE_SENSITIVE:
                        charType = CharType.ALPHA_NUM_CASE_SENSITIVE;
                        break;
                    default:
                        throw new CrackerException(CREATE_CRACKER_USAGE);
                }

                if (passwordLength > 0 && charType != null) {
                    return new BruteForceMode(passwordLength, charType);
                } else {
                    throw new CrackerException(CREATE_CRACKER_USAGE);
                }

            case MODE_DICTIONARY:

                if (args.length > 3) {
                    throw new CrackerException(CREATE_CRACKER_USAGE);
                }

                String dictionary = args[2];
                if (dictionary.length() > 0) {
                    return new DictionaryMode(dictionary);
                }

            default:
                throw new CrackerException(CREATE_CRACKER_USAGE);
        }
    }

}
