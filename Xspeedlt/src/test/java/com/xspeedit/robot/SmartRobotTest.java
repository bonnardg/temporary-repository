package com.xspeedit.robot;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.xspeedit.box.Box;

public class SmartRobotTest {

	@Test
    public void test_pack_items() throws Exception {
        SmartRobot smartRobot = new SmartRobot(10);
        List<Box> boxes = smartRobot.packItems(Arrays.asList(new Integer[] {1, 6, 3, 8, 4, 1, 6, 8, 9, 5, 2, 5, 7, 7, 3}));
        assertEquals(8, boxes.size());
    }
	
}
