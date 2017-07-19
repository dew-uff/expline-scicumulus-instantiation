package br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects;

public class ScicumulusDocument {
	private ExecutionWorkflow execWorkflow;
	private Query query;
	private Database database;
	private Workspace workspace;
	private Binary binary;
	private Environment environ;
	private Credentials cred;
	private ConceptualWorkflow concepWorkflow;
	
	public ScicumulusDocument() {
		this.execWorkflow = new ExecutionWorkflow();
		this.query = new Query();
		this.database = new Database();
		this.workspace = new Workspace();
		this.binary = new Binary();
		this.environ = new Environment();
		this.cred = new Credentials();
		this.concepWorkflow = new ConceptualWorkflow();
	}
	
	public ScicumulusDocument(ExecutionWorkflow execWorkflow, Query query, Database database, Workspace workspace,
			Binary binary, Environment environ, Credentials cred, ConceptualWorkflow concepWorkflow) {
		super();
		this.execWorkflow = execWorkflow;
		this.query = query;
		this.database = database;
		this.workspace = workspace;
		this.binary = binary;
		this.environ = environ;
		this.cred = cred;
		this.concepWorkflow = concepWorkflow;
	}

	public ExecutionWorkflow getExecWorkflow() {
		return execWorkflow;
	}

	public void setExecWorkflow(ExecutionWorkflow execWorkflow) {
		this.execWorkflow = execWorkflow;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public Binary getBinary() {
		return binary;
	}

	public void setBinary(Binary binary) {
		this.binary = binary;
	}

	public Environment getEnviron() {
		return environ;
	}

	public void setEnviron(Environment environ) {
		this.environ = environ;
	}

	public Credentials getCred() {
		return cred;
	}

	public void setCred(Credentials cred) {
		this.cred = cred;
	}

	public ConceptualWorkflow getConceptualWorkflow() {
		return concepWorkflow;
	}

	public void setConceptualWorkflow(ConceptualWorkflow concepWorkflow) {
		this.concepWorkflow = concepWorkflow;
	}
	
	
	
}
