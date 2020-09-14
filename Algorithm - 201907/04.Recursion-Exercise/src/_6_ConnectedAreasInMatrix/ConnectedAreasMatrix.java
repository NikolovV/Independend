package _6_ConnectedAreasInMatrix;

import java.util.*;
import java.util.stream.Collectors;

public class ConnectedAreasMatrix {
    private static final char WALL = '*';
    private static final char VISITED = '+';
    private static char[][] matrix;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rowSize = Integer.parseInt(in.nextLine());
        int colSize = Integer.parseInt(in.nextLine());

        matrix = new char[rowSize][colSize];
        fillMatrix(in);
        printMatrix();

//        matrix[0][3] = matrix[1][3] = matrix[2][3] = matrix[3][4] = matrix[3][6] =
//                matrix[2][7] = matrix[1][7] = matrix[0][7] = '*';

        List<ConnectedAriaInfo> connectedAriaInfoList = new ArrayList<>();
        checkForFreeArea(connectedAriaInfoList);
        Collections.sort(connectedAriaInfoList);
        System.out.println("Total areas found: " + Integer.toString(connectedAriaInfoList.size()));
        printAreas(connectedAriaInfoList);
    }

    private static void printAreas(List<ConnectedAriaInfo> connectedAriaInfoList) {
        for (int i = 0; i < connectedAriaInfoList.size(); i++) {
            System.out.println("Area #" + (i + 1) + " at " + connectedAriaInfoList.get(i).toString());
        }
    }

    private static void fillMatrix(Scanner in) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = in.nextLine().toCharArray();
        }
    }

    private static void checkForFreeArea(List<ConnectedAriaInfo> connectedAriaInfo) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] != WALL && !isVisited(row, col)) {
                    int areaSize = traverseArea(row, col);

                    connectedAriaInfo.add(new ConnectedAriaInfo());
                    connectedAriaInfo.get(connectedAriaInfo.size() - 1).setAreaStartCoordinate(row, col);
                    connectedAriaInfo.get(connectedAriaInfo.size() - 1).setSize(areaSize);
                }
            }
        }
    }

    private static int traverseArea(int row, int col) {
        int areaCount = 0;
        if (isInBound(row, col)) {
            if (!isVisited(row, col) && !isWall(row, col)) {
                markVisited(row, col);
                areaCount += 1;
                areaCount += traverseArea(row + 1, col); //Down
                areaCount += traverseArea(row, col + 1); //Right
                areaCount += traverseArea(row - 1, col); //Up
                areaCount += traverseArea(row, col - 1); //Left
            }
        }
        return areaCount;
    }

    private static void markVisited(int row, int col) {
        matrix[row][col] = VISITED;
    }

    private static boolean isVisited(int row, int col) {
        return matrix[row][col] == VISITED;
    }

    private static boolean isInBound(int row, int col) {
        return (row >= 0 && col >= 0 &&
                row < matrix.length && col < matrix[0].length);
    }

    private static boolean isWall(int row, int col) {
        return matrix[row][col] == WALL;
    }

    private static void printMatrix() {
        for (char[] chars : matrix) {
            System.out.println(Arrays.toString(chars));
        }
    }
}

class ConnectedAriaInfo implements Comparable<ConnectedAriaInfo> {
    private int size;
    private int[] areaStartCoordinate = new int[2];

    public ConnectedAriaInfo() {
    }

    public ConnectedAriaInfo(int row, int col, int size) {
        this.setSize(size);
        this.setAreaStartCoordinate(row, col);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[] getAreaStartCoordinate() {
//        return "(" + Arrays.stream(areaStartCoordinate).mapToObj(String::valueOf).collect(Collectors.joining(", ")) + ")";
        return this.areaStartCoordinate;
    }

    public void setAreaStartCoordinate(int row, int col) {
        this.areaStartCoordinate[0] = row;
        this.areaStartCoordinate[1] = col;
    }

    private boolean greaterCoordinate(int[] area) {
        for (int i = 0; i < area.length; i++) {
            if (this.areaStartCoordinate[i] > area[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(areaStartCoordinate);
        return result;
    }

    // this < ret -1 , this == ret 0, this > ret 1
    @Override
    public int compareTo(ConnectedAriaInfo connectedAriaInfo) {
        if (this.size == connectedAriaInfo.getSize()) {
            if (this.greaterCoordinate(connectedAriaInfo.getAreaStartCoordinate())) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.size < connectedAriaInfo.getSize()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "(" + Arrays.stream(this.areaStartCoordinate).mapToObj(String::valueOf).collect(Collectors.joining(", ")) + ")," +
                " size: " + size;
    }
}
