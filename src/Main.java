import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Graph graph = new Graph();
    private static List<Node> nodes = graph.nodes;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nMenu:");
            System.out.println("1. Create Node");
            System.out.println("2. Connect Nodes");
            System.out.println("3. Find Shortest Path (Source and Target)");
            System.out.println("4. Find Shortest Path (Source)");
            System.out.println("5. Display linked list of the graph");
            System.out.println("6. Generate random graph");
            System.out.println("7. Generate default graph");
            System.out.println("8. Exit");
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
                    graph.printAsLinkedList();
                    break;
                case 6:
                    graph.generateRandomGraph();
                    break;
                case 7:
                    graph.generateDefaultGraph();
                    break;
                case 8:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }

    private static void findShortestPathsFromSource() {
        if (nodes.isEmpty()) {
            System.out.println("No nodes in the graph. Please create nodes first.");
            return;
        }

        System.out.println("Nodes:");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println((i + 1) + ". " + nodes.get(i).value);
        }

        System.out.print("Select source node: ");
        int sourceIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Node source = nodes.get(sourceIndex);

        Map<Node, Integer> distance = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(distance::get));

        for (Node node : nodes) {
            distance.put(node, Integer.MAX_VALUE);
        }

        distance.put(source, 0);
        pq.add(source);
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Edge edge : current.edges) {
                int newDistance = distance.get(current) + edge.weight;
                if (newDistance < distance.get(edge.destination)) {
                    distance.put(edge.destination, newDistance);
                    pq.add(edge.destination);
                }
            }
        }

        System.out.println("Shortest distances from node " + source.value + ":");
        for (Node node : nodes) {
            if (node != source) {
                int dist = distance.get(node);
                System.out.println(source.value + " -> " + node.value + ": " + (dist == Integer.MAX_VALUE ? "No path" : dist));
            }
        }

    }

    private static void findShortestPath() {
        if (nodes.size() < 2) {
            System.out.println("Insufficient nodes to find shortest path. Please create at least two nodes.");
            return;
        }

        System.out.println("Nodes:");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println((i + 1) + ". " + nodes.get(i).value);
        }

        System.out.print("Select source node: ");
        int sourceIndex = scanner.nextInt() - 1;
        System.out.print("Select destination node: ");
        int destinationIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Node source = nodes.get(sourceIndex);
        Node destination = nodes.get(destinationIndex);

        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(distance::get));

        for (Node node : nodes) {
            distance.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
        }
        distance.put(source, 0);

        pq.add(source);
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current == destination) {
                break;
            }

            for (Edge edge : current.edges) {
                int newDistance = distance.get(current) + edge.weight;
                if (newDistance < distance.get(edge.destination)) {
                    distance.put(edge.destination, newDistance);
                    previous.put(edge.destination, current);
                    pq.add(edge.destination);
                }
            }
        }

        LinkedList<Node> path = new LinkedList<>();
        Node current = destination;
        while (current != null) {
            path.addFirst(current);
            current = previous.get(current);
        }

        if (path.getFirst() == source) {
            System.out.println("Shortest path from " + source.value + " to " + destination.value + ":");
            for (Node node : path) {
                System.out.print(node.value + " -> ");
            }
            System.out.println("Distance: " + distance.get(destination));
        } else {
            System.out.println("There is no path from " + source.value + " to " + destination.value);
        }
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