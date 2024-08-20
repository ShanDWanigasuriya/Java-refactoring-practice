package com.common;

public class CommonConstants {

    /* Constant for the key used in config.properties file to retrieve the query file path */
    public static final String QUERY_XML = "QueryFilePath";
    
    /* Path to the properties configuration file */
    public static final String PROPERTY_FILE = "../config/config.properties";
    
    /* Tag name for XML queries */
    public static final String TAG_NAME = "query";
    
    /* Attribute name for query ID in XML */
    public static final String ATTRIB_ID = "id";
    
    /* Separator used in various string operations */
    public static final String COMMA = ",";
    
    /* Property key for the database URL */
    public static final String URL = "url";
    
    /* Property key for the database username */
    public static final String USERNAME = "username";
    
    /* Property key for the database password */
    public static final String PASSWORD = "password";
    
    /* Property key for the JDBC driver name */
    public static final String DRIVER_NAME = "driverName";
    
    /* Query ID for dropping a table */
    public static final String QUERY_ID_DROP_TABLE = "drop_table";
    
    /* Query ID for creating the employee table */
    public static final String QUERY_ID_CREATE_TABLE = "create_employee_table";
    
    /* Query ID for inserting an employee */
    public static final String QUERY_ID_INSERT_EMPLOYEE = "insert_employee";
    
    /* Query ID for retrieving an employee by ID */
    public static final String QUERY_ID_GET_EMPLOYEE = "employee_by_id";
    
    /* Query ID for retrieving all employees */
    public static final String QUERY_ID_ALL_EMPLOYEE = "all_employees";
    
    /* Query ID for removing an employee */
    public static final String QUERY_ID_REMOVE_EMPLOYEE = "remove_employee";
    
    /* Config key for the XSL file path */
    public static final String XSL_FILE_PATH = "XSLFilePath";
    
    /* Config key for the request XML file path */
    public static final String REQUEST_FILE_PATH = "RequestFilePath";
    
    /* Config key for the response XML file path */
    public static final String RESPONSE_FILE_PATH = "ResponseFilePath";
    
    /* XPath key for counting the number of employees */
    public static final String XPATH_EMPLOYEE_COUNT_KEY = "XpathEmployeeCount";
    
    /* XPath key for retrieving the employee ID */
    public static final String XPATH_EMPLOYEE_ID_KEY = "XpathEmployeeIDKey";
    
    /* XPath key for retrieving the employee's full name */
    public static final String XPATH_EMPLOYEE_NAME_KEY = "XpathEmployeeNameKey";
    
    /* XPath key for retrieving the employee's address */
    public static final String XPATH_EMPLOYEE_ADDRESS_KEY = "XpathEmployeeAddressKey";
    
    /* XPath key for retrieving the employee's faculty name */
    public static final String XPATH_FACULTY_KEY = "XpathFacultyNameKey";
    
    /* XPath key for retrieving the employee's department */
    public static final String XPATH_DEPARTMENT_KEY = "XpathDepartmentKey";
    
    /* XPath key for retrieving the employee's designation */
    public static final String XPATH_DESIGNATION_KEY = "XpathDesignationKey";
    
    /* Action constant for delete operations */
    public static final String DELETE = "Delete";
    
    /* Action constant for retrieving an employee */
    public static final String GET_EMPLOYEE = "GetEmployee";
    
    /* Column index for the first column in database operations */
    public static final int COLUMN_INDEX_ONE = 1;
    
    /* Column index for the second column in database operations */
    public static final int COLUMN_INDEX_TWO = 2;
    
    /* Column index for the third column in database operations */
    public static final int COLUMN_INDEX_THREE = 3;
    
    /* Column index for the fourth column in database operations */
    public static final int COLUMN_INDEX_FOUR = 4;
    
    /* Column index for the fifth column in database operations */
    public static final int COLUMN_INDEX_FIVE = 5;
    
    /* Column index for the sixth column in database operations */
    public static final int COLUMN_INDEX_SIX = 6;

}
