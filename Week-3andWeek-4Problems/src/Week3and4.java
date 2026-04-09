import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ": fee=" + fee + ", ts=" + timestamp;
    }
}

public class Week3and4 {

    // 🔹 Bubble Sort (Ascending by fee)
    static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        boolean swapped;
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // optimization
        }

        System.out.println("Bubble Sort Completed");
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // 🔹 Insertion Sort (Fee + Timestamp)
    static void insertionSort(List<Transaction> list) {
        int shifts = 0;

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    (list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).timestamp.compareTo(key.timestamp) > 0))) {

                list.set(j + 1, list.get(j));
                j--;
                shifts++;
            }
            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort Completed");
        System.out.println("Total shifts: " + shifts);
    }

    // 🔹 High Fee Outliers (>50)
    static List<Transaction> findOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }
        return outliers;
    }

    // 🔹 Utility Print
    static void printList(List<Transaction> list) {
        for (Transaction t : list) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {

        // Sample Input
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("Original Transactions:");
        printList(transactions);

        // 🔹 Bubble Sort (by fee)
        List<Transaction> bubbleList = new ArrayList<>(transactions);
        bubbleSort(bubbleList);

        System.out.println("\nAfter Bubble Sort (by fee ASC):");
        printList(bubbleList);

        // 🔹 Insertion Sort (fee + timestamp)
        List<Transaction> insertionList = new ArrayList<>(transactions);
        insertionSort(insertionList);

        System.out.println("\nAfter Insertion Sort (fee + timestamp):");
        printList(insertionList);

        // 🔹 Outliers
        List<Transaction> outliers = findOutliers(transactions);
        System.out.println("\nHigh Fee Outliers (>50):");

        if (outliers.isEmpty()) {
            System.out.println("None");
        } else {
            printList(outliers);
        }
    }
}