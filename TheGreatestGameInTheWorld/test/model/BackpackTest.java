package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.Backpack;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.HumanPlayer;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle.BottleType;
import dk.itu.KF13.TheSim.Game.Model.Physical.Interface.GameObject;

public class BackpackTest {

	Backpack instance;
	
	@Before
	public void setUp(){
		instance = new Backpack();
	}
	
	@Test
	public void testGetContent() {
		assertNotNull(instance.getContent());
	}
	
	@Test
	public void testRoomForMore_NoObjectsInBackPack() {
		assertTrue(instance.roomForMore());
	}
	
	@Test
	public void testRoomForMore_OneObjectInBackPack(){
		//set up test
		ObjBottle bottle1 = new ObjBottle(true, BottleType.WATER);
		bottle1.putInBackpack(instance);
		
		//Test
		assertTrue(instance.roomForMore());
	}
	
	@Test
	public void testRoomForMore_NoRoomInBackPack(){
		//set up test
		for (int i = 1; i<= 30; i++){
			instance.putInBackpack(new ObjBottle(true, BottleType.MASTERBREW));
		}
		//Test
		assertFalse(instance.roomForMore());
	}
	@Test
	public void testPutInBack_NoObjectsInBackPack(){
		//set up test
		ObjBottle bottle1 = new ObjBottle(true, BottleType.WATER);
				
		//Test
		assertTrue(instance.putInBackpack(bottle1));
	}
	@Test
	public void testPutInBack_NoRoomInBackPack(){
		//set up test
		for (int i = 1; i<= 30; i++){
			instance.putInBackpack(new ObjBottle(true, BottleType.MASTERBREW));
		}
				
		//Test
		assertFalse(instance.putInBackpack(new ObjBottle(true, BottleType.MASTERBREW)));
	}
	
	@Test
	public void testNumberOfSpecificItemsInBackPack_OnlyOneType(){
		//set up test
		for (int i = 1; i<= 3; i++){
			instance.putInBackpack(new ObjBottle(true, BottleType.MASTERBREW));
		}
		int output = instance.numberOfSpecificItemsInBackpack("a Masterbrew"); 
		assertTrue(output == 3);
	}
	
	@Test
	public void testNumberOfSpecificItemsInBackPack_More_Types(){
		//set up test
		for (int i = 1; i<= 3; i++){
			instance.putInBackpack(new ObjBottle(true, BottleType.MASTERBREW));
			instance.putInBackpack(new ObjBottle(true, BottleType.WATER));
		}
		int output = instance.numberOfSpecificItemsInBackpack("a Masterbrew"); 
		assertTrue(output == 3);
		output = instance.numberOfSpecificItemsInBackpack("a bottle of water"); 
		assertTrue(output == 3);
	}
	
	@Test
	public void testRemoveFromBackPack(){
		//This test uses the method numberOfSpecificItemsInBackPack to test
		//if the number of items decreases by one when removeFromBackpack is run
		
		//Set up
		for (int i = 1; i<= 3; i++){
			instance.putInBackpack(new ObjBottle(true, BottleType.MASTERBREW));
			instance.putInBackpack(new ObjBottle(true, BottleType.WATER));
		}
		int output = instance.numberOfSpecificItemsInBackpack("a Masterbrew"); 
		assertTrue(output == 3);
		output = instance.numberOfSpecificItemsInBackpack("a bottle of water"); 
		assertTrue(output == 3);
		
		//Sets the first masterbrew to be removed
		GameObject bottleToBeRemoved = instance.getContent().get(0);
		
		
		//Test
		instance.removeFromBackpack(bottleToBeRemoved);
		output = instance.numberOfSpecificItemsInBackpack("a Masterbrew"); 
		assertTrue(output == 2);
		output = instance.numberOfSpecificItemsInBackpack("a bottle of water"); 
		assertTrue(output == 3);
		
		
	}
	
	
}
