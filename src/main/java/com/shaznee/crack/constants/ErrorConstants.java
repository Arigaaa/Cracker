package com.shaznee.crack.constants;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class ErrorConstants {

    public static final String CREATE_CRACKER_USAGE = "Supported Modes \n" +
            "\t -D -> Dictionary Mode \n" +
            "\t -B -> Brute Force Mode\n\n" +
            "Dictionary Mode Usage: \n" +
            "\t Crack <target> -D <{Dictionary File}.txt>\n\n" +
            "Brute Force Mode Usage : \n " +
            "\t Crack <target> -B <password length> <Character Type> \n\n" +
            "Supported Character Types \n" +
            "\t -A   ->  Alpha Case Insensitive\n" +
            "\t -N   ->  Numeric\n" +
            "\t -AU  ->  Alpha Upper Case\n" +
            "\t -AC  ->  Alpha Case Sensitive\n" +
            "\t -AN  ->  Aplhanumeric Case Insensitive\n" +
            "\t -ANC ->  Aplhanumeric Case Sensitive\n";

}
