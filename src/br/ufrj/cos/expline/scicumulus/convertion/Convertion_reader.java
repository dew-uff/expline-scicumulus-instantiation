package br.ufrj.cos.expline.scicumulus.convertion;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Convertion_reader {
	private final IWriter writer;
	private Document document;
	public Convertion_reader()
	{
		writer = null;
	}
	
	public Convertion_reader(IWriter writer)
	{
		this.writer = writer;
		initConvertion();
	}
	
	private void initConvertion()
	{
		try{
			File explineFile = new File("src/othersource/AbstractWorkflow-ScicumulusExample.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			document = dBuilder.parse(explineFile);
			startReading(document);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void startReading(Document document)
	{
		Element root = document.getDocumentElement();
		NodeList childrenList = root.getChildNodes();
		
		/*for(int i = 0;i<childrenList.getLength();i++)
		{
			if(!childrenList.item(i).getNodeName().equals("#text"))
			{
				System.out.println(childrenList.item(i).getNodeName());
			}
		}*/
		
		/*
		 * Catching Activities 
		 */
		NodeList rootChildrenActivity = root.getElementsByTagName("Activity");
			
		//pegando os activities
		for(int i = 0 ; i < rootChildrenActivity.getLength();i++)
		{
			Element auxElem = (Element) rootChildrenActivity.item(i);
			String value = auxElem.getAttribute("value");
			String algebraicOperator = auxElem.getAttribute("algebraicOperator");
			writer.insertActivity(value, algebraicOperator);
			
			NodeList ports = auxElem.getElementsByTagName("Ports");
			Element elemPorts = (Element)ports.item(0);
			//printNodeList(elemPorts.getChildNodes());
			
			/* -----------INICIO INPUT--------- */
			NodeList inputPorts = elemPorts.getElementsByTagName("InputPorts");
			Element elemInputPorts = (Element)inputPorts.item(0);
			//printNodeList(elemInputPorts.getChildNodes());
			
			NodeList portInput = elemInputPorts.getElementsByTagName("Port");
			Element elemPortInput = (Element)portInput.item(0);
			//printNodeList(elemPortInput.getChildNodes());
			
			NodeList relationSchemaInput = elemPortInput.getElementsByTagName("RelationSchema");
			Element elemRelationSchemaInput = (Element)relationSchemaInput.item(0);
			//printNodeList(elemRelationSchema.getChildNodes());
			
			NodeList arrayInput = elemRelationSchemaInput.getElementsByTagName("Array");
			Element elemArrayInput = (Element)arrayInput.item(0);
			//printNodeList(elemArray.getChildNodes());
			
			NodeList relationSchemaAttributeInput = elemArrayInput.getChildNodes();
			System.out.println("Input");
			printNodeList(relationSchemaAttributeInput);
			System.out.println("---");
			/* -----------FIM INPUT--------- */
			
			
			/* -----------INICIO OUTPUT--------- */
			
			NodeList outputPorts = elemPorts.getElementsByTagName("OutputPorts");
			Element elemOutputPorts = (Element)outputPorts.item(0);
			//printNodeList(elemOutputPorts.getChildNodes());
			
			NodeList portOutput = elemOutputPorts.getElementsByTagName("Port");
			Element elemPortOutput = (Element)portOutput.item(0);
			//printNodeList(elemPortOutput.getChildNodes());
			
			NodeList relationSchemaOutput = elemPortOutput.getElementsByTagName("RelationSchema");
			Element elemRelationSchemaOutput = (Element)relationSchemaOutput.item(0);
			//printNodeList(elemRelationSchemaOutput.getChildNodes());
			
			NodeList arrayOutput = elemRelationSchemaOutput.getElementsByTagName("Array");
			if(arrayOutput.getLength() > 0)
			{
				//System.out.println(arrayOutput.item(0).getNodeName());
				Element elemArrayOutput = (Element)arrayOutput.item(0);
				//printNodeList(elemArrayOutput.getChildNodes());
				
				NodeList relationSchemaAttributeOutput = elemArrayOutput.getChildNodes();
				System.out.println("Output");
				printNodeList(relationSchemaAttributeOutput);
				System.out.println("-----");
				/**/
			}
			
			/* -----------FIM OUTPUT--------- */
			
		}
		/*
		 * Catching Edges
		 */
		NodeList rootChildrenEdge = root.getElementsByTagName("Edge");
		
		for(int i = 0; i < rootChildrenEdge.getLength();i++){
			Element auxElem = (Element)rootChildrenEdge.item(i);
		}
		
	}
	
	private void printNodeList(NodeList nodList)
	{
		for(int i=1;i<nodList.getLength();i+=2)
		{
			System.out.println(nodList.item(i).getNodeName());
		}
	}
	

}
