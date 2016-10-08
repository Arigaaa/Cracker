package com.shaznee.crack;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class Brute {

    char[] canUse = {
            '0','1','2','3','4','5','6','7','8','9',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
    };

    int maxlen = 8;

    public static void main (String[] args) {

        Brute b = new Brute();
    }


    public Brute() {
        int k = 0;

        while (k < canUse.length) {
            nextString(new Character(canUse[k]).toString());
            k++;
        }

    }


    private void nextString(String s) {

        int i = 0;

        System.out.println(s);

        while (i< canUse.length) {
            System.out.println(s + new Character(canUse[i]).toString());

            if (new String(s + new Character(canUse[i]).toString()).length() <= maxlen) {nextString(s + new Character(canUse[i]).toString());}
            i++;
        }

    }
}
