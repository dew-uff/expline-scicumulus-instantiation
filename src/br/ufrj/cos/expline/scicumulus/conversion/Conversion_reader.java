package br.ufrj.cos.expline.scicumulus.conversion;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.ufrj.cos.expline.scicumulus.conversion.util.Util;

public class Conversion_reader {
	private final IWriter writer;
	private Document document;
	private final String SOURCE = "source";
	private final String TARGET = "target";
	private final File fileToRead;
	private Map<String, String> properties;
	public static ArrayList<String> inputPortsList;
	private static HashMap<String,HashMap<String,List<String>>> activityMap;
	
	public Conversion_reader(File file, Map<String,String> properties)
	{
		this.properties = properties;
		this.fileToRead = file;
		writer = null;
	}
	
	public Conversion_reader(IWriter writer, File file,Map<String,String> properties)
	{
		this.properties = properties;
		this.fileToRead = file;
		this.writer = writer;
		inputPortsList = new ArrayList<>();
		activityMap = new HashMap<String,HashMap<String,List<String>>>();
		initConvertion();
	}
	
	private void initConvertion()
	{
		try{
			File explineFile = fileToRead;
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
		inputPortsList.clear();
		Element root = document.getDocumentElement();
		
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
				properties.put("activity_"+value, "-");
				writer.insertActivity(value, algebraicOperator);
				
				String elemValue = auxElem.getAttribute("value");
				
				NodeList ports = auxElem.getElementsByTagName("Ports");
				Element elemPorts = (Element)ports.item(0);
								
				/* -----------INICIO OUTPUT--------- */
				String oModAct = "OMod_"+elemValue;
//				System.out.println(oModAct);
				NodeList outputPorts = elemPorts.getElementsByTagName("OutputPorts");
				Element elemOutputPorts = (Element)outputPorts.item(0);
				
				NodeList portOutput = elemOutputPorts.getElementsByTagName("Port");
				Element elemPortOutput = (Element)portOutput.item(0);
				oModAct = elemPortOutput.getAttribute("id")+"_"+oModAct;
//				oModAct = oModAct+"_"+elemPortOutput.getAttribute("id");
				
				NodeList relationSchemaOutput = elemPortOutput.getElementsByTagName("RelationSchema");
				Element elemRelationSchemaOutput = (Element)relationSchemaOutput.item(0);
				
				NodeList arrayOutput = elemRelationSchemaOutput.getElementsByTagName("Array");
				NodeList relationSchemaAttributeOutput = null;
				if(arrayOutput.getLength() > 0)
				{
					Element elemArrayOutput = (Element)arrayOutput.item(0);
					
					relationSchemaAttributeOutput = elemArrayOutput.getChildNodes();
				}
//				if(relationSchemaAttributeOutput != null)
//				{
					writer.insertOutputRelation(oModAct, auxElem.getAttribute("value"));
//				}
				/* -----------FIM OUTPUT--------- */
				
				/* -----------INICIO INPUT--------- */
				String iModAct = "IMod_"+elemValue;
//				System.out.println(iModAct);
				NodeList inputPorts = elemPorts.getElementsByTagName("InputPorts");
				Element elemInputPorts = (Element)inputPorts.item(0);
				HashMap<String,List<String>> temp = new HashMap<>();
				
				NodeList portInput = elemInputPorts.getElementsByTagName("Port");
				NodeList relationSchemaAttributeInput = null;
				for(int inputPortCount = 0;inputPortCount < portInput.getLength();inputPortCount++)
				{
					Element elemPortInput = (Element)portInput.item(inputPortCount);
					
					String id = elemPortInput.getAttribute("id");
					
					iModAct = id+"_"+"IMod_"+elemValue;
//					iModAct = "IMod_"+elemValue+"_"+id;
//					System.out.println(iModAct);
					properties.put("rel_"+iModAct, "");
					
					inputPortsList.add(iModAct);
					
					NodeList relationSchemaInput = elemPortInput.getElementsByTagName("RelationSchema");
					Element elemRelationSchemaInput = (Element)relationSchemaInput.item(0);
					
					NodeList arrayInput = elemRelationSchemaInput.getElementsByTagName("Array");
					System.out.println(arrayInput.getLength() + "----->" + iModAct);
				
					if(arrayInput.getLength() > 0)
					{
						Element elemArrayInput = (Element)arrayInput.item(0);
						
						relationSchemaAttributeInput = elemArrayInput.getChildNodes();
						
					}
					
//					if(relationSchemaAttributeInput != null)
//					{
						writer.insertInputRelation(iModAct, null, auxElem.getAttribute("value"));
//					}
					/* --------- Colocar Field ------ */
					if(arrayInput.getLength() > 0) //relationSchemaAttributeInput != null)
					{
						System.out.println("entrou "+iModAct);
						List<String> listOfFields = new ArrayList<>();
//						System.out.println(relationSchemaAttributeInput.getLength() + " length--->"+  iModAct);
						for(int j = 0; j < relationSchemaAttributeInput.getLength(); j++)
						{
//							System.out.println(relationSchemaAttributeInput.item(j).getNodeType() == Node.ELEMENT_NODE?relationSchemaAttributeInput.item(j):"");
							if(relationSchemaAttributeInput.item(j).getNodeType() == Node.ELEMENT_NODE)
							{
								Element elemAux = (Element)relationSchemaAttributeInput.item(j);
//								System.out.println(elemAux);
//								temp.put( elemAux.getAttribute("name"), iModAct); //Insert in the activityMap
								listOfFields.add(elemAux.getAttribute("name"));
//								System.out.println("coloca no temp "+elemAux.getAttribute("name"));
								
								if(relationSchemaAttributeOutput == null)
								{
									writer.insertField(elemAux.getAttribute("name"), elemAux.getAttribute("type"), iModAct, null, auxElem.getAttribute("value"));
								}else
								{
									if(hasInNodeList(relationSchemaAttributeOutput, elemAux))
									{
										writer.insertField(elemAux.getAttribute("name"), elemAux.getAttribute("type"), iModAct, oModAct, auxElem.getAttribute("value"));
									}
									else
									{
//										System.out.println("entrou !!");
										writer.insertField(elemAux.getAttribute("name"), elemAux.getAttribute("type"), iModAct, null, auxElem.getAttribute("value"));
									}
								}
								
								temp.put(iModAct, listOfFields);
							}
						}
					}else{
//						writer.insertField("", "", iModAct, oModAct, auxElem.getAttribute("value"));
					}
					/* -------- FIM colocar Field---- */
					activityMap.put(auxElem.getAttribute("value"), temp);
				}
				/* -----------FIM INPUT--------- */
				
				
				/* ------------Relation---------- */
//				char[] tempVector = auxElem.getAttribute("value").toCharArray();
//				char charAux = tempVector[0];
//				String elemValue = new Character(charAux).toString();
//				elemValue = elemValue.toUpperCase();
//				tempVector[0] = elemValue.charAt(0);
//				elemValue = new String(tempVector);
				/* --------- Fim Relation ------- */
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
			
//			if(inputPortsList.contains(target))
//				inputPortsList.remove(target);
			
			ArrayList<String> etmep = new ArrayList<>();
			
			for(String str:inputPortsList)
			{
//				System.out.println("________________");
//				System.out.println(str);
				if(Util.getId(str).equals(target))
				{
					etmep.add(str);
					//inputPortsList.remove(str);
				}
			}
			
			for(String str:etmep)
			{
				inputPortsList.remove(str);
			}
			
			Element activitySource = getActivity(SOURCE,source,rootChildrenActivity);
			Element activityTarget = getActivity(TARGET,target,rootChildrenActivity);
			
			String relationName = target+"_"+"IMod_"+activityTarget.getAttribute("value");
//			System.out.println(relationName);
			
//			writer.setDependency(activityTarget.getAttribute("value"), activitySource.getAttribute("value"));
			writer.setDependency(activityTarget.getAttribute("value"), activitySource.getAttribute("value"),relationName);
		}
				
		/* ------FIM Dependency--------- */
		
		/* ------INICIO PErcorrimento -- */
		for(String aux:inputPortsList)
		{
//			System.out.println(aux);
			this.properties.put("EMPTYDOORS_"+aux, "");
		}
		/* ------Fim PErcorrimento ----- */
		
	}
	
	public static HashMap<String,HashMap<String,List<String>>> getActivityMap()
	{
		return activityMap;
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
