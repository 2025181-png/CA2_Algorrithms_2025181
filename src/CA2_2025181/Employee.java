/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA2_2025181;

/**
 *
 * @author Khongorzul
 */

public class Employee implements Comparable<Employee> {

    private String fullName;
    private Manager managerType;
    private Department departmentType;

    public Employee(String fullName, Manager managerType, Department departmentType) {
        this.fullName = fullName;
        this.managerType = managerType;
        this.departmentType = departmentType;
    }

    public String getFullName() {
        return fullName;
    }

    public Manager getManagerType() {
        return managerType;
    }

    public Department getDepartmentType() {
        return departmentType;
    }

    public void setManagerType(Manager managerType) {
        this.managerType = managerType;
    }

    public void setDepartmentType(Department departmentType) {
        this.departmentType = departmentType;
    }

    public int compareTo(Employee other) {
        // Compare by full name (case-insensitive) for sorting and searching
        return this.fullName.compareToIgnoreCase(other.fullName);
    }

    public String toString() {
        return fullName + " | " + managerType + " | " + departmentType;
    }
}