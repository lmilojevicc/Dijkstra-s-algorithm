import java.util.ArrayList;
import java.util.List;

public class Node {
    String value;
    List<Edge> edges;

    public Node(String value) {
        this.value = value;
        this.edges = new ArrayList<>();
    }

    public void addEdgeBidirectional(Node destination, int weight) {
        if (!isEdgePresent(destination)) {
            Edge edge = new Edge(this, destination, weight);
            Edge reverseEdge = new Edge(destination, this, weight);

            this.edges.add(edge);
            destination.edges.add(reverseEdge);
        }

    }

    private boolean isEdgePresent(Node destination) {
        for (Edge edge : this.edges) {
            if (edge.destination == destination) {
                return true;
            }
        }
        return false;
    }
}
