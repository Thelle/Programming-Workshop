package dk.itu.KF13.TheSim.Game.Model.World.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dk.itu.KF13.TheSim.Game.Model.World.Class.LocEmpty;

public class LocEmptyTest {

	LocEmpty instance;
	@Before
	public void testSetup(){
		instance = new LocEmpty(0, 0,"blob");
	}

	@Test
	public void testGetDescription() {
		assertNotNull(instance.getDescription());
	}

}
