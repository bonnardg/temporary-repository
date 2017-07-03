package com.xspeedit.box;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;


/**
 * A Box can contain multiple items:
 * - the sum of the size of its items can't exceed the box size.
 *
 * @author bonnardg
 */

/**
 * Gets the items.
 *
 * @return the items
 */
@Getter
public class Box {
	
	/** The size of the box. */
	private int size;
	
	/** The items of the box. */
	protected List<Integer> items;
	
	/**
	 * Instantiates a new box given a box size.
	 *
	 * @param size the size
	 */
	public Box(int size) {
		this.size = size;
		this.items = new ArrayList<>();
	}
	
	/**
	 * Instantiates a new box given another box.
	 *
	 * @param box the box
	 */
	public Box(Box box) {
		this.size = box.getSize();
		this.items = new ArrayList<>(box.getItems());
	}
	
	/**
	 * Does the box accept this item? If the item is too large regarding the space left in the box, it does not.
	 *
	 * @param item the item
	 * @return true, if the box has still enough space to contain the item.
	 */
	public boolean acceptItem(Integer item) {
		return getItemsSize() + item <= size;
	}
	
	/**
	 * Is the box full? In that case, the sum of the item size is equal to the box size and it can not contain other items.
	 *
	 * @return true, if the sum of the item size is equal to the box size.
	 */
	public boolean isFull() {
		return getItemsSize() == size;
	}
	
	/**
	 * Gets the count of items inside the box.
	 *
	 * @return the number of items inside the box.
	 */
	public int getItemsCount() {
		return this.items.size();
	}
	
	/**
	 * Adds the item in the box if the box can contain it. Throws an IllegalArgumentException if it can not.
	 *
	 * @param item the item to add in the box
	 */
	public void addItem(Integer item) {
		if (!acceptItem(item)) {
			throw new IllegalArgumentException(String.format("Illegal item %d.", item));
		}
		items.add(item);
	}
	
	/**
	 * Gets the sum of the size of the items.
	 *
	 * @return the sum of the size of the items.
	 */
	public int getItemsSize() {
		int itemsSize = 0;
		for (Integer item : items) {
			itemsSize += item;
		}
		return itemsSize;
	}
	
	/**
	 * Gets the box complement items.
	 *
	 * @return the box complement items
	 */
	public List<Integer> getComplementItems() {
		if (this.items.isEmpty()) {
			return Collections.emptyList();
		}
		List<Integer> complementItems = new ArrayList<>(getItems());
		complementItems.remove(0);
		return complementItems;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Integer item : items) {
			sb.append(item);
		}
		return sb.toString();
	}
		
}
