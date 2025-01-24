package KNN;

import org.apache.commons.math3.linear.RealMatrix;
import java.util.ArrayList;
import java.util.Arrays;

// This class helps with displaying the results on the console.
public class Displayer {

    private KNearestNeighbour knn;

    // Constructor to initialize the KNN object.
    public Displayer(KNearestNeighbour knn) {
        this.knn = knn;
    }

    // Method to display the initial table with headings.
    public Displayer displayInitialTable(String[][] heading) {
        System.out.println("\nInitial Table:");
        for (String[] row : heading) {
            System.out.println(Arrays.toString(row));
        }
        return this;
    }

    // Method to display initial information such as headings, meta-data, or other descriptive information.
    public Displayer displayInitialInformation(String[][] heading) {
        System.out.println("\nInitial Information:");
        for (String[] row : heading) {
            System.out.println(Arrays.toString(row));
        }
        return this;
    }

    // Method to display distance information.
    public Displayer displayDistanceInfo(RealMatrix inp, RealMatrix inpNorm, ArrayList<Integer> neighbours, RealMatrix distances) {
        System.out.println("\n--- Distance Information ---");
        System.out.println("Input Matrix (Raw):");
        printMatrix(inp);
        System.out.println("\nInput Matrix (Normalized):");
        printMatrix(inpNorm);
        
        System.out.println("\nDistances to Neighbours:");
        for (int i = 0; i < neighbours.size(); i++) {
            System.out.println("Neighbour " + (i + 1) + ": Index " + neighbours.get(i) + " - Distance: " + distances.getEntry(0, i));
        }
        return this;
    }

    // Method to display the table header (usually to display normalized input data).
    public Displayer displayTableHead(RealMatrix inpNorm) {
        System.out.println("\nTable Header (Normalized Data):");
        printMatrix(inpNorm);
        return this;
    }

    // Method to display the table body (distance info with nearest neighbours).
    public Displayer displayTableBody(RealMatrix inpNorm, ArrayList<Integer> neighbours, RealMatrix distances) {
        System.out.println("\nTable Body:");
        for (int i = 0; i < neighbours.size(); i++) {
            System.out.print("Neighbour " + neighbours.get(i) + " | Normalized Features: ");
            printRow(inpNorm, neighbours.get(i));
            System.out.println(" | Distance: " + distances.getEntry(0, i));
        }
        return this;
    }

    // Helper method to print a matrix.
    private void printMatrix(RealMatrix matrix) {
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            System.out.println(Arrays.toString(matrix.getRow(i)));
        }
    }

    // Helper method to print a specific row in a matrix.
    private void printRow(RealMatrix matrix, int rowIndex) {
        System.out.print(Arrays.toString(matrix.getRow(rowIndex)));
    }
}
