package com.service;

import org.xml.sax.SAXException;
import com.common.CommonConstants;
import com.common.DBConnectionUtil;
import com.common.DisplayUtil;
import com.common.QueryUtil;
import com.common.XSLTransformUtil;
import com.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * This class provides implementation for employee services,
 * including loading employee data from an XML response, creating an employee table,
 * adding employees to the database, and retrieving or removing employee records.
 */
public class EmployeeServiceImpl extends AbstractService {

    // Logger to log messages for this class
    public static final Logger log = Logger.getLogger(EmployeeServiceImpl.class.getName());

    // List to store Employee objects
    private final ArrayList<Employee> employeeList = new ArrayList<>();

    // Database connection and statement objects
    private static Connection connection;
    private static Statement statement;
    private PreparedStatement preparedStatement;

    /**
     * Loads employee data from an XML response and populates the employeeList.
     * Uses XSLTransformUtil to read and parse the XML data.
     */
    @Override
    public void loadEmployeesFormXMLResponse() {
        try {
            // Read employee data from XML and populate the employee list
            List<Map<String, String>> employeeOutputMaps = XSLTransformUtil.readXMLXpathValues();
            for (Map<String, String> employeeOutPutMap : employeeOutputMaps) {
                Employee employee = new Employee();
                employee.setEmployeeID(employeeOutPutMap.get(CommonConstants.XPATH_EMPLOYEE_ID_KEY));
                employee.setFullName(employeeOutPutMap.get(CommonConstants.XPATH_EMPLOYEE_NAME_KEY));
                employee.setAddress(employeeOutPutMap.get(CommonConstants.XPATH_EMPLOYEE_ADDRESS_KEY));
                employee.setFacultyName(employeeOutPutMap.get(CommonConstants.XPATH_FACULTY_KEY));
                employee.setDepartment(employeeOutPutMap.get(CommonConstants.XPATH_DEPARTMENT_KEY));
                employee.setDesignation(employeeOutPutMap.get(CommonConstants.XPATH_DESIGNATION_KEY));
                employeeList.add(employee);
                System.out.println(employee.toString() + "\n");
            }
        } catch (NumberFormatException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        } catch (XPathExpressionException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        } catch (SAXException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        } catch (ParserConfigurationException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * Creates the employee table in the database.
     * Drops the existing table if it exists, then creates a new one using SQL queries.
     */
    @Override
    public void createEmployeeTable() {
        try {
            // Establish database connection and create employee table
            connection = DBConnectionUtil.getDBConnection();
            statement = connection.createStatement();
            statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE));
            statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_TABLE));
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (ClassNotFoundException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (SAXException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (ParserConfigurationException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Adds all employees from the employeeList to the database.
     * Uses batch processing to efficiently insert multiple records.
     */
    @Override
    public void addEmployees() {
        try {
            // Prepare SQL insert statement and set auto-commit to false for batch processing
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_EMPLOYEE));
            connection.setAutoCommit(false);
            for (Employee employee : employeeList) {
                preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employee.getEmployeeID());
                preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, employee.getFullName());
                preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, employee.getAddress());
                preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, employee.getFacultyName());
                preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, employee.getDepartment());
                preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, employee.getDesignation());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (SAXException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (ParserConfigurationException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (NullPointerException e) {
            log.log(Level.SEVERE, e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    /**
     * Retrieves an employee record from the database by employee ID.
     * 
     * @param employeeID - The ID of the employee to retrieve.
     * @return The Employee object corresponding to the given ID.
     */
    @Override
    public Employee getEmployeeByID(String employeeID) {
        return actionOnEmployee(employeeID, CommonConstants.GET_EMPLOYEE).get(0);
    }

    /**
     * Displays all employee records stored in the database.
     * Uses DisplayUtil to format and print the employee data.
     */
    @Override
    public void displayAllEmployees() {
        DisplayUtil.formatDisplay(actionOnEmployee());
    }

    /**
     * Removes an employee record from the database by employee ID.
     * 
     * @param employeeID - The ID of the employee to remove.
     */
    @Override
    public void removeEmployee(String employeeID) {
        actionOnEmployee(employeeID, CommonConstants.DELETE);
    }

    /**
     * Helper method to perform actions on employee records.
     * Can be used to retrieve or delete employees based on provided parameters.
     * 
     * @return An ArrayList of Employee objects.
     */
    private ArrayList<Employee> actionOnEmployee() {
        return this.actionOnEmployee(null, null);
    }

    /**
     * Helper method to perform specific actions (retrieve or delete) on employees
     * based on employee ID and action type.
     * 
     * @param employeeId - The ID of the employee to act on.
     * @param actionType - The type of action (GET_EMPLOYEE or DELETE).
     * @return An ArrayList of Employee objects or null if deleting an employee.
     */
    private ArrayList<Employee> actionOnEmployee(String employeeId, String actionType) {
        ArrayList<Employee> employeeList = new ArrayList<>();
        try {
            connection = DBConnectionUtil.getDBConnection();
            if (actionType != null && actionType.equals(CommonConstants.DELETE)) {
                if (employeeId != null && !employeeId.isEmpty()) {
                    preparedStatement = connection.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_EMPLOYEE));
                    preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employeeId);
                    preparedStatement.executeUpdate();
                    employeeList = null;
                }
            } else {
                if (employeeId != null && !employeeId.isEmpty()) {
                    preparedStatement = connection.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_EMPLOYEE));
                    preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employeeId);
                } else {
                    preparedStatement = connection.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_EMPLOYEE));
                }
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
                    employee.setFullName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
                    employee.setAddress(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
                    employee.setFacultyName(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
                    employee.setDepartment(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
                    employee.setDesignation(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
                    employeeList.add(employee);
                }
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (SAXException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (ParserConfigurationException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (NullPointerException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (ClassNotFoundException e) {
            log.log(Level.SEVERE, e.getMessage());
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.log(Level.SEVERE, e.getMessage());
            }
        }
        return employeeList;
    }
}
