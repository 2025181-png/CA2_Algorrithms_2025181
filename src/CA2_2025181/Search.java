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

// Exact (binary search) and partial (linear scan).
public class Search {

    // Performs binary search on a sorted list of Employees by fullName (case-insensitive).
    // Returns the first matching Employee or null if not found.
    public static Employee binarySearchExact(List<Employee> sortedEmployees, String targetName) {
        int low = 0;
        int high = sortedEmployees.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Employee midEmployee = sortedEmployees.get(mid);

            int cmp = midEmployee.getFullName().compareToIgnoreCase(targetName);

            if (cmp == 0) {
                return midEmployee; // Exact match
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null;
    }

    // Performs partial search by checking if the full name contains the query substring.
    // This is case-insensitive and returns all matching employees.
    public static List<Employee> partialSearch(List<Employee> employees, String partialQuery) {
        List<Employee> matches = new ArrayList<>();
        String queryLower = partialQuery.toLowerCase();

        for (Employee e : employees) {
            if (e.getFullName().toLowerCase().contains(queryLower)) {
                matches.add(e);
            }
        }

        return matches;
    }
}