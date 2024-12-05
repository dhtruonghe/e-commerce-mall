/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author haitr
 */
public class EmployeeTerritories {
    
    private int employeeID;
    private String territoryID;

    public EmployeeTerritories() {
    }

    public EmployeeTerritories(int employeeID, String territoryID) {
        this.employeeID = employeeID;
        this.territoryID = territoryID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getTerritoryID() {
        return territoryID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setTerritoryID(String territoryID) {
        this.territoryID = territoryID;
    }

    @Override
    public String toString() {
        return "EmployeeTerritories{" + "employeeID=" + employeeID + ", territoryID=" + territoryID + '}';
    }
}
