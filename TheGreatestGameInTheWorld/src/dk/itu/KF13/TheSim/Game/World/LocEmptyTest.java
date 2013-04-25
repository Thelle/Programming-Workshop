package dk.itu.KF13.TheSim.Game.World;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
