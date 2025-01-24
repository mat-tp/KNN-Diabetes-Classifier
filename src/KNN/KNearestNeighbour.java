package KNN;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import java.util.*;

public class KNearestNeighbour {

    private RealMatrix trainingData;
    private List<String> labels;

    public KNearestNeighbour(RealMatrix trainingData, List<String> labels) {
        this.trainingData = trainingData;
        this.labels = labels;
    }

    public String classify(RealMatrix testInstance, int k, HashMap<String, Object> result) {
        // Step 1: Calculate the distances from testInstance to each training instance
        RealMatrix distances = calculateDistances(testInstance);
        
        // Step 2: Find the nearest neighbors based on the distances
        ArrayList<Integer> nearestNeighbors = findNearestNeighbors(distances, k);
        
        // Step 3: Retrieve the corresponding labels for the nearest neighbors
        String prediction = getMajorityLabel(nearestNeighbors);

        // Store nearest neighbors and distances in the result map
        result.put("nearestNeighbors", nearestNeighbors);
        result.put("distances", getDistancesArray(distances, nearestNeighbors));
        
        // Display the results
        displayResults(testInstance, nearestNeighbors, distances);
        
        return prediction;
    }

    private RealMatrix calculateDistances(RealMatrix testInstance) {
        // The test instance should be subtracted from each row of the training data.
        int numRows = trainingData.getRowDimension();
        double[][] distanceMatrix = new double[numRows][1]; // We will store the squared distances here
        
        for (int i = 0; i < numRows; i++) {
            // Subtract each row of training data from the test instance
            RealMatrix diff = trainingData.getRowMatrix(i).subtract(testInstance);
            
            // Square the differences and sum them (Euclidean distance)
            double squaredSum = 0;
            for (int j = 0; j < diff.getColumnDimension(); j++) {
                squaredSum += Math.pow(diff.getEntry(0, j), 2);
            }
            
            // Store the squared distance in the matrix
            distanceMatrix[i][0] = Math.sqrt(squaredSum); // Euclidean distance (square root of squared sum)
        }
        
        return MatrixUtils.createRealMatrix(distanceMatrix); // Return the matrix of distances
    }

    private ArrayList<Integer> findNearestNeighbors(RealMatrix distances, int k) {
        ArrayList<Integer> indices = new ArrayList<>();
        double[] distanceArray = distances.getColumn(0);
        
        for (int i = 0; i < distanceArray.length; i++) {
            indices.add(i);
        }
        
        indices.sort(Comparator.comparingDouble(i -> distanceArray[i]));
        return new ArrayList<>(indices.subList(0, k));
    }

    private String getMajorityLabel(ArrayList<Integer> nearestNeighbors) {
        Map<String, Integer> labelCount = new HashMap<>();
        
        for (int index : nearestNeighbors) {
            String label = labels.get(index);
            labelCount.put(label, labelCount.getOrDefault(label, 0) + 1);
        }
        
        return Collections.max(labelCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    // New method to get distances of nearest neighbors
    private double[] getDistancesArray(RealMatrix distances, ArrayList<Integer> nearestNeighbors) {
        double[] nearestDistances = new double[nearestNeighbors.size()];
        for (int i = 0; i < nearestNeighbors.size(); i++) {
            nearestDistances[i] = distances.getEntry(nearestNeighbors.get(i), 0);
        }
        return nearestDistances;
    }

    // New method to display results
    private void displayResults(RealMatrix testInstance, ArrayList<Integer> nearestNeighbors, RealMatrix distances) {
        // Display the test instance details
        System.out.println("\nTest Instance:");
        for (int i = 0; i < testInstance.getColumnDimension(); i++) {
            System.out.printf("%s: %.1f\n", getFeatureName(i), testInstance.getEntry(0, i));
        }

        // Display the k-nearest neighbors and distances
        System.out.println("\n--- Detailed Results ---");
        System.out.print("Nearest Neighbors: " + nearestNeighbors + "\n");

        // Fetch distances for the nearest neighbors
        System.out.print("Distances: ");
        for (int i = 0; i < nearestNeighbors.size(); i++) {
            // Get the index of the nearest neighbor
            int neighborIndex = nearestNeighbors.get(i);
            // Retrieve the corresponding distance for that neighbor
            double distance = distances.getEntry(neighborIndex, 0);
            System.out.printf("%.2f", distance); // Print distance formatted to two decimal places

            // Print a comma if it's not the last distance
            if (i < nearestNeighbors.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(); // Move to the next line after distances

        // Display the predicted class
        String predictedClass = getMajorityLabel(nearestNeighbors);
        System.out.println("Predicted class: " + predictedClass);
    }

    // This method gets the feature name based on the index, add it to your KNN class
    private String getFeatureName(int index) {
        switch (index) {
            case 0: return "Pregnancies";
            case 1: return "Glucose";
            case 2: return "Blood Pressure";
            case 3: return "Skin Thickness";
            case 4: return "Insulin";
            case 5: return "BMI";
            case 6: return "Diabetes Pedigree Function";
            case 7: return "Age";
            default: return "Unknown Feature";
        }
    }

}
