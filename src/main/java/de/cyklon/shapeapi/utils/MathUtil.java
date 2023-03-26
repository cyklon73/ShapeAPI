package de.cyklon.shapeapi.utils;

import org.bukkit.util.Vector;

public class MathUtil {

    public static double[] multiplyMatrixAndPoint(double[][] matrix, double[] point) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double[] result = new double[rows];

        for (int i = 0; i < rows; i++) {
            double sum = 0;
            for (int j = 0; j < cols; j++) {
                sum += matrix[i][j] * point[j];
            }
            result[i] = sum;
        }
        return result;
    }

    public static double[][] createRotationMatrix(Vector rotation) {
        double[][] rotationMatrixX = new double[][] {
                {1, 0, 0},
                {0, Math.cos(rotation.getX()), -Math.sin(rotation.getX())},
                {0, Math.sin(rotation.getX()), Math.cos(rotation.getX())}
        };

        double[][] rotationMatrixY = new double[][] {
                {Math.cos(rotation.getY()), 0, Math.sin(rotation.getY())},
                {0, 1, 0},
                {-Math.sin(rotation.getY()), 0, Math.cos(rotation.getY())}
        };

        double[][] rotationMatrixZ = new double[][] {
                {Math.cos(rotation.getZ()), -Math.sin(rotation.getZ()), 0},
                {Math.sin(rotation.getZ()), Math.cos(rotation.getZ()), 0},
                {0, 0, 1}
        };

        double[][] rotationMatrix = multiplyMatrices(rotationMatrixX, rotationMatrixY);
        return multiplyMatrices(rotationMatrix, rotationMatrixZ);
    }

    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        int m1rows = matrix1.length;
        int m1cols = matrix1[0].length;
        int m2cols = matrix2[0].length;

        double[][] result = new double[m1rows][m2cols];
        for (int i = 0; i < m1rows; i++) {
            for (int j = 0; j < m2cols; j++) {
                for (int k = 0; k < m1cols; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

}
