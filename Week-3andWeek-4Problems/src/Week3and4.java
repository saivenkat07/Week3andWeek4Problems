import java.util.*;

class Transaction {
    String accountId;

    Transaction(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return accountId;
    }
}

public class Week3and4 {

    // Linear search for first occurrence
    static int linearFirstOccurrence(Transaction[] arr, String target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].accountId.equals(target)) {
                System.out.println("Linear first occurrence of " + target + ": index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear search: " + target + " not found (" + comparisons + " comparisons)");
        return -1;
    }

    // Linear search for last occurrence
    static int linearLastOccurrence(Transaction[] arr, String target) {
        int comparisons = 0;
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].accountId.equals(target)) {
                index = i;
            }
        }
        if (index != -1) {
            System.out.println("Linear last occurrence of " + target + ": index " + index + " (" + comparisons + " comparisons)");
        } else {
            System.out.println("Linear search: " + target + " not found (" + comparisons + " comparisons)");
        }
        return index;
    }

    // Binary search for exact match - returns any index of target or -1 if not found
    // Also counts comparisons
    static class BinarySearchResult {
        int index;
        int comparisons;

        BinarySearchResult(int index, int comparisons) {
            this.index = index;
            this.comparisons = comparisons;
        }
    }

    static BinarySearchResult binarySearch(Transaction[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            int cmp = arr[mid].accountId.compareTo(target);
            if (cmp == 0) {
                return new BinarySearchResult(mid, comparisons);
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new BinarySearchResult(-1, comparisons);
    }

    // Count total occurrences of target in sorted array using binary search for bounds
    static int countOccurrences(Transaction[] arr, String target) {
        int first = findFirstOccurrence(arr, target);
        if (first == -1) return 0;
        int last = findLastOccurrence(arr, target);
        return last - first + 1;
    }

    // Find first occurrence in sorted array using binary search
    static int findFirstOccurrence(Transaction[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = arr[mid].accountId.compareTo(target);
            if (cmp == 0) {
                result = mid;
                high = mid - 1; // search left side
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // Find last occurrence in sorted array using binary search
    static int findLastOccurrence(Transaction[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = arr[mid].accountId.compareTo(target);
            if (cmp == 0) {
                result = mid;
                low = mid + 1; // search right side
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // Utility: sort transactions by accountId ascending
    static void sortTransactions(Transaction[] arr) {
        Arrays.sort(arr, Comparator.comparing(t -> t.accountId));
    }

    public static void main(String[] args) {
        // Sample input
        Transaction[] transactions = {
                new Transaction("accB"),
                new Transaction("accA"),
                new Transaction("accB"),
                new Transaction("accC")
        };

        System.out.println("Original Transactions:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }

        // Linear search first and last occurrence
        linearFirstOccurrence(transactions, "accB");
        linearLastOccurrence(transactions, "accB");

        // Sort for binary search
        sortTransactions(transactions);
        System.out.println("\nSorted Transactions:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }

        // Binary search for accB
        BinarySearchResult res = binarySearch(transactions, "accB");
        if (res.index != -1) {
            System.out.println("\nBinary search accB: index " + res.index + " (" + res.comparisons + " comparisons)");
        } else {
            System.out.println("\nBinary search accB: not found (" + res.comparisons + " comparisons)");
        }

        // Count occurrences of accB
        int count = countOccurrences(transactions, "accB");
        System.out.println("Count of accB: " + count);
    }
}