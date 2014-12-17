package br.ufrj.cos.expline.scicumulus.conversion;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Conversion_reader {
	private final IWriter writer;
	private Document document;
	private final String SOURCE = "source";
	private final String TARGET = "target";
	
	public Conversion_reader()
	{
		writer = null;
	}
	
	public Conversion_reader(IWriter writer)
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
		
		//NodeList childrenList = root.getChildNodes();
		
		/*
		 * Catching Activities 
		 */
		NodeList rootChildrenActivity = root.getElementsByTagName("Activity");
			
		//pegando os activities
		for(int i = 0 ; i < rootChildrenActivity.getLength();i++)
		{
			if(rootChildrenActivity.item(i).getNodeType() == Node.ELEMENT_NODE)
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
				//System.out.println("Input");
				//printNodeList(relationSchemaAttributeInput);
				//System.out.println("---");
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
				NodeList relationSchemaAttributeOutput = null;
				if(arrayOutput.getLength() > 0)
				{
					//System.out.println(arrayOutput.item(0).getNodeName());
					Element elemArrayOutput = (Element)arrayOutput.item(0);
					//printNodeList(elemArrayOutput.getChildNodes());
					
					relationSchemaAttributeOutput = elemArrayOutput.getChildNodes();
					//System.out.println("Output");
					//printNodeList(relationSchemaAttributeOutput);
					//System.out.println("-----");
					/**/
				}
				/* -----------FIM OUTPUT--------- */
				
				
				/* ------------Relation---------- */
				
				
				char[] tempVector = auxElem.getAttribute("value").toCharArray();
				char charAux = tempVector[0];
				String df = new Character(charAux).toString();
				df = df.toUpperCase();
				tempVector[0] = df.charAt(0);
				df = new String(tempVector);
				
				String iModAct = "IMod"+df;
				System.out.println(iModAct);
				String oModAct = "OMod"+df;
				System.out.println(oModAct);
				
				writer.insertInputRelation(iModAct, null, auxElem.getAttribute("value"));
				writer.insertOutputRelation(oModAct, auxElem.getAttribute("value"));
				
				if(i == 0)
				{
					
				}
				else
				{
					
				}
				
				/* --------- Fim Relation ------- */
				
				
				
				/* --------- Colocar Field ------ */
				
				for(int j = 0; j < relationSchemaAttributeInput.getLength(); j++)
				{
					if(relationSchemaAttributeInput.item(j).getNodeType() == Node.ELEMENT_NODE)
					{
						Element elemAux = (Element)relationSchemaAttributeInput.item(j);
						if(relationSchemaAttributeOutput == null)
						{
							writer.insertField(elemAux.getAttribute("name"), elemAux.getAttribute("type"), iModAct, oModAct, auxElem.getAttribute("value"));
						}else
						{
							if(hasInNodeList(relationSchemaAttributeOutput, elemAux))
							{
								writer.insertField(elemAux.getAttribute("name"), elemAux.getAttribute("type"), iModAct, oModAct, auxElem.getAttribute("value"));
							}
							else
							{
								writer.insertField(elemAux.getAttribute("name"), elemAux.getAttribute("type"), iModAct, null, auxElem.getAttribute("value"));
							}
						}
					}
				}
				
				/* -------- FIM colocar Field---- */
				
			}	
		}
		
		
		/*
		 * Catching Edges
		 */
		NodeList rootChildrenEdge = root.getElementsByTagName("Edge");
		
		/* ------INICIO Dependency------ */
		
		for(int i = 0; i < rootChildrenEdge.getLength();i++)
		{
			Element auxElem = (Element)rootChildrenEdge.item(i);
			String source = auxElem.getAttribute(SOURCE);
			String target = auxElem.getAttribute(TARGET);
			
			Element activitySource = getActivity(SOURCE,source,rootChildrenActivity);
			Element activityTarget = getActivity(TARGET,target,rootChildrenActivity);
			
			writer.setDependency(activityTarget.getAttribute("value"), activitySource.getAttribute("value"));
			
			//System.out.println(activitySource.getAttribute("value")+" -> "+activityTarget.getAttribute("value"));
			
		}
				
		/* ------FIM Dependency--------- */
		
	}
	
	private void printNodeList(NodeList nodList)
	{
		for(int i=1;i<nodList.getLength();i+=2)
		{
			System.out.println(nodList.item(i).getNodeName());
		}
	}
	
	private void insertRelation(Element element)
	{
		String IMod1 = "IMod1";
		String name = element.getAttribute("value");
		String OMod1 = "OMod1";
		
		//writer.inser
	}
	
	private boolean hasInNodeList(NodeList list, Element elem)
	{
		String nameAux = elem.getAttribute("name");
		for(int i = 0; i < list.getLength();i++)
		{
			if(list.item(i).getNodeType() == Node.ELEMENT_NODE)
			{
				Element elemTemp = (Element)list.item(i);
				String nameElem = elemTemp.getAttribute("name");
				
				//System.out.println(nameAux + "-->" + nameElem);
				
				if(nameAux.equals(nameElem))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private Element getActivity(String type, String idPort,NodeList rootChildrenActivity)
	{
		Element temp = null;
		
		for(int i = 0 ; i < rootChildrenActivity.getLength();i++)
		{
			if(rootChildrenActivity.item(i).getNodeType() == Node.ELEMENT_NODE)
			{
				Element auxElem = (Element) rootChildrenActivity.item(i);
				
				NodeList ports = auxElem.getElementsByTagName("Ports");
				Element elemPorts = (Element)ports.item(0);
				//printNodeList(elemPorts.getChildNodes());
		
				if(type.equals(TARGET))
				{
					NodeList inputPorts = elemPorts.getElementsByTagName("InputPorts");
					Element elemInputPorts = (Element)inputPorts.item(0);
					//printNodeList(elemInputPorts.getChildNodes());
					
					NodeList portInput = elemInputPorts.getElementsByTagName("Port");
					for(int k = 0;k < portInput.getLength();k++)
					{
						if(portInput.item(k).getNodeType() == Node.ELEMENT_NODE)
						{
							Element elemPortInput = (Element)portInput.item(k);
							String aux = elemPortInput.getAttribute("id");
							if(aux.equals(idPort)) 
							{
								return auxElem;
							}
						}
					}
					Element elemPortInput = (Element)portInput.item(0);
				}
				else if(type.equals(SOURCE))
				{
					NodeList outputPorts = elemPorts.getElementsByTagName("OutputPorts");
					Element elemOutputPorts = (Element)outputPorts.item(0);
					//printNodeList(elemOutputPorts.getChildNodes());
					
					NodeList portOutput = elemOutputPorts.getElementsByTagName("Port");
					for(int k = 0;k < portOutput.getLength();k++)
					{
						if(portOutput.item(k).getNodeType() == Node.ELEMENT_NODE)
						{
							Element elemPortOutput = (Element)portOutput.item(k);
							String aux = elemPortOutput.getAttribute("id");
							if(aux.equals(idPort)) 
							{
								return auxElem;
							}
						}
					}
				}
			}
		}
		
		return temp;
	}
	
}