/*******************************************************************************
 * Copyright (c) 2012, 2014 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - Jay Jay Billings,
 *   Jordan H. Deyton, Dasha Gorin, Alexander J. McCaskey, Taylor Patterson,
 *   Claire Saunders, Matthew Wang, Anna Wojtowicz
 *******************************************************************************/
package org.eclipse.ice.viz.service.jme3.geometry;

import java.io.IOException;

import org.eclipse.ice.viz.service.modeling.ShapeController;

/**
 * <p>
 * Wrapper class for allowing an IShape to be stored in a JME3 Spatial as user
 * data
 * </p>
 * <p>
 * ShapeTransient implements JME3's Savable interface but does not offer the
 * ability to persist the IShape. This class exists solely to avoid IShape
 * implementing Savable.
 * </p>
 * 
 * @author Andrew P. Belt
 */
public class ShapeTransient implements Savable {
	/**
	 * <p>
	 * The associated shape
	 * </p>
	 * 
	 */
	private ShapeController shape;

	/**
	 * <p>
	 * Associates a new ShapeTransient with the given IShape
	 * </p>
	 * 
	 * @param shape
	 *            <p>
	 *            The associated shape
	 *            </p>
	 */
	public ShapeTransient(ShapeController shape) {
		this.shape = shape;
	}

	/**
	 * <p>
	 * Returns the associated shape
	 * </p>
	 * 
	 * @return
	 * 		<p>
	 *         The associated shape
	 *         </p>
	 */
	public ShapeController getShape() {
		return shape;
	}

	/**
	 * Not implemented
	 */
	@Override
	public void read(JmeImporter importer) throws IOException {
		// Do nothing
	}

	/**
	 * Not implemented
	 */
	@Override
	public void write(JmeExporter exporter) throws IOException {
		// Do nothing
	}
}