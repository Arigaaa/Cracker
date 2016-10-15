package com.shaznee.crack.cracker.types;

import com.shaznee.crack.cracker.CrackerType;
import com.shaznee.crack.exceptions.CrackerException;
import com.shaznee.crack.exceptions.IncorrectPasswordException;
import com.shaznee.crack.model.CrackResult;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * Created by SHAZNEE on 10-Oct-16.
 */
public class PDFCracker implements CrackerType {

    private File file;

    public PDFCracker(String target) {
        file = new File(target);
        System.out.println("Cracking : " + file.getAbsolutePath());
    }

    @Override
    public CrackResult attempt(String password) throws IncorrectPasswordException, CrackerException {
        PDDocument document = null;
        try {

            System.out.printf("\rAttempting with: %3s", password);
            document = PDDocument.load(file, password);

            int numberOfPages = document.getNumberOfPages();
            System.out.println("Pages :" + numberOfPages);

            if (numberOfPages > 0) {
                System.out.printf("\r Success");
                return new CrackResult(true, password);
            } else {
                return new CrackResult(false, "Not Found");
            }
        } catch (IOException e) {
            throw new IncorrectPasswordException("Password incorrect", e);
        } finally {
            if(document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    throw new CrackerException("Failed to close document", e);
                }
            }
        }
    }
}
