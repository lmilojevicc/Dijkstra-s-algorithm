import java.util.ArrayList;
import java.util.List;

public class Node {
    String value;
    List<Edge> edges;

    public Node(String value) {
        this.value = value;
        this.edges = new ArrayList<>();
    }
}
