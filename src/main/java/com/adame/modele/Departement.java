package com.adame.modele;

/**
 *
 * @author 1895648
 */
public class Departement {
    private String  departmentId;
    private String  departmentName;
    private boolean current;
    private int     numberEmployees;
    private int     months;
    private Manager manager;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public int getNumberEmployees() {
        return numberEmployees;
    }

    public void setNumberEmployees(int numberEmployees) {
        this.numberEmployees = numberEmployees;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }
    
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Departement{" + "departmentId=" + departmentId + ", departmentName=" + departmentName + ", current=" + current + ", numberEmployees=" + numberEmployees + ", months=" + months + '}';
    }
        
    
}
