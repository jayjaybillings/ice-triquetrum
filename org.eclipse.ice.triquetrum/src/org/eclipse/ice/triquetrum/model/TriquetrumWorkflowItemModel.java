package org.eclipse.ice.triquetrum.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.ice.datastructures.ICEObject.Component;
import org.eclipse.ice.datastructures.entry.IEntry;
import org.eclipse.ice.datastructures.entry.StringEntry;
import org.eclipse.ice.datastructures.form.DataComponent;
import org.eclipse.ice.datastructures.form.Form;
import org.eclipse.ice.datastructures.form.FormStatus;
import org.eclipse.ice.io.serializable.IIOService;
import org.eclipse.ice.io.serializable.IOService;
import org.eclipse.ice.item.Item;
import org.eclipse.ice.item.model.Model;

@XmlRootElement(name = "TriquetrumWorkflowItemModel")
public class TriquetrumWorkflowItemModel extends Model {

	public TriquetrumWorkflowItemModel() {
		this(null);
	}

	public TriquetrumWorkflowItemModel(IProject project) {
		// Setup the form and everything
		super(project);
		// TODO: (optional) Add User Code Here
	}

	@Override
	public void setupForm() {
		// TODO: Add User Code Here

		form = new Form();

		// Erwin, you can now setup your Form with information to call
		// Triquetrum. This should go something like...

		// Create a data component
		DataComponent comp = new DataComponent();
		comp.setName("Triquetrum Info");
		comp.setDescription("Information required by Triquetrum");

		// Add an entry
		IEntry entry = new StringEntry();
		entry.setName("Special Triquetrum Parameter");
		entry.setDescription("An important parameter for Triquetrum");

		// Put the entry in the data component
		comp.addEntry(entry);

		// Add the component to the Form
		form.addComponent(comp);

		// The user will be able to change the value of the entry based on what
		// you need. Implement process() below to do some work with Triquetrum
		// once this Item's lifecycle is moved to the Processing phase.

		return;
	}

	@Override
	protected void setupItemInfo() {
		// TODO: Add User Code Here

		// Add descriptive Item information

	}

	@Override
	protected FormStatus reviewEntries(Form preparedForm) {
		// TODO: Add User Code Here

		// Implement detailed entry review here if required

		return null;
	}

	@Override
	public FormStatus process(String actionName) {
		// TODO: Add User Code Here

		// Set the default status value to an error
		FormStatus processStatus = FormStatus.InfoError;

		// Get the data from the user's input into the form. This is a long and
		// dirty way to do it. Ideally, you would just store a class reference
		// to the Entry and grab the value when it comes in.
		String value = ((DataComponent) form.getComponents().get(0)).retrieveEntry("Special Triquetrum Parameter")
				.getValue();

		// DO some work!
		// ... Call Triquetrum!...
		// ... etc!...

		// Make sure that you set the FormStatus to FormStatus.Processed or
		// FormStatus.InfoError if the task you perform is successful.

		// Don't set status until you are finished because it is global and may
		// confuse clients if you set it to InfoError before it is finished with
		// a success.
		status = processStatus;

		return status;
	}

	@Override
	public void loadInput(String name) {

		// Implement this if you want to enable imports for this Item

		// TODO: Add User Code Here
	}
}
