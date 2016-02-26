package org.eclipse.ice.triquetrum;

import org.eclipse.triquetrum.workflow.WorkflowExecutionService;

public class WorkflowServiceTracker {

	private static WorkflowExecutionService EXECUTIONSERVICE;

	public static void setWorkflowExecutionService(WorkflowExecutionService executionService) {
		System.out.println("setting wf service "+executionService);
		WorkflowServiceTracker.EXECUTIONSERVICE = executionService;
	}

	public static WorkflowExecutionService getWorkflowExecutionService() {
		System.out.println("returning wf service "+WorkflowServiceTracker.EXECUTIONSERVICE);
		return WorkflowServiceTracker.EXECUTIONSERVICE;
	}

}
