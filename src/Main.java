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
    }

    private static void findShortestPathsFromSource() {
    }

    private static void findShortestPath() {
    }

    private static void connectNodes() {
    }

    private static void createNode() {
    }
}