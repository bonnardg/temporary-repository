package com.xspeedit.box;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;


public class BoxTest {
	

	@Test
    public void acceptItem_WhenOversizedItem_ThenFalse() throws Exception {
        // Given a box with asize 10
		Box box = new Box(10);
        
		// When an oversized item
		Integer item = 11;
		
		// Then the box does not accept the item
        assertEquals(false, box.acceptItem(item));
    }
	
	@Test
    public void acceptItem_WhenUndersizedItem_ThenTrue() throws Exception {
        // Given a box with asize 10
		Box box = new Box(10);
        
		// When an undersized item
		Integer item = 9;
		
		// Then the box accepts the item
        assertEquals(true, box.acceptItem(item));
    }
	
	@Test
    public void acceptItem_WhenExactSizeItem_ThenTrue() throws Exception {
        // Given a box with asize 10
		Box box = new Box(10);
        
		// When an item with the exact same size as the box
		Integer item = 10;
		
		// Then the box accepts the item
        assertEquals(true, box.acceptItem(item));
    }
	
	@Test
    public void getItemsSize_WhenBoxFilled_ThenItemsSize() throws Exception {
		// Given a box with a size 10
		Box box = FakeBox.builder().size(10)
				// When the box has an several items in it
				.items(Arrays.asList(new Integer[] {1, 6, 2}))
				.build();
		
		// Then the box item size is the sum of items
        assertEquals(9, box.getItemsSize());
    }
	
	@Test
    public void getItemsSize_WhenBoxEmpty_ThenZero() throws Exception {
		// Given a box with a size 10
		Box box = FakeBox.builder().size(10)
				// When the box is empty
				.items(new ArrayList<Integer>())
				.build();
		
		// Then the box item count is zero
        assertEquals(0, box.getItemsSize());
    }
	
	@Test
    public void isFull_WhenBoxNotFull_ThenFalse() throws Exception {
		// Given a box with a size 10
		Box box = FakeBox.builder().size(10)
				// When the box has an item size less than the global size
				.items(Arrays.asList(new Integer[] {1, 6, 2}))
				.build();
        
		// Then the box is not full
        assertEquals(false, box.isFull());
    }
	
	@Test
    public void isFull_WhenBoxFull_ThenTrue() throws Exception {
		// Given a box with a size 10
		Box box = FakeBox.builder().size(10)
				// When the box has an item size equals to the global size
				.items(Arrays.asList(new Integer[] {1, 6, 3}))
				.build();
		
		// Then the box is full
        assertEquals(true, box.isFull());
    }
	
	@Test
    public void getItemsCount_WhenBoxFilled_ThenItemsCount() throws Exception {
		// Given a box with a size 10
		Box box = FakeBox.builder().size(10)
				// When the box has an several items in it
				.items(Arrays.asList(new Integer[] {1, 6, 3}))
				.build();
		
		// Then the box item count is the number of items in the box
        assertEquals(3, box.getItemsCount());
    }
	
	@Test
    public void getItemsCount_WhenBoxEmpty_ThenZero() throws Exception {
		// Given a box with a size 10
		Box box = FakeBox.builder().size(10)
				// When the box is empty
				.items(new ArrayList<Integer>())
				.build();
		
		// Then the box item count is zero
        assertEquals(0, box.getItemsCount());
    }
	
	@Test
    public void addItem_WhenBoxEmptyAndUndersizedItem_ThenSuccess() throws Exception {
		// Given a box with a size 10
		Box box = new Box(10);
				
		// When the box is empty, adding an item with a size less than the global size
		box.addItem(9);
		
		// Then the box contains the item, the box item count is 1 and the box item size is the size of the item
		assertEquals(Arrays.asList(new Integer[] {9}), box.getItems());
        assertEquals(1, box.getItemsCount());
        assertEquals(9, box.getItemsSize());
    }
	
	@Test()
    public void addItem_WhenAddingTwoUndersizedItem_ThenSuccess() throws Exception {
		// Given a box with a size 10
		Box box = new Box(10);
				
		// When adding two items with a size less than the global size
		box.addItem(8);
		box.addItem(1);
		
		// Then the box contains the item, the box item count is 2 and the box item size is the sum of the size of the item
		assertEquals(Arrays.asList(new Integer[] {8, 1}), box.getItems());
        assertEquals(2, box.getItemsCount());
        assertEquals(9, box.getItemsSize());
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void addItem_WhenAddingTwoOversizedItem_ThenException() throws Exception {
		// Given a box with a size 10
		Box box = new Box(10);
				
		// When adding two items with a size more than the global size
		box.addItem(8);
		box.addItem(3);
		
		// Then expecting IllegalArgumentException
    }
	
	@Test
    public void getComplementItems_WhenBoxFilledWithMultipleItems_ThenItems() throws Exception {
		// Given a box with a size 10
		Box box = FakeBox.builder().size(10)
				// When the box has an several items in it
				.items(Arrays.asList(new Integer[] {6, 2, 1}))
				.build();
		
		// Then the box item size is the sum of items
        assertEquals(Arrays.asList(new Integer[] {2, 1}), box.getComplementItems());
    }
	
	@Test
    public void getComplementItems_WhenBoxFilledWithOneItem_ThenNoItem() throws Exception {
		// Given a box with a size 10
		Box box = FakeBox.builder().size(10)
				// When the box has an several items in it
				.items(Arrays.asList(new Integer[] {6}))
				.build();
		
		// Then the box item size is the sum of items
        assertEquals(Collections.emptyList(), box.getComplementItems());
    }
	
	@Test
    public void getComplementItems_WhenBoxEmpty_ThenNoItem() throws Exception {
		// Given a box with a size 10
		Box box = FakeBox.builder().size(10)
				// When the box is empty
				.items(new ArrayList<Integer>())
				.build();
		
		// Then the box item count is zero
        assertEquals(Collections.emptyList(), box.getComplementItems());
    }
	
	@Test
    public void toString_WhenBoxEmpty_ThenEmpty() throws Exception {
		// Given a box with a size 10
		Box box = FakeBox.builder().size(10)
				// When the box is empty
				.items(new ArrayList<Integer>())
				.build();
		
		// Then the box item count is zero
        assertEquals("", box.toString());
    }
	
	@Test
    public void toString_WhenItems_ThenConcatenateItems() throws Exception {
		// Given a box with a size 10
		Box box = FakeBox.builder().size(10)
				// When the box has items
				.items(Arrays.asList(new Integer[] {6, 2, 1}))
				.build();
		
		// Then concatenating the items
        assertEquals("621", box.toString());
    }
	
}
