package com.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.common.XSLTransformUtil;
import com.model.Employee;

public abstract class AbstractService {

    // Logger to log errors and information
    public static final Logger log = Logger.getLogger(AbstractService.class.getName());

    /**
     * Template method that orchestrates the service workflow.
     * This method follows a specific sequence of operations that subclasses can customize by implementing abstract methods.
     */
    public final void serviceTemplate() {
        try {
            // Transform the request using XSLT before processing
            XSLTransformUtil.transformRequest();

            // Load employees from the transformed XML response
            loadEmployeesFormXMLResponse();

            // Create the employee table in the database
            createEmployeeTable();

            // Add the employees to the database
            addEmployees();

            // Display all employees
            displayAllEmployees();

        } catch (TransformerConfigurationException e) {
            // Log specific configuration errors during transformation
            log.log(Level.SEVERE, "Transformer Configuration Error: " + e.getMessage(), e);
        } catch (TransformerException e) {
            // Log general transformation errors
            log.log(Level.SEVERE, "Transformer Error: " + e.getMessage(), e);
        } catch (TransformerFactoryConfigurationError e) {
            // Log errors related to the factory configuration
            log.log(Level.SEVERE, "Transformer Factory Configuration Error: " + e.getMessage(), e);
        }
    }

    /**
     * Abstract method to be implemented by subclasses to load employees from the XML response.
     */
    public abstract void loadEmployeesFormXMLResponse();

    /**
     * Abstract method to be implemented by subclasses to create the employee table in the database.
     */
    public abstract void createEmployeeTable();

    /**
     * Abstract method to be implemented by subclasses to add employees to the database.
     */
    public abstract void addEmployees();

    /**
     * Abstract method to be implemented by subclasses to retrieve an employee by their ID.
     * @param employeeID The ID of the employee to retrieve.
     * @return The Employee object corresponding to the provided ID.
     */
    public abstract Employee getEmployeeByID(String employeeID);

    /**
     * Abstract method to be implemented by subclasses to display all employees.
     */
    public abstract void displayAllEmployees();

    /**
     * Abstract method to be implemented by subclasses to remove an employee by their ID.
     * @param employeeID The ID of the employee to remove.
     */
    public abstract void removeEmployee(String employeeID);
}
