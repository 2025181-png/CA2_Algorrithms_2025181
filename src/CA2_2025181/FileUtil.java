/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA2_2025181;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Khongorzul
 */

// Utility class for reading applicant names from Applicants_Form.txt
public class FileUtil {

    // Reads names line by line from the provided file.
    public static List<String> readNamesFromFile(String filename) {
        List<String> names = new ArrayList<>();

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("ERROR: File not found: " + filename);
            return names;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    names.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Unable to read file: " + e.getMessage());
        }

        if (names.isEmpty()) {
            System.out.println("WARNING: No names were loaded from the file.");
        } else {
            System.out.println("File read successfully. Loaded " + names.size() + " names.");
        }

        return names;
    }
}