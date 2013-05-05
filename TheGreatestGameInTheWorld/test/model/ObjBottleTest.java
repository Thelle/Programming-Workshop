package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle.BottleType;

public class ObjBottleTest {

	ObjBottle instance;
	
	@Test
	public void testGetDescription_MasterBrew() {
		instance = new ObjBottle(true, BottleType.MASTERBREW);
		String description = instance.getDescription();
		assertTrue(description.equalsIgnoreCase("a Masterbrew"));
	}
	
	@Test
	public void testGetDescription_WaterBottle() {
		instance = new ObjBottle(true, BottleType.WATER);
		String description = instance.getDescription();
		assertTrue(description.equalsIgnoreCase("a bottle of water"));
	}

	@Test
	public void testUse_MasterBrew() {
		instance = new ObjBottle(true, BottleType.MASTERBREW);
		int output = instance.use();
		assertTrue(output == -5);
	}
	
	@Test
	public void testUse_WaterBottle() {
		instance = new ObjBottle(true, BottleType.WATER);
		int output = instance.use();
		assertTrue(output == 5);
	}

}
