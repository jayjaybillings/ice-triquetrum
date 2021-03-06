/*******************************************************************************
 * Copyright (c) 2014 UT-Battelle, LLC.
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
package org.eclipse.ice.viz.service.mesh.datastructures;

import java.util.ArrayList;

/**
 * <p>
 * This is a 6-sided polygon with 6 edges and 6 vertices.
 * </p>
 * 
 * @author Jordan H. Deyton
 */
public class Hex extends Polygon {

	/**
	 * <p>
	 * A nullary constructor. This creates a Polygon with no vertices or edges
	 * and initializes any fields necessary. Required for persistence.
	 * </p>
	 * 
	 */
	public Hex() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * <p>
	 * The default constructor.
	 * </p>
	 * 
	 * @param edges
	 *            <p>
	 *            A collection of 6 edges connecting 6 vertices.
	 *            </p>
	 * @param vertices
	 *            <p>
	 *            A collection of 6 vertices connecting 6 edges.
	 *            </p>
	 */
	public Hex(ArrayList<Edge> edges, ArrayList<Vertex> vertices) {
		super(edges, vertices);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * This operation returns the hash value of the Hex.
	 * </p>
	 * 
	 * @return <p>
	 *         The hash of the Object.
	 *         </p>
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * <p>
	 * This operation is used to check equality between this Hex and another
	 * Hex. It returns true if the Hexes are equal and false if they are not.
	 * </p>
	 * 
	 * @param otherObject
	 *            <p>
	 *            The other Object that should be compared with this one.
	 *            </p>
	 * @return <p>
	 *         True if the Objects are equal, false otherwise.
	 *         </p>
	 */
	@Override
	public boolean equals(Object otherObject) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * <p>
	 * This operation copies the contents of a Hex into the current object using
	 * a deep copy.
	 * </p>
	 * 
	 * @param polygon
	 *            <p>
	 *            The Object from which the values should be copied.
	 *            </p>
	 */
	public void copy(Hex polygon) {
		// TODO Auto-generated method stub

	}

	/**
	 * <p>
	 * This operation returns a clone of the Hex using a deep copy.
	 * </p>
	 * 
	 * @return <p>
	 *         The new clone.
	 *         </p>
	 */
	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * <p>
	 * This method calls the {@link IMeshPartVisitor}'s visit method.
	 * </p>
	 * 
	 * @param visitor
	 *            <p>
	 *            The {@link IMeshPartVisitor} that is visiting this
	 *            {@link IMeshPart}.
	 *            </p>
	 */
	@Override
	public void acceptMeshVisitor(IMeshPartVisitor visitor) {
		if (visitor != null) {
			visitor.visit(this);
		}
		return;
	}
}