package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.AbstractWorkflow;

public class SciCumulusParser {
	private AbstractWorkflow absDoc;
	private Map<String, Activity> mapTagActivity = new HashMap<>();
	
	public SciCumulusParser (AbstractWorkflow absDoc) {
		this.absDoc = absDoc;
	}
	
	public ScicumulusDocument parser () {
//		System.out.println(this.absDoc.toString());
		
		ScicumulusDocument sciDoc = new ScicumulusDocument();
		ConceptualWorkflow concepWorkflow = buildConceptualWorkflow ();
		ExecutionWorkflow execWorkflow = buildExecWorkflow(concepWorkflow);
		
		sciDoc.setConceptualWorkflow(concepWorkflow);
		sciDoc.setExecWorkflow(execWorkflow);
		
		return sciDoc;
	}

	private ExecutionWorkflow buildExecWorkflow(ConceptualWorkflow concepWorkflow) {
		List<br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.Port> ports = this.absDoc.getPortsWithoutData();
		
		List<ExecutionWorkflowRelation> ewrList = new ArrayList<>();
		for (br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.Port port : ports) {
			br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.Activity actExpLine = this.absDoc.getMapNamePortActivity().get(port.getName()); 
			Activity sciAct = this.mapTagActivity.get(actExpLine.getValue());
			
			for (Relation rel : sciAct.getRelations()) {
				if (rel.getName().equalsIgnoreCase(port.getName())) {
					ExecutionWorkflowRelation ewr = new ExecutionWorkflowRelation();
					ewr.setAct(sciAct);
					ewr.setRelation(rel);
					ewr.setName(rel.getName());
					
					ewrList.add(ewr);
					break;
				}
			}
		}
		
		
		ExecutionWorkflow ew = new ExecutionWorkflow();
		ew.setExecWorkRelList(ewrList);
		
		return ew;
	}

	private ConceptualWorkflow buildConceptualWorkflow() {
		List<Activity> activities = new ArrayList<>();
		
		
		for (br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.Activity act : this.absDoc.getActivities()) {
			
			Activity activity = new Activity();
			
			activity.setTag(act.getValue());
			activity.setType(act.getAlgebraicOperator());
		
			ArrayList<br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.Port> ports = new ArrayList<>();
			ports.addAll(act.getInputPorts());
			ports.addAll(act.getOutputPorts());
			
			HashMap<String, Field> mapFields = new HashMap<>();
			
			for (br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.Port port : ports) {
				
				Relation rel = new Relation();
				rel.setName(port.getName());
				rel.setRelType(port.getType());
				
				activity.addRelation(rel);
				
				for (br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.Attribute attr : port.getAttributes()) {
					String key = attr.getName() + "-" + attr.getType();
					
					Field field = mapFields.get(key);
					
					
					if (field != null) {
						if (port.getType().equalsIgnoreCase("Input")) {
							field.setInput(rel);
						} else {
							field.setOutput(rel);
						}
					} else {
						field = new Field();
						field.setName(attr.getName());
						field.setType(attr.getType());
						
						if (port.getType().equalsIgnoreCase("Input")) {
							field.setInput(rel);
						} else {
							field.setOutput(rel);
						}
						mapFields.put(key, field);
					}
					
				}
				
				
			}
			
			Collection<Field> fields = mapFields.values();
			
			for (Field f : fields) {
				activity.addField(f);
			}
			
			mapTagActivity.put(activity.getTag(), activity);
			
			activities.add(activity);
			
		}
		
		for (br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.Edge edge : this.absDoc.getEdges()) {
			br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.Activity sourceAct = this.absDoc.getMapNamePortActivity().get(edge.getSource().getName());
			br.ufrj.cos.expline.scicumulus.conversion.model.expLineObjects.Activity targetAct = this.absDoc.getMapNamePortActivity().get(edge.getTarget().getName());
			
			Activity sciActSource = mapTagActivity.get(sourceAct.getValue());
			Activity sciActTarget = mapTagActivity.get(targetAct.getValue());
			
			for (Relation rel : sciActTarget.getRelations()) {
				if (rel.getName().equalsIgnoreCase(edge.getTarget().getName())) {
					rel.setDependencyActivity(sciActSource);
					break;
				}
			}
			
		}
		
		ConceptualWorkflow concepWorkflow = new ConceptualWorkflow();
		concepWorkflow.setActivities(activities);
		
		return concepWorkflow;
	}

	public AbstractWorkflow getSciDoc() {
		return this.absDoc;
	}

	public void setSciDoc(AbstractWorkflow absDoc) {
		this.absDoc = absDoc;
	}
		
}
