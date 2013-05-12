package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.HumanPlayer;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Model.World.Class.LocBrewery;
import dk.itu.KF13.TheSim.Game.Model.World.Class.LocCityHall;
import dk.itu.KF13.TheSim.Game.View.GameView;

public class HumanPlayerTest {
	
	HumanPlayer player;
	@Before
	public void setUp() {
		player = new HumanPlayer(new GameView());
	}

	@Test
	public void testGetLocation() {
		//Set up
		LocBrewery brewery = new LocBrewery(1, 1, "Carlsberg", null, null);
		player.setLocation(brewery);
		
		assertTrue(player.getLocation() == brewery);
	}

	@Test
	public void testSetLocation() {
		LocBrewery brewery = new LocBrewery(1, 1, "Carlsberg", null, null);
		assertTrue(player.setLocation(brewery));
	}

	@Test
	public void testTakeObjectGameObject_Takeable() {
		//set up test
		LocCityHall location = new LocCityHall(1, 1, "blah", null, null);
		player.setLocation(location);
		ObjBottle bottle1 = new ObjBottle(true, BottleType.WATER);
		
		//Test
		boolean status = player.takeObject(bottle1);
		assertTrue(status);		
	}
	
	@Test
	public void testTakeObjectGameObject_NonTakeable() {
		//set up test
		LocBrewery location = new LocBrewery(1, 1, "blah", null, null);
		player.setLocation(location);
		ObjBottle bottle1 = new ObjBottle(false, BottleType.WATER);
		//Test
		boolean status = player.takeObject(bottle1);
		assertFalse(status);		
	}
	
	@Test
	public void testTakeObjectString() {
		//set up test
		LocCityHall location = new LocCityHall(1, 1, "blah", null, null);
		player.setLocation(location);
				
		//Test
		boolean status = player.takeObject("take bottle of water");
		assertTrue(status);
	}

	@Test
	public void testGetAlcoholLevel() {
		assertTrue(player.getAlcoholLevel() == 4);
	}

	@Test
	public void testChangeAlcoholLevel() {
		assertTrue(player.getAlcoholLevel() == 4);
		player.changeAlcoholLevel(1);
		assertTrue(player.getAlcoholLevel() == 5);
	}

	@Test
	public void testReturnContentOfBackpack_Nothing() {
		assertNotNull(player.returnContentOfBackpack());
	}

	
}
