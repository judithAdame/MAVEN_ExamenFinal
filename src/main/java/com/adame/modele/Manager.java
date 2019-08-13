package com.adame.modele;

import java.util.Date;
import java.util.LinkedHashSet;

/**
 *
 * @author 1895648
 */
public class Manager {
    private String managerNumber;
    private String firstName;
    private String lastName;
    private Date   dateHire;
    private boolean retired;
    private double salary;
    private LinkedHashSet<Departement> departements = new LinkedHashSet(); 

    public String getManagerNumber() {
        return managerNumber;
    }

    public void setManagerNumber(String managerNumber) {
        this.managerNumber = managerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateHire() {
        return dateHire;
    }

    public void setDateHire(Date dateHire) {
        this.dateHire = dateHire;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LinkedHashSet<Departement> getDepartements() {
        return departements;
    }

    public void addDepartement(Departement departement){
        this.departements.add(departement);
    }

    @Override
    public String toString() {
        String str;
        str = "Manager{" + "managerNumber=" + managerNumber + ", firstName=" + firstName + ", lastName=" + lastName + ", dateHire=" + dateHire + ", retired=" + retired + ", salary=" + salary + ", departements {";
        if (departements == null) 
                return str+ '}';
        for (Object d : departements) {
            str+= d.toString();
        }
        return str+ '}';
    }
}
