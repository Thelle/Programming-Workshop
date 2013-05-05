package dk.itu.KF13.TheSim.Game.Model.Physical.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.Backpack;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjGun;

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
