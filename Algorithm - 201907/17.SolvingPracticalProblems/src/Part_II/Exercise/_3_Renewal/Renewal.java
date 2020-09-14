package Part_II.Exercise._3_Renewal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Renewal {

    private static int[][] graph;
    private static int[][] buildCosts;
    private static int[][] destroyCosts;

    public static void main(String[] args) throws IOException {
        readInput();

        Set<CityPath> cityPaths = getCityPaths();

        int totalCost = kruskal(cityPaths);
        System.out.println(totalCost);
    }

    private static void readInput() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(in.readLine());
        graph = new int[size][size];
        buildCosts = new int[size][size];
        destroyCosts = new int[size][size];

        for (int i = 0; i < size; i++) {
            String[] currentCityPath = in.readLine().split("");
            for (int j = 0; j < size; j++) {
                graph[i][j] = Integer.parseInt(currentCityPath[j]);
            }
        }

        for (int i = 0; i < size; i++) {
            char[] currentBuildCosts = in.readLine().toCharArray();
            for (int j = 0; j < size; j++) {
                int cost = convertCharToCost(currentBuildCosts[j]);
                buildCosts[i][j] = cost;
            }
        }

        for (int i = 0; i < size; i++) {
            char[] currentDestroyCost = in.readLine().toCharArray();
            for (int j = 0; j < size; j++) {
                int cost = convertCharToCost(currentDestroyCost[j]);
                destroyCosts[i][j] = cost;
            }
        }
    }

    private static int convertCharToCost(char token) {
        int cost;
        if (Character.isUpperCase(token)) {
            cost = token - 'A';
        } else {
            cost = token - 'a' + 26;
        }
        return cost;
    }

    private static Set<CityPath> getCityPaths() {
        Set<CityPath> cityPaths = new TreeSet<>();
        for (int row = 0; row < graph.length - 1; row++) {
            for (int col = row + 1; col < graph.length; col++) {
                int cost = (graph[row][col] == 0) ? buildCosts[row][col] : -destroyCosts[row][col];

                CityPath cityPath = new CityPath(row, col, cost);
                cityPaths.add(cityPath);
            }
        }
        return cityPaths;
    }

    private static int kruskal(Set<CityPath> cityPathSet) {
        int totalCost = 0;
        int[] parent = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            parent[i] = i;
        }

        for (CityPath cityPath : cityPathSet) {
            int firstCity = cityPath.getFirstCity();
            int secondCity = cityPath.getSecondCity();
            int cost = cityPath.getCost();

            int startVertexRoot = findRoot(firstCity, parent);
            int endVertexRoot = findRoot(secondCity, parent);

            if (startVertexRoot != endVertexRoot) {
                parent[startVertexRoot] = endVertexRoot;
                if (cost > 0) {
                    totalCost += cost;
                }
            } else if (cost < 0) {
                totalCost -= cost;
            }
        }
        return totalCost;
    }

    private static int findRoot(int vertex, int[] parent) {
        int root = vertex;
        while (parent[root] != root) {
            root = parent[root];
        }

        while (vertex != root) {
            int oldParent = parent[vertex];
            parent[vertex] = root;
            vertex = oldParent;
        }
        return root;
    }

    private static class CityPath implements Comparable<CityPath> {

        private final int firstCity;
        private final int secondCity;
        private final int cost;

        CityPath(int startNode, int endNode, int weight) {
            this.firstCity = startNode;
            this.secondCity = endNode;
            this.cost = weight;
        }

        public int getFirstCity() {
            return this.firstCity;
        }

        public int getSecondCity() {
            return this.secondCity;
        }

        public int getCost() {
            return this.cost;
        }

        @Override
        public int compareTo(CityPath o) {
            int comp = Integer.compare(this.cost, o.cost);
            if (comp == 0) {
                comp = Integer.compare(this.firstCity, o.firstCity);
                if (comp == 0) {
                    comp = Integer.compare(this.secondCity, o.secondCity);
                }
            }
            return comp;
        }
    }
}