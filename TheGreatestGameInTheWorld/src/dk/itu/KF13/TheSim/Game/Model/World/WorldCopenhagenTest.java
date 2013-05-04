package dk.itu.KF13.TheSim.Game.Model.World;

import static org.junit.Assert.*;

import org.junit.Test;

public class WorldCopenhagenTest {

	/**
	 * testGetLocations tests if the method getLocations returns anything.
	 * Assert: getLocations returns not null
	 */
	@Test
	public void testGetLocations() {
		WorldCopenhagen instance = new WorldCopenhagen();
		
		assertNotNull(instance.getLocations());
	}

}
