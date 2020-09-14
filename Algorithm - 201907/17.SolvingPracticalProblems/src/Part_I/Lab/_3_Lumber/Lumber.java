package Part_I.Lab._3_Lumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Lumber {
    private static int logsCount;
    private static int queriesCount;
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[] id;
    private static int count = 0;

    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        readInput();
        canTravel();
    }

    private static void readInput() throws IOException {
        String[] line = in.readLine().split(" ");
        logsCount = Integer.parseInt(line[0]);
        queriesCount = Integer.parseInt(line[1]);
        List<Log> logList = new ArrayList<>();
        graph = new ArrayList[logsCount + 1];

        for (int i = 1; i <= logsCount; i++) {
            line = in.readLine().split(" ");
            int x1 = Integer.parseInt(line[0]);
            int y1 = Integer.parseInt(line[1]);
            int x2 = Integer.parseInt(line[2]);
            int y2 = Integer.parseInt(line[3]);
            Log log = new Log(i, x1, x2, y1, y2);
            graph[i] = new ArrayList<>();

            for (Log element : logList) {
                if (element.intersects(log)) {
                    graph[element.id].add(i);
                    graph[i].add(element.id);
                }
            }
            logList.add(log);
        }

    }

    private static void canTravel() throws IOException {
        visited = new boolean[logsCount + 1];
        id = new int[logsCount + 1];

        for (int node = 1; node <= logsCount; node++) {
            if (!visited[node]) {
                DFS(node);
                count++;
            }
        }

        for (int i = 0; i < queriesCount; i++) {
            String[] queryData = in.readLine().split(" ");
            int from = Integer.parseInt(queryData[0]);
            int to = Integer.parseInt(queryData[1]);

            System.out.println(id[from] == id[to] ? "YES" : "NO");
        }
    }

    private static void DFS(int node) {
        visited[node] = true;
        id[node] = count;

        for (int child : graph[node]) {
            if (!visited[child]) {
                DFS(child);
            }
        }
    }

    private static class Log {
        public int id;
        public int x1;
        public int x2;
        public int y1;
        public int y2;

        Log(int id, int x1, int x2, int y1, int y2) {
            this.id = id;
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        public boolean intersects(Log other) {
            return this.x1 <= other.x2 && this.x2 >= other.x1 &&
                    this.y1 >= other.y2 && this.y2 <= other.y1;
        }
    }
}
