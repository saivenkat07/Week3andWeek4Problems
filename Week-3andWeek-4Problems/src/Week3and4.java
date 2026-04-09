import java.util.*;

class Client {
    String name;
    int riskScore;
    double balance;

    Client(String name, int riskScore, double balance) {
        this.name = name;
        this.riskScore = riskScore;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name + ": risk=" + riskScore + ", balance=" + balance;
    }
}

public class Week3and4 {

    // 🔹 Bubble Sort (Ascending by Risk Score)
    static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // optimization
        }

        System.out.println("Bubble Sort (ASC) completed. Swaps: " + swaps);
    }

    // 🔹 Insertion Sort (DESC Risk + Balance)
    static void insertionSort(Client[] arr) {
        int shifts = 0;

        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].balance < key.balance))) {

                arr[j + 1] = arr[j];
                j--;
                shifts++;
            }

            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort (DESC) completed. Shifts: " + shifts);
    }

    // 🔹 Get Top N High Risk Clients
    static List<Client> getTopN(Client[] arr, int n) {
        List<Client> top = new ArrayList<>();

        for (int i = 0; i < Math.min(n, arr.length); i++) {
            top.add(arr[i]);
        }
        return top;
    }

    // 🔹 Print Utility
    static void printArray(Client[] arr) {
        for (Client c : arr) {
            System.out.println(c);
        }
    }

    static void printList(List<Client> list) {
        for (Client c : list) {
            System.out.println(c);
        }
    }

    public static void main(String[] args) {

        // Sample Input
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 10000),
                new Client("clientB", 50, 7000)
        };

        System.out.println("Original Clients:");
        printArray(clients);

        // 🔹 Bubble Sort (ASC)
        Client[] bubbleArr = clients.clone();
        bubbleSort(bubbleArr);

        System.out.println("\nAfter Bubble Sort (ASC Risk):");
        printArray(bubbleArr);

        // 🔹 Insertion Sort (DESC)
        Client[] insertionArr = clients.clone();
        insertionSort(insertionArr);

        System.out.println("\nAfter Insertion Sort (DESC Risk + Balance):");
        printArray(insertionArr);

        // 🔹 Top 3 High Risk
        List<Client> topClients = getTopN(insertionArr, 3);

        System.out.println("\nTop High-Risk Clients:");
        printList(topClients);
    }
}