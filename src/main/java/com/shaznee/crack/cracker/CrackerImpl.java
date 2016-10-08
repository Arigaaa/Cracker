package com.shaznee.crack.cracker;

import java.io.File;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public abstract class CrackerImpl implements Cracker {

    private File file;

    protected CrackerImpl(File file) {
        this.file = file;
    }
}
