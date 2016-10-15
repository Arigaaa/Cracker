package com.shaznee.crack;

import com.shaznee.crack.cracker.Cracker;
import com.shaznee.crack.cracker.types.PDFCracker;
import com.shaznee.crack.exceptions.CrackerException;
import com.shaznee.crack.model.CrackResult;

import static com.shaznee.crack.constants.CrackerConstants.CREATE_CRACKER_USAGE;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class Crack {

    /***
     * arg 0 : Target
     *
     * arg 1 : mode = D/B
     *                -D - Dictionary mode
     *                -B - BruteForce mode
     *
     * arg 2 : dictionary text if dictionary mode
     * arg 2 : passoword length if bruteForce mode
     *
     * arg 3 : password charactor type
     *                -A   - Alpha Case Insensitive
     *                -N   - Numeric
     *                -AU  - Alpha Upper Case
     *                -AC  - Alpha Case Sensitive
     *                -AN  - Alphanumeric Case Insensitive
     *                -ANC - Alphanumeric Case Sensitive
     */

    public static void main(String... args) {
        if (args.length > 2) {
            try {
                Cracker cracker = Cracker.createCracker(args);
                cracker.setCrackerType(new PDFCracker(args[0]));
                CrackResult crackResult = cracker.crack();
                if (crackResult.isSuccessful()) {
                    System.out.println("Password \t\t: " + crackResult.getPassword());
                    System.out.println("Time Elapsed \t: " + crackResult.getTimeTaken(CrackResult.TIME_SECONDS));
                } else {
                    System.out.println("Password \t\t: " + crackResult.getPassword());
                    System.out.println("Time Elapsed \t: " + crackResult.getTimeTaken(CrackResult.TIME_SECONDS));
                }
            } catch (CrackerException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println(CREATE_CRACKER_USAGE);
        }

    }

}
