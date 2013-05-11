package controller;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import dk.itu.KF13.TheSim.Game.Controller.BlackJackController;
import dk.itu.KF13.TheSim.Game.Controller.IGameController;

public class BlackJackControllerTest {

	private BlackJackController instance;
	private IGameController controllerMock;
	
	@Before
	public void setUp() {
		//Set up the mock object
		controllerMock = EasyMock.createMock(IGameController.class);
		instance = new BlackJackController(controllerMock, null);
	}

	@Test
	public void testYesNo_y() {
		// Sets up the mock object
		EasyMock.expect(controllerMock.getStringInput()).andReturn("y");
		EasyMock.replay(controllerMock);
		
		String output = instance.getYesNo();
		assertTrue(output.equals("y"));		
	}
	
	@Test
	public void testYesNo_n() {
		// Sets up the mock object
		EasyMock.expect(controllerMock.getStringInput()).andReturn("n");
		EasyMock.replay(controllerMock);
		
		String output = instance.getYesNo();
		assertTrue(output.equals("n"));		
	}
	
	@Test
	public void testTestInput_WrongInput() throws NoSuchMethodException, 
		IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		//Make it possible to test the method through reflection
		Method method = BlackJackController.class.getDeclaredMethod("testUserInput", String.class);
		method.setAccessible(true);
		
		boolean output = (boolean) method.invoke(instance, "Camel");
		assertFalse(output);
	}
	

}
