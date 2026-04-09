import java.util.*;

public class Week3and4 {

    // Linear search for exact threshold match (unsorted)
    static int linearSearch(int[] arr, int target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear search found threshold=" + target + " at index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear search: threshold=" + target + " not found (" + comparisons + " comparisons)");
        return -1;
    }

    // Binary search to find insertion point for new target in sorted array
    // Returns index where target should be inserted to keep order
    static int binarySearchInsertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        System.out.println("Binary search insertion point for " + target + " is index " + low + " (" + comparisons + " comparisons)");
        return low;
    }

    // Binary search floor: largest value ≤ target
    static Integer binarySearchFloor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floorIndex = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (arr[mid] == target) {
                System.out.println("Binary floor of " + target + " is " + arr[mid] + " (exact match, " + comparisons + " comparisons)");
                return arr[mid];
            } else if (arr[mid] < target) {
                floorIndex = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (floorIndex != -1) {
            System.out.println("Binary floor of " + target + " is " + arr[floorIndex] + " (" + comparisons + " comparisons)");
            return arr[floorIndex];
        } else {
            System.out.println("Binary floor of " + target + " does not exist (" + comparisons + " comparisons)");
            return null;
        }
    }

    // Binary search ceiling: smallest value ≥ target
    static Integer binarySearchCeiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ceilIndex = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (arr[mid] == target) {
                System.out.println("Binary ceiling of " + target + " is " + arr[mid] + " (exact match, " + comparisons + " comparisons)");
                return arr[mid];
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                ceilIndex = mid;
                high = mid - 1;
            }
        }
        if (ceilIndex != -1) {
            System.out.println("Binary ceiling of " + target + " is " + arr[ceilIndex] + " (" + comparisons + " comparisons)");
            return arr[ceilIndex];
        } else {
            System.out.println("Binary ceiling of " + target + " does not exist (" + comparisons + " comparisons)");
            return null;
        }
    }

    public static void main(String[] args) {
        int[] sortedRisks = {10, 25, 50, 100};
        int threshold = 30;
        int unsortedRisks[] = {50, 10, 100, 25};

        System.out.println("Linear search on unsorted array:");
        linearSearch(unsortedRisks, threshold);

        System.out.println("\nBinary search operations on sorted array:");
        binarySearchInsertionPoint(sortedRisks, threshold);
        binarySearchFloor(sortedRisks, threshold);
        binarySearchCeiling(sortedRisks, threshold);
    }
}