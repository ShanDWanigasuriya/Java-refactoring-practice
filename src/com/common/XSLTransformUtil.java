package com.common;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

public class XSLTransformUtil extends CommonUtil {

    // List to hold the XML data as a list of maps (each map represents an employee)
    private static final ArrayList<Map<String, String>> xmlObjectList = new ArrayList<Map<String, String>>();

    // Map to hold the XPath output for each employee
    private static Map<String, String> xpathOutPutMap = null;

    /**
     * Transforms an XML request using an XSLT stylesheet.
     * 
     * @throws TransformerConfigurationException if there is an error in the configuration of the transformer
     * @throws TransformerException if there is an error during the transformation process
     * @throws TransformerFactoryConfigurationError if there is an error in the transformer factory configuration
     */
    public static void transformRequest() throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
        Source xmlInput = new StreamSource(new File(properties.getProperty(CommonConstants.REQUEST_FILE_PATH)));
        Source xsl = new StreamSource(new File(properties.getProperty(CommonConstants.XSL_FILE_PATH)));
        Result xmlOutPut = new StreamResult(new File(properties.getProperty(CommonConstants.RESPONSE_FILE_PATH)));

        // Perform the transformation of the XML input using the specified XSL stylesheet
        TransformerFactory.newInstance().newTransformer(xsl).transform(xmlInput, xmlOutPut);
    }

    /**
     * Reads the transformed XML response and extracts values using XPath.
     * 
     * @return An ArrayList of Maps, each containing employee data extracted from the XML
     * @throws SAXException if there is an error in parsing the XML
     * @throws IOException if an I/O error occurs
     * @throws ParserConfigurationException if there is a configuration error in the XML parser
     * @throws NumberFormatException if the employee count value cannot be converted to an integer
     * @throws XPathExpressionException if there is an error in evaluating the XPath expression
     */
    public static ArrayList<Map<String, String>> readXMLXpathValues() throws SAXException, IOException, ParserConfigurationException, NumberFormatException, XPathExpressionException {

        // Parse the XML document from the response file path
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(properties.getProperty(CommonConstants.RESPONSE_FILE_PATH));

        // Create an XPath instance to evaluate expressions
        XPath xpath = XPathFactory.newInstance().newXPath();

        // Extract the total number of employee nodes
        int nodeCount = Integer.parseInt(extractXpathValue(doc, xpath, CommonConstants.XPATH_EMPLOYEE_COUNT_KEY, null));

        // Loop through each employee node and extract relevant data
        for (int node = 1; node <= nodeCount; node++) {
            xpathOutPutMap = new HashMap<>();

            xpathOutPutMap.put(CommonConstants.XPATH_EMPLOYEE_ID_KEY, 
                extractXpathValue(doc, xpath, CommonConstants.XPATH_EMPLOYEE_ID_KEY, String.valueOf(node)));

            xpathOutPutMap.put(CommonConstants.XPATH_EMPLOYEE_NAME_KEY, 
                extractXpathValue(doc, xpath, CommonConstants.XPATH_EMPLOYEE_NAME_KEY, String.valueOf(node)));

            xpathOutPutMap.put(CommonConstants.XPATH_EMPLOYEE_ADDRESS_KEY, 
                extractXpathValue(doc, xpath, CommonConstants.XPATH_EMPLOYEE_ADDRESS_KEY, String.valueOf(node)));

            xpathOutPutMap.put(CommonConstants.XPATH_FACULTY_KEY, 
                extractXpathValue(doc, xpath, CommonConstants.XPATH_FACULTY_KEY, String.valueOf(node)));

            xpathOutPutMap.put(CommonConstants.XPATH_DEPARTMENT_KEY, 
                extractXpathValue(doc, xpath, CommonConstants.XPATH_DEPARTMENT_KEY, String.valueOf(node)));

            xpathOutPutMap.put(CommonConstants.XPATH_DESIGNATION_KEY, 
                extractXpathValue(doc, xpath, CommonConstants.XPATH_DESIGNATION_KEY, String.valueOf(node)));

            // Add the extracted data to the list of employee data
            xmlObjectList.add(xpathOutPutMap);
        }
        return xmlObjectList;
    }

    /**
     * Helper method to extract a value from the XML document using XPath.
     * 
     * @param doc The XML document object
     * @param xpath The XPath instance for evaluating expressions
     * @param key The key for the XPath expression from the properties
     * @param xpathNode The specific node index (used for iterating through multiple nodes)
     * @return The string value extracted from the XML document
     * @throws XPathExpressionException if there is an error in evaluating the XPath expression
     */
    private static String extractXpathValue(Document doc, XPath xpath, String key, String xpathNode) throws XPathExpressionException {
        XPathExpression expr = xpath.compile(MessageFormat.format(properties.getProperty(key), xpathNode));
        return (String) expr.evaluate(doc, XPathConstants.STRING);
    }
}
