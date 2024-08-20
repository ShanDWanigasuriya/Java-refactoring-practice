package com.main;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import com.service.AbstractService;
import com.service.EmployeeServiceImpl;

/**
 * The ExecuteMain class serves as the entry point for the application.
 * It initializes the employee service and executes the service template method,
 * which encapsulates the core business logic for managing employees.
 */
public class ExecuteMain {

    /**
     * The main method serves as the starting point of the program execution.
     * It initializes an instance of EmployeeServiceImpl and invokes the serviceTemplate method.
     * 
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        
        // Initialize the employee service by creating an instance of EmployeeServiceImpl
        AbstractService employeeService = new EmployeeServiceImpl();

        // Execute the service template method, which handles the overall service execution
        employeeService.serviceTemplate();
    }

}
