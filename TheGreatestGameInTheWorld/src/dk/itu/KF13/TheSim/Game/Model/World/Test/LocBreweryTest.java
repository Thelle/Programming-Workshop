package dk.itu.KF13.TheSim.Game.Model.World.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dk.itu.KF13.TheSim.Game.Model.World.Class.LocBrewery;

public class LocBreweryTest {

	LocBrewery instance;
	@Before
	public void testSetup(){
		instance = new LocBrewery(0, 0,"blob");
	}

	@Test
	public void testGetDescription() {
		assertNotNull(instance.getDescription());
	}


}
