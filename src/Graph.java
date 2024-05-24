import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    List<Node> nodes;
    Random random;

    public Graph() {
        this.nodes = new ArrayList<>();
        random = new Random();
        generateDefaultGraph();
    }

    public void generateDefaultGraph() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");

        this.nodes.add(a);
        this.nodes.add(b);
        this.nodes.add(d);
        this.nodes.add(e);
        this.nodes.add(c);

        a.addEdgeBidirectional(b, 5);
        a.addEdgeBidirectional(c, 3);
        b.addEdgeBidirectional(c, 2);
        b.addEdgeBidirectional(d, 4);
        c.addEdgeBidirectional(d, 6);
        c.addEdgeBidirectional(e, 7);
        d.addEdgeBidirectional(e, 8);
    }

    public void generateRandomGraph() {
        nodes.clear();
        for (char i = 'A'; i <= 'E'; i++) {
            nodes.add(new Node(String.valueOf(i)));
        }

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                if (random.nextBoolean()) {
                    int weight = random.nextInt(5) + 1;
                    nodes.get(i).addEdgeBidirectional(nodes.get(j), weight);
                }
            }
        }
    }

    public void printAsLinkedList() {
        System.out.println("Graph as Linked list:");
        for (Node node : nodes) {
            System.out.print(node.value + ". ");
            if (node.edges.isEmpty()) {
                System.out.println("null");
            } else {
                for (int i = 0; i < node.edges.size(); i++) {
                    Edge edge = node.edges.get(i);
                    System.out.print("(" + edge.destination.value + "," + edge.weight + ")");
                    if (i < node.edges.size() - 1) {
                        System.out.print("->");
                    } else {
                        System.out.println("->null");
                    }
                }
            }
        }
    }
}
