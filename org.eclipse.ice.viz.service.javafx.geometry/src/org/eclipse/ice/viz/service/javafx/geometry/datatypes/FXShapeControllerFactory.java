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
package org.eclipse.ice.viz.service.javafx.geometry.datatypes;

import org.eclipse.ice.viz.service.modeling.AbstractController;
import org.eclipse.ice.viz.service.modeling.AbstractMesh;
import org.eclipse.ice.viz.service.modeling.IControllerFactory;
import org.eclipse.ice.viz.service.modeling.ShapeMesh;

/**
 * A factory for creating instances of Shape with FXShapeViews for use with
 * JavaFX.
 * 
 * @author Robert Smith
 *
 */
public class FXShapeControllerFactory implements IControllerFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ice.viz.service.modeling.IControllerFactory#createController(
	 * org.eclipse.ice.viz.service.modeling.AbstractMeshComponent)
	 */
	@Override
	public AbstractController createController(AbstractMesh model) {

		// Check that the model is a shape component, if not return null
		if (model instanceof ShapeMesh) {

			// Create an FXShapeView for the model, then wrap them both in a
			// shape
			FXShapeView view = new FXShapeView((ShapeMesh) model);
			return new FXShapeController((ShapeMesh) model, view);
		}
		return null;
	}

}