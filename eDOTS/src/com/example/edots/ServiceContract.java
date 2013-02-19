package com.example.edots;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * This class is responsible for building service contracts
 * for each of the services in the system.
 * 
 * @author Ryan Rybarczyk
 */
public class ServiceContract {
	/**
	 * Builds the XML contract file based upon the 
	 * provided information.
	 * 
	 * @param serviceName
	 * @param type - 0:QoS
	 * @param serviceData
	 */
	public void buildContract(String serviceName, int type, HashMap<String, String> serviceData) {
		String fileName = serviceName;
		try {
			Element contractName = new Element("serviceContract");
			Document doc = new Document(contractName);
			doc.setRootElement(contractName);
			
			if(type == 0) {	
				String value = "";
				Element qosElement = new Element("QoS");
			    
				Collection<String> data = serviceData.keySet();
			   
			    Iterator<String> itr = data.iterator();
			   
			    while(itr.hasNext()) {
			    	value = itr.next().toString();
					qosElement.addContent(new Element(value).setText(serviceData.get(value)));
			    }
				 
				doc.getRootElement().addContent(qosElement);
			}
		 
			XMLOutputter xmlOutput = new XMLOutputter();
		 
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data\\contracts\\" + fileName + ".xml"));
		 
			System.out.println(fileName + " Saved!");
		 } catch (IOException io) {
			 System.err.println(io.getMessage());
		 }
	}
		
	// Test method.
	public static void main(String[] args) {
		ServiceContract example = new ServiceContract();
		HashMap<String, String> sampleData = new HashMap<String, String>();
		
		sampleData.put("frameRate", "30");
		sampleData.put("Resolution", "320x235");
		example.buildContract("Camera03", 0, sampleData);
	}
}
