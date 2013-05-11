package model;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import dk.itu.KF13.TheSim.Game.Model.Physical.Class.ObjBottle;
import dk.itu.KF13.TheSim.Game.Model.World.Class.LocEmpty;

public class LocEmptyTest {

	LocEmpty instance;
	
	@Before
	public void testSetup(){
		instance = new LocEmpty(0, 0,"blob", null, null);
	}

	@Test
	public void testCreateBottleMasterBrew() throws NoSuchMethodException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Using reflection to make it possible to test the private method
		Method method = LocEmpty.class.getDeclaredMethod("createBottle", int.class);
		method.setAccessible(true);
		
		//Test with the minimum and maximum numbers which should return masterbrews
		ObjBottle output = (ObjBottle) method.invoke(instance, 0);		
		assertEquals("a Masterbrew", output.getDescription());
		
		output = (ObjBottle) method.invoke(instance, 4);		
		assertEquals("a Masterbrew", output.getDescription());		
	}
	
	@Test
	public void testCreateBottleWaterBottle() throws NoSuchMethodException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Using reflection to make it possible to test the private method
		Method method = LocEmpty.class.getDeclaredMethod("createBottle", int.class);
		method.setAccessible(true);
		
		//Test with the minimum and maximum numbers which should return bottles of water
		ObjBottle output = (ObjBottle) method.invoke(instance, 5);		
		assertEquals("a bottle of water", output.getDescription());
		
		output = (ObjBottle) method.invoke(instance, 9);		
		assertEquals("a bottle of water", output.getDescription());		
	}
	
	@Test
	public void testCreateBottleNull() throws NoSuchMethodException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Using reflection to make it possible to test the private method
		Method method = LocEmpty.class.getDeclaredMethod("createBottle", int.class);
		method.setAccessible(true);
		
		//Test with the minimum and maximum numbers which should return null
		ObjBottle output = (ObjBottle) method.invoke(instance, 10);		
		assertNull(output);
		
		output = (ObjBottle) method.invoke(instance, 14);		
		assertNull(output);	
	}

}
