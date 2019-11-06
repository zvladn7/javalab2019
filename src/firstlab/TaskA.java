package firstlab;


import java.util.Scanner;

public class TaskA {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[][] matrix = createMatrix(scanner.nextInt(), scanner.nextInt());
    printMatrix(matrix);
  }

  private static int[][] createMatrix(int rows, int columns) {
    int[][] matrix = new int[rows][columns];
    for (int i = 0; i < rows; ++i) {
      matrix[i][0] = 1;
    }
    return matrix;
  }

  private static void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[i].length; ++j) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
}