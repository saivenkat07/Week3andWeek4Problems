import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ": return=" + returnRate + "%, volatility=" + volatility;
    }
}

public class Week3and4 {

    // 🔹 Merge Sort (ASC by returnRate, stable)
    static void mergeSort(Asset[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].returnRate <= arr[j].returnRate) {
                temp[k++] = arr[i]; // keeps stability
                i++;
            } else {
                temp[k++] = arr[j];
                j++;
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // 🔹 Quick Sort (DESC returnRate + ASC volatility)
    static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {

            // Hybrid: switch to insertion sort for small partitions
            if (high - low < 10) {
                insertionSort(arr, low, high);
                return;
            }

            int pivotIndex = medianOf3(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate &&
                            arr[j].volatility < pivot.volatility)) {

                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // 🔹 Median-of-3 Pivot Selection
    static int medianOf3(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        if (arr[low].returnRate > arr[mid].returnRate)
            swap(arr, low, mid);

        if (arr[low].returnRate > arr[high].returnRate)
            swap(arr, low, high);

        if (arr[mid].returnRate > arr[high].returnRate)
            swap(arr, mid, high);

        return mid;
    }

    // 🔹 Insertion Sort (for small partitions)
    static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low &&
                    (arr[j].returnRate < key.returnRate ||
                            (arr[j].returnRate == key.returnRate &&
                                    arr[j].volatility > key.volatility))) {

                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 🔹 Swap utility
    static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 🔹 Print utility
    static void printArray(Asset[] arr) {
        for (Asset a : arr) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {

        // Sample Input
        Asset[] assets = {
                new Asset("AAPL", 12, 0.3),
                new Asset("TSLA", 8, 0.6),
                new Asset("GOOG", 15, 0.2),
                new Asset("MSFT", 12, 0.25)
        };

        System.out.println("Original Assets:");
        printArray(assets);

        // 🔹 Merge Sort (ASC return)
        Asset[] mergeArr = assets.clone();
        mergeSort(mergeArr, 0, mergeArr.length - 1);

        System.out.println("\nAfter Merge Sort (ASC Return):");
        printArray(mergeArr);

        // 🔹 Quick Sort (DESC return + volatility)
        Asset[] quickArr = assets.clone();
        quickSort(quickArr, 0, quickArr.length - 1);

        System.out.println("\nAfter Quick Sort (DESC Return + ASC Volatility):");
        printArray(quickArr);
    }
}