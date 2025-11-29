/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA2_2025181;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Khongorzul
 */

// Generates random employees using ONLY names loaded from Applicants_Form.txt.
public class RandomEmployee {

    private static final Random RANDOM = new Random();

    // Assigns random ManagerType and DepartmentType to each name and converts the names into Employee objects.
    public static List<Employee> createEmployeesFromNames(List<String> names) {
        List<Employee> employees = new ArrayList<>();
        for (String name : names) {
            employees.add(createRandomEmployeeForName(name));
        }
        return employees;
    }
    
    // Generates count random employees by reusing names from the loaded file.
    // Names are picked randomly from the same list of applicant names.
    public static List<Employee> generateRandomEmployees(List<String> applicantNames, int count) {
        List<Employee> employees = new ArrayList<>();

        if (applicantNames == null || applicantNames.isEmpty()) {
            System.out.println("Cannot select random employees: no names loaded from file.");
            return employees;
        }

        for (int i = 0; i < count; i++) {
            String randomName = applicantNames.get(RANDOM.nextInt(applicantNames.size()));
            employees.add(createRandomEmployeeForName(randomName));
        }

        return employees;
    }

    private static Employee createRandomEmployeeForName(String name) {
        Manager[] managerTypes = Manager.values();
        Department[] departmentTypes = Department.values();

        Manager randomManager = managerTypes[RANDOM.nextInt(managerTypes.length)];
        Department randomDepartment = departmentTypes[RANDOM.nextInt(departmentTypes.length)];

        return new Employee(name, randomManager, randomDepartment);
    }
}