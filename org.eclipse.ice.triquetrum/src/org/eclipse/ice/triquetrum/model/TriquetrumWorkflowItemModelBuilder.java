package org.eclipse.ice.triquetrum.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.ice.item.model.AbstractModelBuilder;
import org.eclipse.ice.item.Item;
import org.eclipse.ice.item.ItemType;

public class TriquetrumWorkflowItemModelBuilder extends AbstractModelBuilder {

	public TriquetrumWorkflowItemModelBuilder() {
		setName("TriquetrumWorkflowItemModel");
		setType(ItemType.Model);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ice.item.AbstractItemBuilder#getInstance(org.eclipse.core.resources.IProject)
	 */
	@Override
	public Item getInstance(IProject projectSpace) {
		Item item = new TriquetrumWorkflowItemModel(projectSpace);
		item.setItemBuilderName(this.getItemName());
		return item;
	}
}