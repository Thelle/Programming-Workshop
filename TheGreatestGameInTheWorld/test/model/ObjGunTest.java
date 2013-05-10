package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.Backpack;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjGun;
import dk.itu.KF13.TheSim.Game.View.GameView;

public class ObjGunTest {
	
	ObjGun instance;
	
	@Before
	public void setUp() {
		instance = new ObjGun(true, new GameView());
	}

	@Test
	public void testUse() {
		int output = instance.use();
		boolean status = output ==0;
		assertTrue(status);
	}
	
	
	@Test
	public void testPutInBackpack() {
		Backpack backpack = new Backpack();
		boolean status = instance.putInBackpack(backpack);
		assertTrue(status);
	}

	@Test
	public void testGetDescription() {
		assertNotNull(instance.getDescription());
	}

}
