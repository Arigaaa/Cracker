package com.shaznee.cracker.core.extensions;

import com.shaznee.cracker.core.CrackerType;
import com.shaznee.cracker.exceptions.CrackerException;
import com.shaznee.cracker.exceptions.IncorrectPasswordException;
import com.shaznee.cracker.core.model.CrackResult;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

import static com.shaznee.cracker.constants.CrackerConstants.CLEARLINE_FMT;

/**
 * Created by SHAZNEE on 10-Oct-16.
 */
public class PDFCracker implements CrackerType {

    private File file;

    public PDFCracker(String target) throws CrackerException {
        file = new File(target);
        if (!file.exists()) {
            throw new CrackerException("Target file does not exist");
        }
        System.out.println("Cracking : " + file.getAbsolutePath());
    }

    @Override
    public CrackResult attempt(String password) throws IncorrectPasswordException, CrackerException {

        System.out.printf("\rAttempting with : %3s", password);
        try (
                PDDocument document = PDDocument.load(file, password);
                ) {

            int numberOfPages = document.getNumberOfPages();
            if (numberOfPages > 0) {
                System.out.printf(CLEARLINE_FMT, "");
                try {
                    document.close();
                } catch (IOException e) {
                    throw new CrackerException("Could not close file", e);
                } finally {
                    return new CrackResult(true, password);
                }
            } else {
                return new CrackResult(false, "Not Found");
            }
        } catch (IOException e) {
            throw new IncorrectPasswordException("Password incorrect", e);
        } finally {
            System.out.printf(CLEARLINE_FMT, "");
        }
    }
}
