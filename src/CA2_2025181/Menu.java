/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA2_2025181;

/**
 *
 * @author Khongorzul
 */
public enum Menu {
    SORT(1, "Sort Applicants and show first 20"),
    SEARCH(2, "Search employee by name (exact / partial)"),
    ADD_EMPLOYEE(3, "Add new employee"),
    SELECT_RANDOM(4, "Select random employees"),
    EXIT(5, "Exit program");

    private final int code;
    private final String description;

    Menu(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // Convert numeric user input into enum safely
    public static Menu fromCode(int code) {
        for (Menu option : values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
}