package _11_B_treesAndRed_Black_Trees_Exercise.Heaps;

import java.util.*;

public class AStar {
    private char[][] map;
    private PriorityQueue<Node> queue;

    public AStar(char[][] map) {
        this.setMap(map);
        this.setQueue(new PriorityQueue<>());
    }

    public static int getH(Node current, Node goal) {
        int deltaX = Math.abs(current.getCol() - goal.getCol());
        int deltaY = Math.abs(current.getRow() - goal.getRow());

        return deltaX + deltaY;
    }

    public Iterable<Node> getPath(Node start, Node goal) {
        Map<Node, Node> parentMap = new LinkedHashMap<>();
        parentMap.put(start, null);
        this.queue.enqueue(start);

        while (this.queue.size() > 0) {
            Node current = this.queue.dequeue();
            int currentRow = current.getRow();
            int currentCol = current.getCol();

            if (current.equals(goal)) {
                return AStar.recoverPath(start, goal, parentMap);
            }

            this.checkNeighbour(currentRow + 1, currentCol, current, goal, parentMap); // Down
            this.checkNeighbour(currentRow - 1, currentCol, current, goal, parentMap); // UP
            this.checkNeighbour(currentRow, currentCol + 1, current, goal, parentMap); // Right
            this.checkNeighbour(currentRow, currentCol - 1, current, goal, parentMap); // Left
        }

        List<Node> path = new ArrayList<>();
        path.add(null);
        return path;
    }

    private static List<Node> recoverPath(Node start, Node goal, Map<Node, Node> parentMap) {
        List<Node> path = new ArrayList<>();
        Node temp = goal;
        path.add(temp);
        while (!temp.equals(start)) {
            temp = parentMap.get(temp);
            path.add(temp);
        }
        Collections.reverse(path);
        return path;
    }

    private void checkNeighbour(int row, int col, Node current, Node goal, Map<Node, Node> parentMap) {
        Node node = new Node(row, col);

        if (this.isFreePosition(row, col) && !parentMap.containsKey(node)) {
            node.setG(current.getG() + 1);
            node.setF(node.getG() + getH(current, goal));
            this.queue.enqueue(node);
            parentMap.put(node, current);
        }
    }

    private boolean isFreePosition(int row, int col) {
        return row >= 0 && col >= 0 && row < this.map.length
                && col < this.map[row].length
                && this.map[row][col] != 'W';
    }

    public char[][] getMap() {
        return this.map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public PriorityQueue<Node> getQueue() {
        return this.queue;
    }

    public void setQueue(PriorityQueue<Node> queue) {
        this.queue = queue;
    }
}
