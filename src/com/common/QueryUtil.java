package com.common;

import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.TransformerException;
import java.io.File;
import org.xml.sax.SAXException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import javax.xml.transform.TransformerConfigurationException;

public class QueryUtil extends CommonUtil {
	
	public static String queryByID(String id) throws SAXException, IOException, ParserConfigurationException {
		NodeList nodeList; 
		Element element = null;
		/*
		*
		* Read the EmployeeQuery.xml file and read each query node into node
		* list. It refers tag name query
		*/
		
		nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new File(properties.getProperty(CommonConstants.QUERY_XML)))
				.getElementsByTagName(CommonConstants.TAG_NAME);
				
		/*
		*
		* Extract the node from node list using query id. query id is taken from
		* query node attribute
		*/
				
		for (int value = 0; value < nodeList.getLength(); value++) {
			element = (Element) nodeList.item(value);
			if (element.getAttribute(CommonConstants.ATTRIB_ID).equals(id))
				break;
		}
		return element.getTextContent().trim();
	}
}
