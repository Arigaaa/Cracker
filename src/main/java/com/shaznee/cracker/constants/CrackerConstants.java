package com.shaznee.cracker.constants;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class CrackerConstants {

    public static final String CREATE_CRACKER_USAGE = "Supported Modes \n" +
            "\t -D -> Dictionary Mode \n" +
            "\t -B -> Brute Force Mode\n\n" +
            "Dictionary Mode Usage: \n" +
            "\t Cracker-<version> <target> -D <{Dictionary File}.txt>\n\n" +
            "Brute Force Mode Usage : \n " +
            "\t Cracker-<version> <target> -B <password length> <Character Type> <Generation Order> \n\n" +
            "Supported Character Types (Optional) \n" +
            "\t -A   ->  Alpha Case Insensitive (Default)\n" +
            "\t -N   ->  Numeric\n" +
            "\t -AU  ->  Alpha Upper Case\n" +
            "\t -AC  ->  Alpha Case Sensitive\n" +
            "\t -AN  ->  Aplhanumeric Case Insensitive\n" +
            "\t -ANC ->  Aplhanumeric Case Sensitive\n\n" +
            "Generation order (Optional) \n" +
            "\t -OD  ->  Descending order (Default)\n" +
            "\t -OA  ->  Ascending order";

    public static final String CLEARLINE_FMT = "\r%30s\r";

}
