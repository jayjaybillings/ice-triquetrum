package org.eclipse.ice.triquetrum.launcher;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.core.resources.IProject;

import org.eclipse.ice.item.jobLauncher.JobLauncher;
import org.eclipse.ice.datastructures.form.Form;
import org.eclipse.ice.datastructures.form.FormStatus;
import org.eclipse.ice.io.serializable.IIOService;

@XmlRootElement(name = "TriquetrumWorkflowItemLauncher")
public class TriquetrumWorkflowItemLauncher extends JobLauncher {

	private String fullExecCMD;

	private IIOService ioService;

	public TriquetrumWorkflowItemLauncher() {
		this(null);
	}

	public TriquetrumWorkflowItemLauncher(IProject project) {

		// Call the JobLauncher constructor
		super(project);

		// TODO: Add User Code Here
	}

	@Override
	protected void setupItemInfo() {
		// TODO: Add User Code Here
	}

	@Override
	public void setupForm() {
		form = new Form();
		// TODO: Add User Code Here
	}

	@Override
	public FormStatus process(String actionName) {
		// TODO: Add User Code Here
		return null;
	}
}