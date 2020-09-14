package DijkstraSkeleton;

public class Node implements Comparable<Node> {
    public int id;
    public int distance;

    public Node(int id, int distance) {
        this.id = id;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.distance, o.getDistance());
    }
}
