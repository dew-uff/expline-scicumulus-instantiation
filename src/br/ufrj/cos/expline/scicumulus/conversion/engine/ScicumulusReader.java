package br.ufrj.cos.expline.scicumulus.conversion.engine;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.AbstractWorkflow;
import br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.ExpLineParser;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.SciCumulusParser;
import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.ScicumulusDocument;

public class ScicumulusReader {
	private File abstractWorkflowFile;
	private Document doc;
	private final Logger logger = Logger.getLogger("SciCumulusReader");
	
	public ScicumulusReader(File inputFile) {
		this.abstractWorkflowFile = inputFile;
		
		initComponents ();
			
	}

	private void initComponents() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = factory.newDocumentBuilder();
			this.setDoc(docBuilder.parse(this.abstractWorkflowFile));
			
		} catch (ParserConfigurationException e) {
			this.logger.log(Level.SEVERE, "Can't parser XMLDocument");
		} catch (SAXException | IOException e) {
			this.logger.log(Level.SEVERE, "Generic Problem.");
		}
	}
	
	public ScicumulusDocument Run ()
	{
		AbstractWorkflow explineStructure = createExpLineStructure ();
		
		SciCumulusParser sciParser = new SciCumulusParser (explineStructure);
		ScicumulusDocument sciDoc = sciParser.parser();
		
		return sciDoc;
	}
	
	private AbstractWorkflow createExpLineStructure() {
		Element root = this.doc.getDocumentElement();
		ExpLineParser parser = new ExpLineParser();
		return parser.parseDocument(root);
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
}
