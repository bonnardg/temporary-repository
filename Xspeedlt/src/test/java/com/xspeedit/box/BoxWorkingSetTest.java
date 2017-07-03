package com.xspeedit.box;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class BoxWorkingSetTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void addItem_WhenNullItem_ThenException() {
		// Given a box working set with a size 10 and a first item of 9
		BoxWorkingSet boxWorkingSet = new BoxWorkingSet(9, 10);
		
		// When adding null
		boxWorkingSet.addItem(null);
		
		// Then expected Exception
	}
	
	@Test
	public void addItem_WhenFirstItemGivesFullBox_ThenSuccess() {
		// Given a box working set with a size 10 and a first item of 9
		BoxWorkingSet boxWorkingSet = new BoxWorkingSet(9, 10);
		
		// When adding item 1
		boxWorkingSet.addItem(1);
		
		// Then working set has a full box
		assertEquals(true, boxWorkingSet.hasFullBox());
		assertEquals(Arrays.asList(new Integer[] {9, 1}), boxWorkingSet.getElectedBox().getItems());
	}
	
	@Test
	public void addItem_WhenSecondItemGivesFullBox_ThenSuccess() {
		// Given a box working set with a size 10 and a first item of 9
		BoxWorkingSet boxWorkingSet = new BoxWorkingSet(7, 10);
		
		// When adding item 1
		boxWorkingSet.addItem(1);
		boxWorkingSet.addItem(2);
		
		// Then working set has a full box
		assertEquals(true, boxWorkingSet.hasFullBox());
		assertEquals(Arrays.asList(new Integer[] {7, 1, 2}), boxWorkingSet.getElectedBox().getItems());
	}
	
	@Test
	public void addItem_WhenThirdItemGivesFullBox_ThenHigherCountElectedBox() {
		// Given a box working set with a size 10 and a first item of 9
		BoxWorkingSet boxWorkingSet = new BoxWorkingSet(5, 10);
		
		// When adding item 1
		boxWorkingSet.addItem(1);
		boxWorkingSet.addItem(2);
		boxWorkingSet.addItem(3);
		
		// Then working set has a full box
		assertEquals(true, boxWorkingSet.hasFullBox());
		assertEquals(Arrays.asList(new Integer[] {5, 2, 3}), boxWorkingSet.getElectedBox().getItems());
	}
	
	@Test
	public void addItem_WhenThirdItemIsTheLast_ThenHigherCountElectedBox() {
		// Given a box working set with a size 10 and a first item of 9
		BoxWorkingSet boxWorkingSet = new BoxWorkingSet(5, 10);
		
		// When adding item 1
		boxWorkingSet.addItem(3);
		boxWorkingSet.addItem(1);
		boxWorkingSet.addItem(3);
		
		// Then working set has a full box
		assertEquals(false, boxWorkingSet.hasFullBox());
		assertEquals(Arrays.asList(new Integer[] {5, 3, 1}), boxWorkingSet.getElectedBox().getItems());
	}

}
