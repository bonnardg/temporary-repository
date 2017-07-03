package com.xspeedit.item;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

public class ClassifiedItemsTest {

	@Test
	public void classifyItems_WhenMultipleItems_ThenSuccess() {
		// Given a bunch of items
		List<Integer> items = Arrays.asList(new Integer[] {1, 6, 3, 8, 4, 1, 6, 8, 9, 5, 2, 5, 7, 7, 3});
		
		// When classifying the items
		ClassifiedItems classifiedItems = FakeClassifiedItems.builder().items(items).build();
		
		// Then the items are classified
		SortedMap<Integer, Integer> expectedSortedItems = new TreeMap<>();
		expectedSortedItems.put(1, 2);
		expectedSortedItems.put(2, 1);
		expectedSortedItems.put(3, 2);
		expectedSortedItems.put(4, 1);
		expectedSortedItems.put(5, 2);
		expectedSortedItems.put(6, 2);
		expectedSortedItems.put(7, 2);
		expectedSortedItems.put(8, 2);
		expectedSortedItems.put(9, 1);
		assertEquals(expectedSortedItems, ((FakeClassifiedItems) classifiedItems).getCountPerItem());
	}
	
	@Test
	public void existItem_WhenItemExist_ThenTrue() {
		// Given an item
		List<Integer> items = Arrays.asList(new Integer[] {1});
		
		// When adding the item
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		
		// Then the item exists
		assertEquals(true, classifiedItems.existItem(1));
	}
	
	@Test
	public void existItem_WhenItemDoesNotExist_ThenFalse() {
		// Given another item
		List<Integer> items = Arrays.asList(new Integer[] {1});
		
		// When adding other item
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		
		// Then the item does not exist
		assertEquals(false, classifiedItems.existItem(2));
	}
	
	@Test
	public void existItems_WhenAnItemExist_ThenTrue() {
		// Given an item
		List<Integer> items = Arrays.asList(new Integer[] {1});
		
		// When adding the item
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		
		// Then the an item exists
		assertEquals(true, classifiedItems.existItems());
	}
	
	@Test
	public void existItems_WhenAnItemDoesNotExist_ThenFalse() {
		// Given another item
		List<Integer> items = new ArrayList<>();
		
		// When adding no item
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		
		// Then no item exists
		assertEquals(false, classifiedItems.existItems());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeItem_WhenItemDoesNotExist_ThenException() {
		// Given another item
		List<Integer> items = new ArrayList<>();
		
		// When adding no item and trying to remove an item
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		classifiedItems.removeItem(1);
		
		// Then throws exception
	}
	
	@Test
	public void removeItem_WhenItemExists_ThenSuccess() {
		// Given another item
		List<Integer> items =  Arrays.asList(new Integer[] {1, 1});
		ClassifiedItems classifiedItems = FakeClassifiedItems.builder().items(items).build();
		
		// When removing an item
		classifiedItems.removeItem(1);
		
		// Then the item is removed
		SortedMap<Integer, Integer> expectedSortedItems = new TreeMap<>();
		expectedSortedItems.put(1, 1);
		assertEquals(expectedSortedItems, ((FakeClassifiedItems) classifiedItems).getCountPerItem());
	}

	@Test
	public void removeItem_WhenItemExistsAndLastOne_ThenSuccess() {
		// Given another item
		List<Integer> items =  Arrays.asList(new Integer[] {1});
		ClassifiedItems classifiedItems = FakeClassifiedItems.builder().items(items).build();
		
		// When removing an item
		classifiedItems.removeItem(1);
		
		// Then the item is removed
		SortedMap<Integer, Integer> expectedSortedItems = new TreeMap<>();
		assertEquals(expectedSortedItems, ((FakeClassifiedItems) classifiedItems).getCountPerItem());
	}
	
	@Test
	public void highestItem_WhenItemExists_ThenSuccess() {
		// Given another item
		List<Integer> items =  Arrays.asList(new Integer[] {1, 6, 3, 8, 4, 1, 6, 8, 9, 5, 2, 5, 7, 7, 3});
		
		// When adding the items
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		
		// Then the gives the highest item
		assertEquals(Integer.valueOf(9), classifiedItems.highestItem());
	}
	
	@Test
	public void highestItem_WhenItemDoesNotExist_ThenNull() {
		// Given another item
		List<Integer> items =  new ArrayList<>();
		
		// When adding the items
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		
		// Then the gives null
		assertEquals(null, classifiedItems.highestItem());
	}
	
	@Test
	public void findNearestLowerItem_WhenItemDoesNotExist_ThenNull() {
		// Given another item
		List<Integer> items =  new ArrayList<>();
		
		// When adding the items
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		
		// Then the gives null
		assertEquals(null, classifiedItems.findNearestLowerItem(5));
	}
	
	@Test
	public void findNearestLowerItem_WhenItemExist_ThenSuccess() {
		// Given another item
		List<Integer> items =  Arrays.asList(new Integer[] {1, 6, 3, 8, 1, 6, 8, 9, 5, 2, 5, 7, 7, 3});
		
		// When adding the items
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		
		// Then the gives the nearest lower item
		assertEquals(Integer.valueOf(3), classifiedItems.findNearestLowerItem(5));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeItems_WhenBoxIsNull_ThenException() {
		// Given another item
		List<Integer> items = new ArrayList<>();
		
		// When adding no item and trying to remove a null box
		ClassifiedItems classifiedItems = new ClassifiedItems(items);
		classifiedItems.removeItems(null);
		
		// Then throws exception
	}
	
	@Test
	public void removeItems_WhenItemExists_ThenSuccess() {
		// Given another item and a bunch of existing item
		List<Integer> items =  Arrays.asList(new Integer[] {1, 1, 3, 4});
		ClassifiedItems classifiedItems = FakeClassifiedItems.builder().items(items).build();
		List<Integer> existingItems =  Arrays.asList(new Integer[] {1, 4});
		
		// When removing the box items
		classifiedItems.removeItems(existingItems);
		
		// Then the item are removed
		SortedMap<Integer, Integer> expectedSortedItems = new TreeMap<>();
		expectedSortedItems.put(1, 1);
		expectedSortedItems.put(3, 1);
		assertEquals(expectedSortedItems, ((FakeClassifiedItems) classifiedItems).getCountPerItem());
	}
}
