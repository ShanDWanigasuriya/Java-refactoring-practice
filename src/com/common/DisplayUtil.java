package com.common;

import java.util.ArrayList;
import com.model.Employee;

/**
 * The DisplayUtil class provides utility methods for displaying employee information in a formatted manner.
 */
public class DisplayUtil extends CommonUtil {

    // Constants for formatting output
    public static final String TAB = "\t";
    public static final String NEW_LINE = "\n";
    public static final String EMPLOYEE_ID = "Employee ID";
    public static final String FULL_NAME = "Full Name";
    public static final String ADDRESS = "Address";
    public static final String FACULTY_NAME = "Faculty Name";
    public static final String DEPARTMENT = "Department";
    public static final String DESIGNATION = "Designation";
    
    /**
     * Formats and displays a list of employees in a tabular format.
     * 
     * @param employeeList The list of Employee objects to be displayed
     */
    public static void formatDisplay(ArrayList<Employee> employeeList) {
        // Print the header for the employee table
        System.out.println(EMPLOYEE_ID + TAB + FULL_NAME + TAB + TAB + ADDRESS + TAB + TAB + FACULTY_NAME + TAB + TAB + DEPARTMENT + TAB +TAB + DESIGNATION + NEW_LINE);
        
        // Print a separator line for better readability
        System.out.println("==============================================================================================");
        
        // Iterate over the employee list and print each employee's details
        for (Employee employee : employeeList) {
            // Print details of each employee in a formatted manner
            System.out.println(employee.getEmployeeID() + TAB + employee.getFullName() + TAB + employee.getAddress() + TAB + employee.getFacultyName() + TAB + employee.getDepartment() + TAB + employee.getDesignation() + NEW_LINE);
            
            // Print a separator line after each employee's details
            System.out.println("-------------------------------------------------------------------------------------------");
        }
    }
}
