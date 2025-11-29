/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA2_2025181;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khongorzul
 */

// Implements a recursive MergeSort for Employee lists.
public class MergeSort {

    public static void mergeSort(List<Employee> list) {
        if (list == null || list.size() <= 1) {
            return; // Already sorted or nothing to sort
        }
        // Use a recursive helper to perform divide & conquer
        mergeSortRecursive(list, 0, list.size() - 1);
    }

    private static void mergeSortRecursive(List<Employee> list, int left, int right) {
        if (left >= right) {
            return; // Base one element
        }

        int mid = left + (right - left) / 2;

        // Recursively sort left and right halves
        mergeSortRecursive(list, left, mid);
        mergeSortRecursive(list, mid + 1, right);

        // Merge the two sorted halves
        merge(list, left, mid, right);
    }

    private static void merge(List<Employee> list, int left, int mid, int right) {
        List<Employee> temp = new ArrayList<>();

        int i = left;
        int j = mid + 1;

        // Merge two sorted sublists into temp list
        while (i <= mid && j <= right) {
            if (list.get(i).compareTo(list.get(j)) <= 0) {
                temp.add(list.get(i));
                i++;
            } else {
                temp.add(list.get(j));
                j++;
            }
        }

        // Copy remaining elements from left side
        while (i <= mid) {
            temp.add(list.get(i));
            i++;
        }

        // Copy remaining elements from right side
        while (j <= right) {
            temp.add(list.get(j));
            j++;
        }

        // Move sorted data back into the original list segment
        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }
}