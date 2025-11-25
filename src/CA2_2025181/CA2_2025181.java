/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CA2_2025181;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Khongorzul
 */

public class CA2_2025181 {

    /**
     * @param args the command line arguments
     */

    // Application for the Bank organisation.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read file name and load applicants
        System.out.println("Press enter to start. Filename: (Applicants_Form.txt)");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) {
            filename = "src/Applicants_Form.txt"; // default fallback if user just presses Enter
        }

        List<String> applicantNames = FileUtil.readNamesFromFile(filename);

        // Convert names into employees by assigning random manager and department
        List<Employee> employees = new ArrayList<>();
        if (!applicantNames.isEmpty()) {
            employees.addAll(RandomEmployee.createEmployeesFromNames(applicantNames));
        }

        boolean exit = false;

        while (!exit) {
            printMainMenu();
            int choice = InputValidator.readIntInRange(scanner, 1, 5);
            Menu selected = Menu.fromCode(choice);

            if (selected == null) {
                System.out.println("Unknown option. Please try again.");
                continue;
            }

            switch (selected) {
                case SORT:
                    handleSort(employees);
                    break;
                case SEARCH:
                    handleSearch(scanner, employees);
                    break;
                case ADD_EMPLOYEE:
                    handleAddEmployee(scanner, employees);
                    break;
                case SELECT_RANDOM:
                    handleSelectRandom(scanner, employees, applicantNames);
                    break;
                case EXIT:
                    exit = true;
                    System.out.println("The system is out.");
                    break;
            }
        }

        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n--- BANK ORGANISATION MENU ---");
        for (Menu option : Menu.values()) {
            System.out.println(option.getCode() + ". " + option.getDescription());
        }
        System.out.print("Please select an option: ");
    }

    // SORT – uses recursive MergeSort and displays first 20 employees.
    private static void handleSort(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees available to sort.");
            return;
        }

        System.out.println("\nSorting employees alphabetically by name.");
        MergeSort.mergeSort(employees);

        System.out.println("First 20 employees:");
        int limit = Math.min(20, employees.size());
        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ". " + employees.get(i));
        }
    }

    // SEARCH – lets user choose between exact (binary) and partial search.
    private static void handleSearch(Scanner scanner, List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees available to search.");
            return;
        }

        // Ensure the list is sorted before using binary search
        MergeSort.mergeSort(employees);

        System.out.println("\nSEARCH OPTIONS:");
        System.out.println("1. Exact search by full name (binary search)");
        System.out.println("2. Partial search (contains substring)");
        System.out.print("Choose search type: ");

        int searchChoice = InputValidator.readIntInRange(scanner, 1, 2);

        if (searchChoice == 1) {
            String name = InputValidator.readNonEmptyString(scanner, "Enter full name to search: ");
            Employee found = Search.binarySearchExact(employees, name);
            if (found != null) {
                System.out.println("Employee found:");
                System.out.println(found);
            } else {
                System.out.println("No employee found with name: " + name);
            }
        } else {
            String part = InputValidator.readNonEmptyString(scanner, "Enter part of the name to search: ");
            List<Employee> matches = Search.partialSearch(employees, part);
            if (matches.isEmpty()) {
                System.out.println("No employees matched the search text: " + part);
            } else {
                System.out.println("Employees matching \"" + part + "\":");
                int index = 1;
                for (Employee e : matches) {
                    System.out.println(index + ". " + e);
                    index++;
                }
            }
        }
    }

    // ADD_EMPLOYEE – add a new employee with manager and department validation.
    private static void handleAddEmployee(Scanner scanner, List<Employee> employees) {
        System.out.println("\nADD NEW EMPLOYEE:");

        String name = InputValidator.readNonEmptyString(scanner, "Enter full name: ");

        // Select ManagerType
        System.out.println("Select Manager Type:");
        Manager[] managerTypes = Manager.values();
        for (int i = 0; i < managerTypes.length; i++) {
            System.out.println((i + 1) + ". " + managerTypes[i].getDisplayName());
        }
        System.out.print("Enter choice: ");
        int managerChoice = InputValidator.readIntInRange(scanner, 1, managerTypes.length);
        Manager managerType = managerTypes[managerChoice - 1];

        // Select DepartmentType
        System.out.println("Select Department:");
        Department[] departmentTypes = Department.values();
        for (int i = 0; i < departmentTypes.length; i++) {
            System.out.println((i + 1) + ". " + departmentTypes[i].getDisplayName());
        }
        System.out.print("Enter choice: ");
        int deptChoice = InputValidator.readIntInRange(scanner, 1, departmentTypes.length);
        Department departmentType = departmentTypes[deptChoice - 1];

        Employee newEmployee = new Employee(name, managerType, departmentType);
        employees.add(newEmployee);

        System.out.println("\"" + name + "\" has been added as \"" +
                managerType.getDisplayName() + "\" to \"" +
                departmentType.getDisplayName() + "\" successfully!");
    }

    // SELECT_RANDOM – create random employees using names from Applicants_Form.txt
    private static void handleSelectRandom(Scanner scanner,
                                             List<Employee> employees,
                                             List<String> applicantNames) {

        if (applicantNames == null || applicantNames.isEmpty()) {
            System.out.println("Cannot select random employees because no names were loaded from file.");
            return;
        }

        System.out.print("\nHow many random employees do you want to select? ");
        int count = InputValidator.readIntInRange(scanner, 1, 1000);

        List<Employee> randomEmployees = RandomEmployee.generateRandomEmployees(applicantNames, count);
        employees.addAll(randomEmployees);

        System.out.println(count + " random employees selected and added to the list:");
        int index = 1;
        for (Employee e : randomEmployees) {
            System.out.println(index + ". " + e);
            index++;
        }
    }
}