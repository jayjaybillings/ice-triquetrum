/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation -
 *   Kasper Gammeltoft
 *******************************************************************************/

package org.eclipse.ice.reflectivity.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ice.datastructures.ICEObject.ListComponent;
import org.eclipse.ice.datastructures.form.DataComponent;
import org.eclipse.ice.reflectivity.MaterialSelection;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptorProvider;

/**
 * Provides custom tabs to the reflectivity model's custom tabbed properties
 * view. The first tab describes the data component and input values for its
 * entries. The second provides an interface for editing the values in the
 * table. Finally, the third provides the output data for the chi squared
 * analysis.
 *
 * @author Kasper Gammeltoft
 *
 */
public class ReflectivityTabDescriptorProvider
		implements ITabDescriptorProvider {

	/**
	 * The tab descriptors.
	 */
	ITabDescriptor[] descriptors;

	/**
	 * The data component that is currently being inspected
	 */
	DataComponent component;

	/**
	 * The output data component that is being displayed
	 */
	DataComponent output;

	/**
	 * The list component for the current reflectivity model table
	 */
	ListComponent listComp;

	/**
	 * The table selection in the model
	 */
	MaterialSelection tableSelection;

	/**
	 * The constructor
	 */
	public ReflectivityTabDescriptorProvider() {
		// Local declarations
		component = null;
		descriptors = new ITabDescriptor[3];
	}

	/**
	 * A filter for filtering the selections received by the tab sections
	 */
	private final IFilter filter = new IFilter() {
		@Override
		public boolean select(Object toTest) {
			return (toTest instanceof DataComponent
					|| toTest instanceof MaterialSelection
					|| toTest instanceof ListComponent);
		}
	};

	/**
	 * Gets the tab descriptors for the three tabs. Creates them if they haven't
	 * been created and fills them with the appropriate tab section.
	 *
	 * @see org.eclipse.ui.views.properties.tabbed.ITabDescriptorProvider#
	 *      getTabDescriptors(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public ITabDescriptor[] getTabDescriptors(IWorkbenchPart part,
			ISelection selection) {

		// Make sure the selection is valid
		if (selection != null && selection instanceof IStructuredSelection) {

			Object[] selectedObjects = ((IStructuredSelection) selection)
					.toArray();
			// If there are objects in the selection
			if (selectedObjects.length >= 4) {
				// Set the data component if it is valid
				Object first = selectedObjects[0];
				if (first instanceof DataComponent) {
					component = (DataComponent) first;
				}
				// Set the output component from the seleciton if it is valid
				Object second = selectedObjects[1];
				if (second instanceof DataComponent) {
					output = (DataComponent) second;
				}

				// Set the list from the selection if it is valid
				Object third = selectedObjects[2];
				if (third instanceof ListComponent) {
					listComp = (ListComponent) third;
				}
				// Set the selection from the table if it is valid
				Object fourth = selectedObjects[3];
				if (fourth instanceof MaterialSelection) {
					tableSelection = (MaterialSelection) fourth;
				}
			}

			// Get a reference to the data component
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			if (obj instanceof DataComponent) {
				component = (DataComponent) obj;
			}

			// Get a reference to the output data component
			Object obj2 = ((IStructuredSelection) selection).toList().get(1);
			if (obj2 instanceof DataComponent) {
				output = (DataComponent) obj2;
			}

			// Create the tab first if it has not been done already
			if (descriptors[0] == null) {

				// New tab, generic and the same for all reflectivity models
				AbstractTabDescriptor inputTabDescriptor = new AbstractTabDescriptor() {

					@Override
					public String getCategory() {
						return "Reflectivity";
					}

					@Override
					public String getId() {
						return "Reflectivity.Input";
					}

					@Override
					public String getLabel() {
						return "Inputs";
					}

				};

				// Set the tab provider
				descriptors[0] = inputTabDescriptor;
			}

			// Create the cell editing tab
			if (descriptors[1] == null) {

				AbstractTabDescriptor editCellTab = new AbstractTabDescriptor() {

					@Override
					public String getCategory() {
						return "Reflectivity";
					}

					@Override
					public String getId() {
						return "Reflectivity.Cell";
					}

					@Override
					public String getLabel() {
						return "Cell Editor";
					}
				};
				// Add the tab to the list of tab descriptors
				descriptors[1] = editCellTab;
			}

			// Create the output tab
			if (descriptors[2] == null) {
				AbstractTabDescriptor outputTab = new AbstractTabDescriptor() {

					@Override
					public String getCategory() {
						return "Reflectivity";
					}

					@Override
					public String getId() {
						return "Reflectivity.Output";
					}

					@Override
					public String getLabel() {
						return "Output";
					}

				};
				// Add the tab to the list of descriptors
				descriptors[2] = outputTab;
			}
			// Get the first tab, the input entries
			final ITabDescriptor tab = descriptors[0];

			// Create a SectionDescriptor for the data component's inputs
			AbstractSectionDescriptor generalSection = new AbstractSectionDescriptor() {

				@Override
				public String getId() {
					return "Input:";
				}

				@Override
				public ISection getSectionClass() {
					ReflectivityDataPropertySection section;
					section = new ReflectivityDataPropertySection();
					section.setDataComponent(component);
					return section;
				}

				@Override
				public String getTargetTab() {
					return tab.getId();
				}

				@Override
				public IFilter getFilter() {
					return filter;
				}

			};

			// Add the section descriptor to the tab
			List<AbstractSectionDescriptor> sectionDescriptors = new ArrayList<AbstractSectionDescriptor>();
			sectionDescriptors.add(generalSection);

			((AbstractTabDescriptor) tab)
					.setSectionDescriptors(sectionDescriptors);

			final ITabDescriptor tab2 = descriptors[1];

			// Create a SectionDescriptor for the data component's inputs
			AbstractSectionDescriptor cellSection = new AbstractSectionDescriptor() {

				@Override
				public String getId() {
					return "Cell:";
				}

				@Override
				public ISection getSectionClass() {
					// Create the cell editor section, and pass in the list
					// component and selection from the table
					ReflectivityCellEditorSection section;
					section = new ReflectivityCellEditorSection();
					section.setMaterialSelection(tableSelection);
					section.setListComponent(listComp);
					return section;

				}

				@Override
				public String getTargetTab() {
					return tab2.getId();
				}

				@Override
				public IFilter getFilter() {
					return filter;
				}

			};

			// Add the section descriptor to the tab
			sectionDescriptors = new ArrayList<AbstractSectionDescriptor>();
			sectionDescriptors.add(cellSection);

			((AbstractTabDescriptor) tab2)
					.setSectionDescriptors(sectionDescriptors);

			final ITabDescriptor tab3 = descriptors[2];

			// Create a SectionDescriptor for the output data component
			AbstractSectionDescriptor outputSection = new AbstractSectionDescriptor() {

				@Override
				public String getId() {
					return "Output:";
				}

				@Override
				public ISection getSectionClass() {
					// Use the data section with the output component.
					ReflectivityDataPropertySection section;
					section = new ReflectivityDataPropertySection();
					section.setDataComponent(output);
					// We do not want the user editing the output, so disable
					// the entries!
					section.setIsEnabled(false);
					return section;
				}

				@Override
				public String getTargetTab() {
					return tab3.getId();
				}

				@Override
				public IFilter getFilter() {
					return filter;
				}

			};

			// Add the section descriptor to the tab
			sectionDescriptors = new ArrayList<AbstractSectionDescriptor>();
			sectionDescriptors.add(outputSection);

			((AbstractTabDescriptor) tab3)
					.setSectionDescriptors(sectionDescriptors);

		}

		// Finally return the descriptors for use with the properties view
		return descriptors;
	}

}
