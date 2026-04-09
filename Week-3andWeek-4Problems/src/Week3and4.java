import java.util.*;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ": volume=" + volume;
    }
}

public class Week3and4 {

    // 🔹 Merge Sort (Ascending, Stable)
    static void mergeSort(Trade[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].volume <= arr[j].volume) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        // copy back
        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // 🔹 Quick Sort (Descending)
    static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) { // DESC
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    static void swap(Trade[] arr, int i, int j) {
        Trade temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 🔹 Merge Two Sorted Arrays + Total Volume
    static int mergeAndTotal(Trade[] a, Trade[] b) {
        int i = 0, j = 0;
        int total = 0;

        System.out.println("\nMerged Trades:");

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                System.out.println(a[i]);
                total += a[i].volume;
                i++;
            } else {
                System.out.println(b[j]);
                total += b[j].volume;
                j++;
            }
        }

        while (i < a.length) {
            System.out.println(a[i]);
            total += a[i].volume;
            i++;
        }

        while (j < b.length) {
            System.out.println(b[j]);
            total += b[j].volume;
            j++;
        }

        return total;
    }

    // 🔹 Print Utility
    static void printArray(Trade[] arr) {
        for (Trade t : arr) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {

        // Sample Input
        Trade[] trades = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };

        System.out.println("Original Trades:");
        printArray(trades);

        // 🔹 Merge Sort (Ascending)
        Trade[] mergeArr = trades.clone();
        mergeSort(mergeArr, 0, mergeArr.length - 1);

        System.out.println("\nAfter Merge Sort (ASC Volume):");
        printArray(mergeArr);

        // 🔹 Quick Sort (Descending)
        Trade[] quickArr = trades.clone();
        quickSort(quickArr, 0, quickArr.length - 1);

        System.out.println("\nAfter Quick Sort (DESC Volume):");
        printArray(quickArr);

        // 🔹 Merge two sorted lists (example: morning & afternoon)
        Trade[] morning = {
            new Trade("m1", 100),
            new Trade("m2", 400)
        };

        Trade[] afternoon = {
            new Trade("a1", 200),
            new Trade("a2", 300)
        };

        // Sort both before merging
        mergeSort(morning, 0, morning.length - 1);
        mergeSort(afternoon, 0, afternoon.length - 1);

        int total = mergeAndTotal(morning, afternoon);

        System.out.println("\nTotal Volume: " + total);
    }
}