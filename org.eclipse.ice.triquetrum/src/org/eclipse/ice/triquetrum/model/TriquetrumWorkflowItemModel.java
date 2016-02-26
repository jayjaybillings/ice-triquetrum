package org.eclipse.ice.triquetrum.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.core.resources.IProject;
import org.eclipse.ice.datastructures.entry.DiscreteEntry;
import org.eclipse.ice.datastructures.entry.IEntry;
import org.eclipse.ice.datastructures.entry.StringEntry;
import org.eclipse.ice.datastructures.form.DataComponent;
import org.eclipse.ice.datastructures.form.Form;
import org.eclipse.ice.datastructures.form.FormStatus;
import org.eclipse.ice.item.model.Model;
import org.eclipse.ice.triquetrum.WorkflowServiceTracker;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService;
import org.eclipse.triquetrum.workflow.WorkflowExecutionService.StartMode;

import ptolemy.actor.CompositeActor;
import ptolemy.actor.TypedCompositeActor;
import ptolemy.actor.lib.Const;
import ptolemy.actor.lib.gui.Display;
import ptolemy.data.expr.Parameter;
import ptolemy.data.expr.StringParameter;
import ptolemy.domains.sdf.kernel.SDFDirector;

@XmlRootElement(name = "TriquetrumWorkflowItemModel")
public class TriquetrumWorkflowItemModel extends Model {

  private static final String AVAILABLE_WORKFLOWS = "Available workflows";
  private Map<String, CompositeActor> models;
  private DiscreteEntry workflowSelectionEntry;
  private DataComponent workflowCfgComp;

  private String selectedWorkflowName;

  public TriquetrumWorkflowItemModel() {
    this(null);
  }

  public TriquetrumWorkflowItemModel(IProject project) {
    // Setup the form and everything
    super(project);
  }

  @Override
  public void setupForm() {
    if (form == null) {
      allowedActions = new ArrayList<String>();
      allowedActions.add("Launch the Workflow");

      form = new Form();
      form.setActionList(allowedActions);
      models = new HashMap<>();
      try {
        models.put("hello", buildTrivialHelloCompositeActor());
        models.put("goodbye", buildTrivialGoodbyeCompositeActor());
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    // Create a data component for the workflow selection
    DataComponent workflowSelectionComp = new DataComponent();
    // not sure what this ID is for? I can not seem to find getters by ID on
    // the form or other ways in which the ID is used?
    workflowSelectionComp.setId(1);
    workflowSelectionComp.setName("Triquetrum Info");
    workflowSelectionComp.setDescription("Select a Triquetrum workflow from the list");

    workflowSelectionEntry = new DiscreteEntry();
    workflowSelectionEntry.setName(AVAILABLE_WORKFLOWS);
    workflowSelectionEntry.setAllowedValues(new ArrayList<String>(models.keySet()));
    workflowSelectionComp.addEntry(workflowSelectionEntry);
    form.addComponent(workflowSelectionComp);

    // Create a data component for configuring a selected workflow
    workflowCfgComp = new DataComponent();
    workflowCfgComp.setId(2);
    workflowCfgComp.setDescription("Configure the selected workflow");
    form.addComponent(workflowCfgComp);

    return;
  }

  @Override
  public void setupFormWithServices() {
    // TODO use workflow repository service here?
    super.setupFormWithServices();
  }

  @Override
  protected void setupItemInfo() {
    setName("Triquetrum workflow Model");
    setDescription("An item to launch a Triquetrum workflow");
  }

  @Override
  protected FormStatus reviewEntries(Form preparedForm) {
    String newUserSelectedWorkflowName = workflowSelectionEntry.getValue();
    if ((selectedWorkflowName==null) || !selectedWorkflowName.equals(newUserSelectedWorkflowName)) {
      workflowCfgComp.clearEntries();
      CompositeActor selectedWorkflow = models.get(newUserSelectedWorkflowName);
      if (selectedWorkflow != null) {
        for (Parameter p : selectedWorkflow.attributeList(Parameter.class)) {
          StringEntry newEntry = new StringEntry();
          newEntry.setName(p.getName());
          newEntry.setDefaultValue(p.getExpression());
          workflowCfgComp.addEntry(newEntry);
        }
        workflowCfgComp.setName("Workflow : "+newUserSelectedWorkflowName);
        this.selectedWorkflowName = newUserSelectedWorkflowName;
        return FormStatus.ReadyToProcess;
      } else {
        return FormStatus.InfoError;
      }
    } else {
      // the workflow parameterization is optional, so we just check that the cfg panel was shown at least.
      // and if so, we're ready to process!
      return FormStatus.ReadyToProcess;
    }
  }

  @Override
  public FormStatus process(String actionName) {

    // Not sure if this might break something in ICE,
    // but I'd like to make sure that review is always done,
    // also when the user did not save the form.
    // Using getForm generates an NPE here, so let's try with using form directly.
    FormStatus processStatus = reviewEntries(form);

    if (FormStatus.ReadyToProcess.equals(processStatus)) {
      String selectedWorkflowName = workflowSelectionEntry.getValue();

      CompositeActor selectedWorkflow = models.get(selectedWorkflowName);
      try {
        WorkflowExecutionService workflowExecutionService = WorkflowServiceTracker.getWorkflowExecutionService();
        if (workflowExecutionService != null) {
          workflowExecutionService.start(StartMode.RUN, selectedWorkflow, null, getParameterOverrides(), null);
        } else {
          // figure out how to return error msgs to ICE
          // seems there's no FormStatus.ProcessError or so...
          System.err.println("No active WorkflowExecutionService found...");
        }
        status = FormStatus.Processed;
      } catch (Exception e) {
        e.printStackTrace();
        status = FormStatus.Processed;
      }

    } else {
      status = processStatus;
    }
    return status;
  }

  @Override
  public void loadInput(String name) {

    // Implement this if you want to enable imports for this Item

    // TODO: Add User Code Here
  }

  private Map<String, String> getParameterOverrides() {
    Map<String, String> parameterOverrides = new HashMap<>();

    for (IEntry entry : workflowCfgComp.retrieveAllEntries()) {
      if (entry.isModified()) {
        parameterOverrides.put(entry.getName(), entry.getValue());
      }
    }
    return parameterOverrides;
  }

  private CompositeActor buildTrivialHelloCompositeActor() throws Exception {
    CompositeActor compositeActor = new TypedCompositeActor();
    compositeActor.setName("hello");
    compositeActor.setDirector(new SDFDirector(compositeActor, "director"));
    new StringParameter(compositeActor, "helloMsg").setExpression("hello");
    Const source = new Const(compositeActor, "const");
    source.value.setExpression("helloMsg");
    Display sink = new Display(compositeActor, "sink");
    compositeActor.connect(source.output, sink.input);
    return compositeActor;
  }
  private CompositeActor buildTrivialGoodbyeCompositeActor() throws Exception {
    CompositeActor compositeActor = new TypedCompositeActor();
    compositeActor.setName("goodbye");
    compositeActor.setDirector(new SDFDirector(compositeActor, "director"));
    new StringParameter(compositeActor, "goodbyeMsg").setExpression("goodbye");
    Const source = new Const(compositeActor, "const");
    source.value.setExpression("goodbyeMsg");
    Display sink = new Display(compositeActor, "sink");
    compositeActor.connect(source.output, sink.input);
    return compositeActor;
  }
}
