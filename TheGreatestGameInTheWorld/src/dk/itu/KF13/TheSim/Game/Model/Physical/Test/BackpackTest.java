package dk.itu.KF13.TheSim.Game.Model.Physical.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.Backpack;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle.BottleType;

public class BackpackTest {

	@Test
	public void testGetContent() {
		Backpack instance = new Backpack();
		assertNotNull(instance.getContent());
	}
	
	@Test
	public void testRoomForMore_NoObjectsInBackPack() {
		Backpack instance = new Backpack();
		assertTrue(instance.roomForMore());
	}
	
	@Test
	public void testRoomForMore_OneObjectInBackPack(){
		//set up test
		Backpack instance = new Backpack();
		ObjBottle bottle1 = new ObjBottle(true, BottleType.WATER);
		bottle1.putInBackpack(instance);
		
		//Test
		assertTrue(instance.roomForMore());
	}
	
	@Test
	public void testRoomForMore_NoRoomInBackPack(){
		//set up test
		Backpack instance = new Backpack();
		ObjBottle bottle1 = new ObjBottle(true, BottleType.WATER);
		ObjBottle bottle2 = new ObjBottle(true, BottleType.WATER);
		bottle1.putInBackpack(instance);
		bottle2.putInBackpack(instance);
		
		//Test
		assertFalse(instance.roomForMore());
	}
	@Test
	public void testPutInBack_NoObjectsInBackPack(){
		//set up test
		Backpack instance = new Backpack();
		ObjBottle bottle1 = new ObjBottle(true, BottleType.WATER);
				
		//Test
		assertTrue(instance.putInBackpack(bottle1));
	}
	@Test
	public void testPutInBack_NoRoomInBackPack(){
		//set up test
		Backpack instance = new Backpack();
		ObjBottle bottle1 = new ObjBottle(true, BottleType.WATER);
		ObjBottle bottle2 = new ObjBottle(true, BottleType.WATER);
		ObjBottle bottle3 = new ObjBottle(true, BottleType.WATER);
		bottle1.putInBackpack(instance);
		bottle2.putInBackpack(instance);
				
		//Test
		assertFalse(instance.putInBackpack(bottle3));
	}
}
