package KNN;

//package KNN;
//
//import org.apache.commons.math3.linear.MatrixUtils;
//import org.apache.commons.math3.linear.RealMatrix;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Scanner;
//
///*
// * This is a problem 1 :
// *  The project was part of my practical projects.
// *  I desided to continue working on it since I was not provide with 
// * 	a solid structrure of how I can approach the problem code wise.
// * 
// */
//
//// This class is responsible for making classification using k-nearest neighbors.
//public class Main {
//    
//    // Constants for feature indexes
//    private static final int PREGNANCIES_INDEX = 0;
//    private static final int GLUCOSE_INDEX = 1;
//    private static final int BLOOD_PRESSURE_INDEX = 2;
//    private static final int SKIN_THICKNESS_INDEX = 3;
//    private static final int INSULIN_INDEX = 4;
//    private static final int BMI_INDEX = 5;
//    private static final int DIABETES_PEDIA_INDEX = 6;
//    private static final int AGE_INDEX = 7;
//
//    public static void main(String[] args) {
//        // Create a Scanner object to read user input
//        Scanner scanner = new Scanner(System.in);
//
//        // Step 1: Prompt the user for the number of neighbors (k)
//        System.out.print("Enter the number of neighbors (k) for KNN classification: ");
//        int k = scanner.nextInt(); // Read the integer value for k
//        
//        // Step 2: Read the diabetes data from the file using DiabetesDataReader
//        List<DiabeteClass> diabetesData = DiabetesDataReader.readfile();
//        
//        // Step 3: Extract features and outcomes
//        double[][] x_array = new double[diabetesData.size()][8];  // 8 features
//        ArrayList<String> y_array = new ArrayList<>();            // Target variable (Outcome as a string)
//        
//        // Step 4: Populate the arrays with feature and outcome data
//        for (int i = 0; i < diabetesData.size(); i++) {
//            DiabeteClass diabete = diabetesData.get(i);
//            
//            // Features
//            x_array[i][PREGNANCIES_INDEX] = diabete.pregnancies;
//            x_array[i][GLUCOSE_INDEX] = diabete.glucose;
//            x_array[i][BLOOD_PRESSURE_INDEX] = diabete.bloodpressure;
//            x_array[i][SKIN_THICKNESS_INDEX] = diabete.skinthickness;
//            x_array[i][INSULIN_INDEX] = diabete.insulin;
//            x_array[i][BMI_INDEX] = diabete.BMI;
//            x_array[i][DIABETES_PEDIA_INDEX] = diabete.diabetesPedigreeFunction;
//            x_array[i][AGE_INDEX] = diabete.age;
//            
//            // Outcome (Target variable) as a String
//            y_array.add(String.valueOf(diabete.outcome));
//        }
//
//        // Step 5: Convert the feature array (x_array) to a RealMatrix for use in KNN
//        RealMatrix xMatrix = MatrixUtils.createRealMatrix(x_array);
//        
//        // Step 6: Create an instance of KNearestNeighbour with the feature matrix and labels (as ArrayList<String>)
//        KNearestNeighbour knn = new KNearestNeighbour(xMatrix, y_array);
//        
//        // Step 7: Handle commands (e.g., make predictions)
//        classifyTestSamples(knn, k); // Pass k to the classification method
//        
//        // Close the scanner
//        scanner.close();
//    }
//
//    // Handles the classification test instances using the K-nearest neighbors algorithm.
//    static void classifyTestSamples(KNearestNeighbour knn, int k) {
//        // Sample data of 10 samples self generated for testing the  code.
//        double[][] testSamplesArray = {
//            {2, 85, 66, 29, 0, 26.6, 0.4, 31},  //  1
//            {1, 120, 80, 35, 0, 28.0, 0.5, 45},  // 2
//            {3, 90, 70, 30, 50, 25.0, 0.3, 22},  //  3
//            {0, 100, 60, 20, 0, 30.0, 0.2, 29},  //  4
//            {5, 140, 90, 40, 80, 32.5, 0.7, 50},  //  5
//            {4, 150, 85, 38, 70, 27.5, 0.6, 39},  //  6
//            {2, 95, 75, 28, 0, 24.0, 0.1, 33},    //  7
//            {6, 110, 65, 30, 20, 26.0, 0.4, 41},  //  8
//            {2, 130, 72, 32, 0, 29.0, 0.3, 27},   //  9
//            {3, 135, 77, 34, 50, 31.0, 0.2, 34}   //  10
//        };
//        
//        int count = 0;
//        // Iterate through each test instance to classify it
//        for (double[] sampleTest : testSamplesArray) {
//            // Create a RealMatrix for the current test instance
//            RealMatrix sampleTestMatrix = MatrixUtils.createRowRealMatrix(sampleTest);
//            
//            // Classify the test instance with the user-defined value of k
//            HashMap<String, Object> results = new HashMap<>();
//            String prediction = knn.classify(sampleTestMatrix, k, results);
//            
//            // Display predicted class and additional details
//            System.out.println("Predicted class: " + prediction);
//            System.out.println("--- Detailed Results ---");
//            System.out.println("Nearest Neighbors: " + results.get("nearestNeighbors"));
//            System.out.println("Distances: " + Arrays.toString((double[]) results.get("distances")));
//            System.out.println();
//            
//            // the sample count :
//            count++;
//            
//            if (prediction.equals("0")) { // if prediction is "0"
//                System.out.println("Sample test " + count + " is predicted to NOT have diabetes.");
//            } else {
//                System.out.println("Sample test " + count + " is predicted to have diabetes.");
//            }
//        }
//    }
//
//    static void displaySummary(RealMatrix testInstance) {
//        System.out.println("\nTest Instance Summary:");
//        System.out.printf("Pregnancies: %.1f%n", testInstance.getEntry(0, PREGNANCIES_INDEX));
//        System.out.printf("Glucose: %.1f%n", testInstance.getEntry(0, GLUCOSE_INDEX));
//        System.out.printf("Blood Pressure: %.1f%n", testInstance.getEntry(0, BLOOD_PRESSURE_INDEX));
//        System.out.printf("Skin Thickness: %.1f%n", testInstance.getEntry(0, SKIN_THICKNESS_INDEX));
//        System.out.printf("Insulin: %.1f%n", testInstance.getEntry(0, INSULIN_INDEX));
//        System.out.printf("BMI: %.1f%n", testInstance.getEntry(0, BMI_INDEX));
//        System.out.printf("Diabetes Pedigree Function: %.3f%n", testInstance.getEntry(0, DIABETES_PEDIA_INDEX));
//        System.out.printf("Age: %.1f%n", testInstance.getEntry(0, AGE_INDEX));
//    }
//}
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;
//import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int PREGNANCIES_INDEX = 0;
    private static final int GLUCOSE_INDEX = 1;
    private static final int BLOOD_PRESSURE_INDEX = 2;
    private static final int SKIN_THICKNESS_INDEX = 3;
    private static final int INSULIN_INDEX = 4;
    private static final int BMI_INDEX = 5;
    private static final int DIABETES_PEDIA_INDEX = 6;
    private static final int AGE_INDEX = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Read the diabetes data from the file
        List<DiabeteClass> diabetesData = DiabetesDataReader.readfile();

        // Step 2: Split the data into training, validation, and test sets
        double[][] features = new double[diabetesData.size()][8];
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < diabetesData.size(); i++) {
            DiabeteClass diabete = diabetesData.get(i);
            features[i][PREGNANCIES_INDEX] = diabete.pregnancies;
            features[i][GLUCOSE_INDEX] = diabete.glucose;
            features[i][BLOOD_PRESSURE_INDEX] = diabete.bloodpressure;
            features[i][SKIN_THICKNESS_INDEX] = diabete.skinthickness;
            features[i][INSULIN_INDEX] = diabete.insulin;
            features[i][BMI_INDEX] = diabete.BMI;
            features[i][DIABETES_PEDIA_INDEX] = diabete.diabetesPedigreeFunction;
            features[i][AGE_INDEX] = diabete.age;
            labels.add(String.valueOf(diabete.outcome));
        }

        // Convert to RealMatrix
        RealMatrix fullMatrix = MatrixUtils.createRealMatrix(features);

        // Split the dataset into 70% training, 15% validation, and 15% testing
        int trainSize = (int) (0.7 * fullMatrix.getRowDimension());
        int valSize = (int) (0.15 * fullMatrix.getRowDimension());
        int testSize = fullMatrix.getRowDimension() - trainSize - valSize;

        RealMatrix trainFeatures = fullMatrix.getSubMatrix(0, trainSize - 1, 0, 7);
        RealMatrix valFeatures = fullMatrix.getSubMatrix(trainSize, trainSize + valSize - 1, 0, 7);
        RealMatrix testFeatures = fullMatrix.getSubMatrix(trainSize + valSize, fullMatrix.getRowDimension() - 1, 0, 7);

        List<String> trainLabels = labels.subList(0, trainSize);
        List<String> valLabels = labels.subList(trainSize, trainSize + valSize);
        List<String> testLabels = labels.subList(trainSize + valSize, labels.size());

        // Step 3: Create the KNN model with the training data
        KNearestNeighbour knn = new KNearestNeighbour(trainFeatures, trainLabels);

        // Step 4: Tune hyperparameter k using validation data
        System.out.println("Enter the maximum value of k to test: ");
        int maxK = scanner.nextInt();
        int optimalK = findOptimalK(knn, valFeatures, valLabels, maxK);
        System.out.println("Optimal k value found: " + optimalK);

        // Step 5: Evaluate the model on the test data
        evaluateModel(knn, testFeatures, testLabels, optimalK);

        scanner.close();
    }

    // Function to find the optimal value of k using validation data
    private static int findOptimalK(KNearestNeighbour knn, RealMatrix valFeatures, List<String> valLabels, int maxK) {
        int bestK = 1;
        double bestAccuracy = 0.0;

        for (int k = 1; k <= maxK; k++) {
            int correct = 0;
            for (int i = 0; i < valFeatures.getRowDimension(); i++) {
                RealMatrix sample = valFeatures.getRowMatrix(i);
                String actual = valLabels.get(i);
                String predicted = knn.classify(sample, k, new HashMap<>());
                if (predicted.equals(actual)) {
                    correct++;
                }
            }
            double accuracy = (double) correct / valFeatures.getRowDimension();
            System.out.printf("Accuracy for k=%d: %.2f%%%n", k, accuracy * 100);
            if (accuracy > bestAccuracy) {
                bestAccuracy = accuracy;
                bestK = k;
            }
        }
        return bestK;
    }

    // Function to evaluate the model on the test set
    private static void evaluateModel(KNearestNeighbour knn, RealMatrix testFeatures, List<String> testLabels, int k) {
        int correct = 0;

        for (int i = 0; i < testFeatures.getRowDimension(); i++) {
            RealMatrix sample = testFeatures.getRowMatrix(i);
            String actual = testLabels.get(i);
            String predicted = knn.classify(sample, k, new HashMap<>());

            System.out.printf("Test Sample %d - Predicted: %s, Actual: %s%n", i + 1, predicted, actual);

            if (predicted.equals(actual)) {
                correct++;
            }
        }

        double accuracy = (double) correct / testFeatures.getRowDimension();
        System.out.printf("Test Accuracy: %.2f%%%n", accuracy * 100);
    }
}

