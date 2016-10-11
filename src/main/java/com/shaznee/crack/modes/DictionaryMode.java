package com.shaznee.crack.modes;

import com.shaznee.crack.cracker.CrackerImpl;
import com.shaznee.crack.exceptions.CrackerException;
import com.shaznee.crack.exceptions.IncorrectPasswordException;
import com.shaznee.crack.model.CrackResult;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class DictionaryMode extends CrackerImpl {

    private List<String> wordList;

    public DictionaryMode(String dictionary) throws CrackerException {
        init(new File(dictionary));
    }

    private void init(File dictionary) throws CrackerException {
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(dictionary));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
                ) {
            wordList = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                wordList.addAll(Arrays.asList(line.split(" ")));
            }
        }
        catch (IOException e) {
            throw new CrackerException("Could not load dictionary file. Please try a different file", e);
        }
    }

    @Override
    protected CrackResult run() throws CrackerException {
        for (String word : wordList) {
            try {
                CrackResult crackResult = crackerType.attempt(word);
                if (crackResult.isSuccessful()) {
                    return crackResult;
                }
            } catch (IncorrectPasswordException e) {
                continue;
            }
        }
        return new CrackResult(false, "Not Found");
    }
}
