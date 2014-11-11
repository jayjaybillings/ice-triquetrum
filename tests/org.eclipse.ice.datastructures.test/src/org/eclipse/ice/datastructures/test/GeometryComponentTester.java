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
package org.eclipse.ice.datastructures.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.eclipse.ice.datastructures.form.geometry.ComplexShape;
import org.eclipse.ice.datastructures.form.geometry.GeometryComponent;
import org.eclipse.ice.datastructures.form.geometry.IShape;
import org.eclipse.ice.datastructures.form.geometry.OperatorType;
import org.eclipse.ice.datastructures.form.geometry.PrimitiveShape;
import org.eclipse.ice.datastructures.form.geometry.ShapeType;
import org.eclipse.ice.datastructures.updateableComposite.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.junit.Test;

/**
 * <!-- begin-UML-doc -->
 * <p>
 * Tests the GeometryComponent class
 * </p>
 * <!-- end-UML-doc -->
 * 
 * @author Jay Jay Billings
 * @generated 
 *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class GeometryComponentTester {
	/**
	 * <!-- begin-UML-doc -->
	 * <p>
	 * Checks whether a ComplexShape and a PrimitiveShape <span
	 * style="font-size:16pt;"></span>can be added, returned, and removed from a
	 * GeometryComponent
	 * </p>
	 * <!-- end-UML-doc -->
	 * 
	 * @generated 
	 *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Test
	public void checkAddShapes() {
		// begin-user-code

		// Instantiate a GeometryComponent
		GeometryComponent geometry = new GeometryComponent();

		// Add a PrimitiveShape
		PrimitiveShape sphere = new PrimitiveShape(ShapeType.Sphere);
		geometry.addShape(sphere);
		assertEquals(1, geometry.getShapes().size());
		assertEquals(sphere, geometry.getShapes().get(0));

		// Add a ComplexShape
		ComplexShape complex = new ComplexShape(OperatorType.Union);
		geometry.addShape(complex);
		assertEquals(2, geometry.getShapes().size());
		assertEquals(complex, geometry.getShapes().get(1));

		// Try adding null
		geometry.addShape(null);
		assertEquals(2, geometry.getShapes().size());

		// Add a shape with an unknown concrete type
		IShape unknownShape = new PrimitiveShape();
		geometry.addShape(unknownShape);
		assertEquals(3, geometry.getShapes().size());
		assertEquals(unknownShape, geometry.getShapes().get(2));

		// Remove the second shape
		geometry.removeShape(complex);
		assertEquals(2, geometry.getShapes().size());

		// Steal the list from the GeometryComponent
		ArrayList<IShape> shapes = geometry.getShapes();
		assertEquals(2, shapes.size());

		// Remove a shape from the stolen list
		shapes.remove(0);
		assertEquals(1, shapes.size());

		// Give it to the GeometryComponent
		geometry.setShapes(shapes);
		assertEquals(1, geometry.getShapes().size());

		// end-user-code
	}

	/**
	 * <!-- begin-UML-doc -->
	 * <p>
	 * This operation checks the shape to ensure that it can be correctly
	 * visited by a realization of the IComponent interface.
	 * </p>
	 * <!-- end-UML-doc -->
	 * 
	 * @generated 
	 *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Test
	public void checkVisitation() {
		// begin-user-code

		// Instantiate TestVisitor
		TestVisitor testVisitor = new TestVisitor();

		// Instantiate TestShape
		Component unknownComponent = new GeometryComponent();

		// Call accept operation
		unknownComponent.accept(testVisitor);

		// Check that testVisitor was visited
		assertTrue(testVisitor.wasVisited());

		// end-user-code
	}

	/**
	 * <!-- begin-UML-doc -->
	 * <p>
	 * This operation checks the ability of the GeometryComponent to update its
	 * Entries.
	 * </p>
	 * <!-- end-UML-doc -->
	 * 
	 * @generated 
	 *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Test
	public void checkUpdate() {
		// begin-user-code
		// Not implemented
		// end-user-code
	}

	/**
	 * <!-- begin-UML-doc -->
	 * <p>
	 * This operation tests the GeometryComponent to ensure that it can properly
	 * dispatch notifications when it receives an update that changes its state.
	 * </p>
	 * <!-- end-UML-doc -->
	 * 
	 * @generated 
	 *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Test
	public void checkNotifications() {
		// begin-user-code

		// Create a new listener and add it to a GeometryComponent
		GeometryComponent geometry = new GeometryComponent();
		TestComponentListener listener = new TestComponentListener();
		geometry.register(listener);

		// Modify geometryComponent's shapes list
		geometry.addShape(new PrimitiveShape(ShapeType.Cube));

		// Check that the listener was notified and reset the listener
		assertTrue(listener.wasNotified());
		listener.reset();

		// Change geometryComponent's Name
		geometry.setName("name");

		// Check that the listener was notified and reset the listener
		assertTrue(listener.wasNotified());
		listener.reset();

		// Set a name
		geometry.setName("name");

		// Check that the listener was notified and reset the listener
		assertTrue(listener.wasNotified());
		listener.reset();

		// Set an ID
		geometry.setId(3);

		// Check that the listener was notified and reset the listener
		assertTrue(listener.wasNotified());
		listener.reset();

		// end-user-code
	}

	/**
	 * <!-- begin-UML-doc -->
	 * <p>
	 * This operation checks the ability of the GeometryComponent to persist
	 * itself to XML and to load itself from an XML input stream.
	 * </p>
	 * <!-- end-UML-doc -->
	 * 
	 * @generated 
	 *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Test
	public void checkLoadingFromXML() {
		// begin-user-code

		// Instantiate a GeometryComponent
		GeometryComponent geometry = new GeometryComponent();
		geometry.addShape(new PrimitiveShape(ShapeType.Sphere));
		geometry.setId(25);
		geometry.setDescription("description");
		geometry.setName("name");

		// Load it into XML
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		geometry.persistToXML(outputStream);

		assertNotNull(outputStream);

		// convert information inside of outputStream to inputStream
		ByteArrayInputStream inputStream = new ByteArrayInputStream(
				outputStream.toByteArray());

		String xmlFile2 = new String(outputStream.toByteArray());
		// System.err.println(xmlFile2);

		// load contents into xml
		GeometryComponent loadGeometry = new GeometryComponent();
		loadGeometry.loadFromXML(inputStream);

		// Check contents
		assertTrue(loadGeometry.equals(geometry));

		// Try to pass null into the operations

		loadGeometry.loadFromXML(null);
		// Nothing happens - check comparison

		// Check contents
		assertTrue(loadGeometry.equals(geometry));

		// Pass a bad file
		String xmlFile = "p98npv597p35tu8mp34958muy3cpt983t,oe";

		inputStream = new ByteArrayInputStream(xmlFile.getBytes());

		// Run operation
		loadGeometry.loadFromXML(inputStream);

		// Check contents
		assertTrue(loadGeometry.equals(geometry));

		// end-user-code
	}

	/**
	 * <!-- begin-UML-doc -->
	 * <p>
	 * This operation checks the GeometryComponent to ensure that its equals()
	 * and hashcode() operations work.
	 * </p>
	 * <!-- end-UML-doc -->
	 * 
	 * @generated 
	 *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Test
	public void checkEquality() {
		// begin-user-code
		// Create GeometryComponent to test
		GeometryComponent component = new GeometryComponent();
		GeometryComponent equalComponent = new GeometryComponent();
		GeometryComponent unEqualComponent = new GeometryComponent();
		GeometryComponent transitiveComponent = new GeometryComponent();

		// Change values
		IShape shape = new PrimitiveShape(ShapeType.Cylinder);
		IShape weirdShape = new ComplexShape(OperatorType.Intersection);

		component.addShape(shape);
		equalComponent.addShape(shape);
		transitiveComponent.addShape(shape);

		unEqualComponent.addShape(weirdShape);

		// Set ICEObject data
		component.setId(1);
		equalComponent.setId(1);
		transitiveComponent.setId(1);
		unEqualComponent.setId(2);

		component.setName("DC 5V");
		equalComponent.setName("DC 5V");
		transitiveComponent.setName("DC 5V");
		unEqualComponent.setName("AC 115V");

		// Assert two equal ComplexShapes return true
		assertTrue(component.equals(equalComponent));

		// Assert two unequal ComplexShapes return false
		assertFalse(component.equals(unEqualComponent));

		// Assert equals() is reflexive
		assertTrue(component.equals(component));

		// Assert the equals() is Symmetric
		assertTrue(component.equals(equalComponent)
				&& equalComponent.equals(component));

		// Assert equals() is transitive
		if (component.equals(equalComponent)
				&& equalComponent.equals(transitiveComponent)) {
			assertTrue(component.equals(transitiveComponent));
		} else {
			fail();
		}

		// Assert equals is consistent
		assertTrue(component.equals(equalComponent)
				&& component.equals(equalComponent)
				&& component.equals(equalComponent));
		assertTrue(!component.equals(unEqualComponent)
				&& !component.equals(unEqualComponent)
				&& !component.equals(unEqualComponent));

		// Assert checking equality with null is false
		assertFalse(component==null);

		// Assert that two equal objects return same hashcode
		assertTrue(component.equals(equalComponent)
				&& component.hashCode() == equalComponent.hashCode());

		// Assert that hashcode is consistent
		assertTrue(component.hashCode() == component.hashCode());

		// Assert that hashcodes from unequal objects are different
		assertTrue(component.hashCode() != unEqualComponent.hashCode());

		// end-user-code
	}

	/**
	 * <!-- begin-UML-doc -->
	 * <p>
	 * This operation tests the construction of the GeometryComponent class and
	 * the functionality inherited from ICEObject.
	 * </p>
	 * <!-- end-UML-doc -->
	 * 
	 * @generated 
	 *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Test
	public void checkCreation() {
		// begin-user-code

		// Create a new GeometryComponent
		GeometryComponent geometry = new GeometryComponent();

		// Check that the shapes list exists but is empty
		assertNotNull(geometry.getShapes());
		assertTrue(geometry.getShapes().isEmpty());

		// end-user-code
	}

	/**
	 * <!-- begin-UML-doc -->
	 * <p>
	 * This operation checks the GeometryComponent to ensure that its copy() and
	 * clone() operations work as specified.
	 * </p>
	 * <!-- end-UML-doc -->
	 * 
	 * @generated 
	 *            "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@Test
	public void checkCopying() {
		// begin-user-code

		GeometryComponent geometry = new GeometryComponent();
		GeometryComponent cloneGeometry;
		GeometryComponent copyGeometry;

		// Set up ICEObject stuff for GeometryComponent
		geometry.setId(25);
		geometry.setDescription("Geometry description");
		geometry.setName("Geometry name");

		// Set up GeometryComponent-specific stuff
		PrimitiveShape sphere1 = new PrimitiveShape(ShapeType.Sphere);
		ComplexShape cube1 = new ComplexShape(OperatorType.Union);

		geometry.addShape(sphere1);
		geometry.addShape(cube1);

		// Clone contents
		cloneGeometry = (GeometryComponent) geometry.clone();

		assertNotNull(cloneGeometry);

		// Check equality of contents
		assertTrue(cloneGeometry.equals(geometry));

		// Copy contents
		copyGeometry = new GeometryComponent();
		copyGeometry.copy(geometry);

		// Check equality of contents
		assertTrue(copyGeometry.equals(geometry));

		// Pass null into copy contents, show nothing has changed
		copyGeometry.copy(null);

		// Check equality of contents
		assertTrue(copyGeometry.equals(geometry));

		// end-user-code
	}
}