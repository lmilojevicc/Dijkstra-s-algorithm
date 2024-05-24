import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Node> nodes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nMenu:");
            System.out.println("1. Create Node");
            System.out.println("2. Connect Nodes");
            System.out.println("3. Find Shortest Path (Source and Target)");
            System.out.println("4. Find Shortest Path (Source)");
            System.out.println("5. Display linked list of the graph");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createNode();
                    break;
                case 2:
                    connectNodes();
                    break;
                case 3:
                    findShortestPath();
                    break;
                case 4:
                    findShortestPathsFromSource();
                    break;
                case 5:
                    printLinkedList();
                    break;
                case 6:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }

    private static void printLinkedList() {
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

    private static void findShortestPathsFromSource() {
    }

    private static void findShortestPath() {
    }

    private static void connectNodes() {
        if (nodes.size() < 2) {
            System.out.println("Insufficient number of nodes. Create at least two nodes.");
            return;
        }

        System.out.println("Nodes: ");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println((i + 1) + "." + nodes.get(i).value);
        }

        System.out.print("Select source node: ");
        int sourceIndex = scanner.nextInt() - 1;
        System.out.print("Select destination node: ");
        int destinationIndex = scanner.nextInt() - 1;
        System.out.print("Enter weight: ");
        int weight = scanner.nextInt();
        scanner.nextLine();

        Node source = nodes.get(sourceIndex);
        Node destination = nodes.get(destinationIndex);

        source.addEdgeBidirectional(destination, weight);

        System.out.println("Created an edge between " + source.value + " and " + destination.value + " with weight " + weight);
    }

    private static void createNode() {
        System.out.print("Enter Node value: ");
        String value = scanner.nextLine();
        nodes.add(new Node(value));
        System.out.print("Node created successfully");
    }
}