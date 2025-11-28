/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA2_2025181;

/**
 *
 * @author Khongorzul
 */

// Enum representing different types of managers in a Bank
public enum Manager {
    BRANCH_MANAGER,
    ASSISTANT_MANAGER,
    TEAM_LEAD,
    OPERATIONS_MANAGER;

    // Name for printing in menus
    public String getDisplayName() {
        switch (this) {
            case BRANCH_MANAGER:
                return "Branch Manager";
            case ASSISTANT_MANAGER:
                return "Assistant Manager";
            case TEAM_LEAD:
                return "Team Lead";
            case OPERATIONS_MANAGER:
                return "Operations Manager";
            default:
                return this.name();
        }
    }
}