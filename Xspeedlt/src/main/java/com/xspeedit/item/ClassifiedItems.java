package com.xspeedit.item;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.xspeedit.box.Box;

public class ClassifiedItems {
	
	SortedMap<Integer, Integer> countPerItem;

	public ClassifiedItems(List<Integer> items) {
		countPerItem = new TreeMap<>();
		classifyItems(items);
	}
	
	private void classifyItem(Integer item) {
		if (countPerItem.get(item) != null) {
			countPerItem.put(item, countPerItem.get(item) + 1);
		} else {
			countPerItem.put(item, 1);
		}
	}
	
	private void classifyItems(List<Integer> items) {
		for (Integer item : items) {
			classifyItem(item);
		}
	}
	
	public boolean existItem(Integer item) {
		return countPerItem.get(item) != null;
	}
	
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
	
	public boolean existItems() {
		return countPerItem.size() != 0;
	}
	
	public Integer highestItem() {
		return countPerItem.lastKey();
	}
	
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
	
	public void removeBoxItems(Box box) {
		if (box == null) {
			throw new IllegalArgumentException("Illegal null box.");
		}
		for (Integer boxItem : box.getItems()) {
			removeItem(boxItem);
		}
	}

}
