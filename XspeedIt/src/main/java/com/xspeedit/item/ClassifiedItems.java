package com.xspeedit.item;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The Class ClassifiedItems stores the items by value.
 * Example: If instantiating ClassifiedItems with items {1, 6, 3, 8, 4, 1, 6, 8, 9, 5, 2, 5, 7, 7, 3},
 *   it counts the number of each item and stores the count in the countPerItem map:
 *   
 *   countPerItem.1 = 2
 *   countPerItem.2 = 1
 *   countPerItem.3 = 2
 *   countPerItem.4 = 1
 *   countPerItem.5 = 2
 *   countPerItem.6 = 2
 *   countPerItem.7 = 2
 *   countPerItem.8 = 2
 *   countPerItem.9 = 1
 * 
 * It exposes the method to manipulates the classified items to build the box.
 * 
 * @author bonnardg
 */
public class ClassifiedItems {
	
	/** The count per item map : 
	 *   Key is the item value
	 *   Value is the count of item */
	protected SortedMap<Integer, Integer> countPerItem;

	/**
	 * Instantiates a new classified items.
	 *
	 * @param items the items to classify
	 */
	public ClassifiedItems(List<Integer> items) {
		countPerItem = new TreeMap<>();
		classifyItems(items);
	}
	
	/**
	 * Classify the item in the countPerItem map.
	 *
	 * @param item the item to classify
	 */
	private void classifyItem(Integer item) {
		if (countPerItem.get(item) != null) {
			countPerItem.put(item, countPerItem.get(item) + 1);
		} else {
			countPerItem.put(item, 1);
		}
	}
	
	/**
	 * Classify the items in the countPerItem map.
	 *
	 * @param items the items to classify
	 */
	private void classifyItems(List<Integer> items) {
		if (items == null) {
			throw new IllegalArgumentException("Illegal null items.");
		}
		for (Integer item : items) {
			classifyItem(item);
		}
	}
	
	/**
	 * Checks if there an item of this value in the countPerItem map.
	 *
	 * @param item the item to check
	 * @return true, if there is an item of this value in the countPerItem map.
	 */
	public boolean existItem(Integer item) {
		return countPerItem.get(item) != null;
	}
	
	/**
	 * Removes one of the item in the countPerItem map.
	 *
	 * @param item the item to remove from the map.
	 */
	public void removeItem(Integer item) {
		if (!existItem(item)) {
			throw new IllegalArgumentException(String.format("Illegal unexisting item %d.", item));
		}
		Integer classifiedItem = countPerItem.get(item);
		if (classifiedItem.compareTo(1) == 0) {
			countPerItem.remove(item);
		} else {
			countPerItem.put(item, countPerItem.get(item) - 1);
		}	
	}
	
	/**
	 * Check if there are still items to classify.
	 *
	 * @return true, if there are still items to classify.
	 */
	public boolean existItems() {
		return countPerItem.size() != 0;
	}
	
	/**
	 * Gets the highest item still to classify.
	 *
	 * @return the highest item value to classify.
	 */
	public Integer highestItem() {
		if (countPerItem.isEmpty()) {
			return null;
		}
		return countPerItem.lastKey();
	}
	
	/**
	 * Find nearest lower item from another item.
	 *
	 * @param item the item
	 * @return the nearest lower item from the item passed as parameter.
	 */
	public Integer findNearestLowerItem(Integer item) {
		Integer nearestLowestItem = item - 1;
		while (!existItem(nearestLowestItem) && nearestLowestItem.compareTo(0) > 0) {
			nearestLowestItem -= 1;
		}
		if (nearestLowestItem.compareTo(0) > 0) {
			return nearestLowestItem;
		}
		return null;
	}
	
	/**
	 * Removes the items from the classified items.
	 *
	 * @param items the items
	 */
	public void removeItems(List<Integer> items) {
		if (items == null) {
			throw new IllegalArgumentException("Illegal null items.");
		}
		for (Integer item : items) {
			removeItem(item);
		}
	}

}
