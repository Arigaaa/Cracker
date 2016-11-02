package com.shaznee.cracker.modes.dictionary;

import com.shaznee.cracker.core.CrackerImpl;
import com.shaznee.cracker.core.TargetCracker;
import com.shaznee.cracker.exceptions.CrackerException;
import com.shaznee.cracker.exceptions.IncorrectPasswordException;
import com.shaznee.cracker.core.model.CrackResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SHAZNEE on 07-Oct-16.
 */
public class DictionaryMode extends CrackerImpl {

    private List<String> wordList;

    public DictionaryMode(String dictionary, TargetCracker targetCracker) throws CrackerException {
        super(targetCracker);
        File dictionaryFile = new File(dictionary);
        if (!dictionaryFile.exists()) {
            throw new CrackerException("Dictionary file does not exixt");
        }
        init(dictionaryFile);
    }

    private void init(File dictionary) throws CrackerException {
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(dictionary));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
                ) {
            wordList = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                wordList.addAll(Arrays.asList(line.split("\\s*(\\s|=>|,)\\s*")));
            }
        }
        catch (IOException e) {
            throw new CrackerException("Could not load dictionary file. Please try a different file", e);
        }
    }

    @Override
    protected CrackResult run() throws CrackerException {
        System.out.println("Number of words :" + wordList.size());
        for (String word : wordList) {
            try {
                CrackResult crackResult = targetCracker.attempt(word);
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
