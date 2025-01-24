package KNN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class will be responsible for reading the diabetes data from a .csv file.
 */
public class DiabetesDataReader {

    /**
     * Method for reading the diabetes data from the .csv file.
     * @return List of DiabeteClass objects representing the dataset.
     */
    public static List<DiabeteClass> readfile() {
        // List to store diabetes data instances
        List<DiabeteClass> diabetesList = new ArrayList<>();
        
        // File path to the diabetes data
        String filepath = "data/diabetes.csv";
        File file = new File(filepath); // File handler
        
        try (Scanner sc = new Scanner(file)) {
            // Read and print the headers of the file
            if (sc.hasNextLine()) {
                String headers = sc.nextLine();
                System.out.println("The headers of the file: " + headers);
            }
            
            // Reading the rest of the file
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] values = line.split(","); // Split the line by commas
                
                // Parse each value into appropriate types (as per the DiabeteClass fields)
                int pregnancies = Integer.parseInt(values[0]);
                int glucose = Integer.parseInt(values[1]);
                int bloodPressure = Integer.parseInt(values[2]);
                int skinThickness = Integer.parseInt(values[3]);
                int insulin = Integer.parseInt(values[4]);
                double BMI = Double.parseDouble(values[5]);
                double diabetesPedigreeFunction = Double.parseDouble(values[6]);
                int age = Integer.parseInt(values[7]);
                int outcome = Integer.parseInt(values[8]);
                
                // Create a DiabeteClass object and add it to the list
                DiabeteClass diabete = new DiabeteClass(pregnancies, glucose, bloodPressure, 
                                                        skinThickness, insulin, BMI, 
                                                        diabetesPedigreeFunction, age, outcome);
                diabetesList.add(diabete);
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        return diabetesList; // Return the list of diabetes data objects
    }
}
