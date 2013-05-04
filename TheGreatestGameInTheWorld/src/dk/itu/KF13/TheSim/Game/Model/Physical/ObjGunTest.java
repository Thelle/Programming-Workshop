package dk.itu.KF13.TheSim.Game.Model.Physical;

import static org.junit.Assert.*;

import org.junit.Test;

public class ObjGunTest {

	@Test
	public void testPutInBackpack() {
		ObjGun instance = new ObjGun(true);
		Backpack backpack = new Backpack();
		assertFalse(instance.putInBackpack(backpack));
	}

	@Test
	public void testGetDescription() {
		ObjGun instance = new ObjGun(true);
		assertNotNull(instance.getDescription());
	}

}
