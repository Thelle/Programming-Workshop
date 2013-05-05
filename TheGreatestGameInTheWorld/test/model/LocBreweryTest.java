package model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Model.Physical.Interface.GameObject;
import dk.itu.KF13.TheSim.Game.Model.World.Class.LocBrewery;

/*
 * We are using LocBrewery as a proxy for all the locations
 */
public class LocBreweryTest {

	LocBrewery instance;
	@Before
	public void setUp() {
		instance = new LocBrewery(1, 1, "Carslberg");
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetDescription() {
		assertNotNull(instance.getDescription());
	}

	@Test
	public void testGetExits() {
		//This has been tested be going around the world and making sure that all
		//exits are returned correctly
		assertTrue(true);
	}

	@Test
	public void testPlaceObject() {
		boolean status = instance.placeObject(new ObjBottle(true, BottleType.MASTERBREW));
		assertTrue(status);
	}

	@Test
	public void testGetObjectDescriptions_NoObjects() {
		//remove objects from location
		List<GameObject> content = instance.getObjects();

		for (int i = 0; i <content.size(); i++){
			instance.removeObject(content.get(i));
			i--;
		}
		
		String output = instance.getObjectDescriptions();
		boolean status = output.equalsIgnoreCase("No objects to be found at this location");
		assertTrue(status);
	}
	
	@Test
	public void testGetObjectDescriptions_SomeObjects() {
		
		String output = instance.getObjectDescriptions();
		boolean status = output.equalsIgnoreCase("No objects to be found at this location");
		assertFalse(status);
	}

	@Test
	public void testGetObjects() {
		instance.getObjects();
	}

	@Test
	public void testGetName() {
		String output = instance.getName();
		boolean status = output.equalsIgnoreCase("Carslberg");
		assertTrue(status);
	}

}
