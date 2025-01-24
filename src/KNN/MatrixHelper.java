package KNN;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.stream.IntStream;

public class MatrixHelper {

    static RealMatrix division(RealMatrix matrix1, RealMatrix matrix2) {
        double[][] result = new double[matrix1.getRowDimension()][matrix1.getColumnDimension()];
        IntStream.range(0, matrix1.getRowDimension()).forEach(i ->
            IntStream.range(0, matrix1.getColumnDimension()).forEach(j ->
                result[i][j] = matrix1.getEntry(i, j) / matrix2.getEntry(i, j)
            )
        );
        return MatrixUtils.createRealMatrix(result);
    }

    static RealMatrix sum(RealMatrix matrix) {
        double[][] result = new double[matrix.getRowDimension()][1];
        IntStream.range(0, matrix.getRowDimension()).forEach(i ->
            result[i][0] = IntStream.range(0, matrix.getColumnDimension())
                .mapToDouble(c -> matrix.getEntry(i, c))
                .sum()
        );
        return MatrixUtils.createRealMatrix(result);
    }

    static RealMatrix square(RealMatrix matrix) {
        double[][] result = new double[matrix.getRowDimension()][matrix.getColumnDimension()];
        IntStream.range(0, matrix.getRowDimension()).forEach(i ->
            IntStream.range(0, matrix.getColumnDimension()).forEach(j ->
                result[i][j] = Math.pow(matrix.getEntry(i, j), 2)
            )
        );
        return MatrixUtils.createRealMatrix(result);
    }

    static RealMatrix squareRoot(RealMatrix matrix) {
        double[][] result = new double[matrix.getRowDimension()][matrix.getColumnDimension()];
        IntStream.range(0, matrix.getRowDimension()).forEach(i ->
            IntStream.range(0, matrix.getColumnDimension()).forEach(j ->
                result[i][j] = Math.sqrt(matrix.getEntry(i, j))
            )
        );
        return MatrixUtils.createRealMatrix(result);
    }
}
