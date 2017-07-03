package com.xspeedit.robot;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.xspeedit.box.Box;

public class SmartRobotTest {

	@Test(expected = IllegalArgumentException.class)
    public void packItems_WhenNullItems_ThenException() throws Exception {
		// Given a smart robot
		SmartRobot robot = new SmartRobot(10);
		
		// When packing null items
		robot.packItems(null);
				
		// Then throws exception
    }
	
	@Test
    public void packItems_WhenMultipleItemsGivenCase_ThenSuccess() throws Exception {
		// Given a smart robot
		SmartRobot robot = new SmartRobot(10);
		
		// When packing items
		List<Box> boxes = robot.packItems(Arrays.asList(new Integer[] {1, 6, 3, 8, 4, 1, 6, 8, 9, 5, 2, 5, 7, 7, 3}));
				
		// Then number of box is optimized
        assertEquals(8, boxes.size());
    }
	
	@Test
    public void packItems_WhenMultipleItemsOtherCase_ThenSuccess() throws Exception {
		// Given a smart robot
		SmartRobot robot = new SmartRobot(10);
		
		// When packing items
		List<Box> boxes = robot.packItems(Arrays.asList(new Integer[] {9, 5, 1, 6, 5, 2, 3, 5, 7, 8, 4, 1, 6, 8, 7, 8, 9, 5, 2, 5, 7, 1, 6, 6, 3, 7, 3}));
		
		// Then number of box is optimized
        assertEquals(16, boxes.size());
    }
	
}
