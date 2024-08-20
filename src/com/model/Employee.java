package com.model;

/**
 * Represents an Employee with details like ID, full name, address, faculty name, department, and designation.
 */
public class Employee {

    // Private fields to store employee details
    private String employeeID;
    private String fullName;
    private String address;
    private String facultyName;
    private String department;
    private String designation;

    /**
     * Gets the employee's ID.
     * 
     * @return employeeID - The ID of the employee.
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the employee's ID.
     * 
     * @param employeeID - The ID to be set for the employee.
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Gets the full name of the employee.
     * 
     * @return fullName - The full name of the employee.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the employee.
     * 
     * @param fullName - The full name to be set for the employee.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the address of the employee.
     * 
     * @return address - The address of the employee.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the employee.
     * 
     * @param address - The address to be set for the employee.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the name of the faculty the employee belongs to.
     * 
     * @return facultyName - The faculty name of the employee.
     */
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * Sets the name of the faculty the employee belongs to.
     * 
     * @param facultyName - The faculty name to be set for the employee.
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    /**
     * Gets the department of the employee.
     * 
     * @return department - The department of the employee.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department of the employee.
     * 
     * @param department - The department to be set for the employee.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Gets the designation of the employee.
     * 
     * @return designation - The designation of the employee.
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the designation of the employee.
     * 
     * @param designation - The designation to be set for the employee.
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Returns a string representation of the Employee object, which includes all employee details.
     * 
     * @return A formatted string with employee details.
     */
    @Override
    public String toString() {
        return "Employee ID = " + employeeID + "\n" 
             + "Full Name = " + fullName + "\n" 
             + "Address = " + address + "\n"
             + "Faculty Name = " + facultyName + "\n" 
             + "Department = " + department + "\n" 
             + "Designation = " + designation;
    }
}
