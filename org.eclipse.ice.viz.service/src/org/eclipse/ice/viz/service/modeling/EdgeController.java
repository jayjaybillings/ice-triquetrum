/*******************************************************************************
 * Copyright (c) 2015 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.ice.viz.service.modeling;

import org.eclipse.ice.viz.service.datastructures.VizObject.IManagedVizUpdateable;
import org.eclipse.ice.viz.service.datastructures.VizObject.UpdateableSubscriptionType;

/**
 * A controller for an Edge part.
 * 
 * @author Robert Smith
 *
 */
public class EdgeController extends AbstractController {

	/**
	 * The default constructor.
	 * 
	 * @param model
	 *            The controller's model
	 * @param view
	 *            The controller's view
	 */
	public EdgeController(EdgeMesh model, AbstractView view) {
		super(model, view);
	}

	/**
	 * Gets the location for the edge's first vertex.
	 *
	 * @return A list of the vertex's 3D coordinates
	 */
	public double[] getStartLocation() {
		return ((EdgeMesh) model).getStartLocation();
	}

	/**
	 * Gets the location for the edge's second vertex
	 * 
	 * @return A list of the vertex's 3D coordinates
	 */
	public double[] getEndLocation() {
		return ((EdgeMesh) model).getEndLocation();
	}

	/**
	 * Get the edge's length.
	 * 
	 * @return The edge's length
	 */
	public double getLength() {
		return ((EdgeMesh) model).getLength();
	}

	@Override
	public void update(IManagedVizUpdateable component,
			UpdateableSubscriptionType[] type) {

		// Recalculate the edge's length
		((EdgeMesh) model).calculateLength();

		super.update(component, type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ice.viz.service.modeling.AbstractController#clone()
	 */
	@Override
	public Object clone() {

		// Clone the model and view
		EdgeMesh modelClone = (EdgeMesh) model.clone();
		AbstractView viewClone = (AbstractView) view.clone();

		// Create a new controller for the clones and return it
		EdgeController clone = new EdgeController(modelClone, viewClone);
		return clone;
	}
}