package com.xspeedit.box;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class BoxWorkingSet {

	public static final Comparator<Box> BOX_ITEM_COUNT_ORDER = new BoxItemCountComparator();

	private SortedSet<Box> workingBoxes;

	public BoxWorkingSet(Integer leadingItem, int workingBoxSize) {
		this.workingBoxes = new TreeSet<Box>(BOX_ITEM_COUNT_ORDER);
		Box leadingBox = new Box(workingBoxSize);
		leadingBox.addItem(leadingItem);
		workingBoxes.add(leadingBox);
	}

	public void addItem(Integer item) {
		if (item == null) {
			throw new IllegalArgumentException("Illegal null item.");
		}
		Box newWorkingBox;
		for (Box workingBox : workingBoxes) {
			if (workingBox.acceptItem(item)) {
				newWorkingBox = workingBox;
				newWorkingBox.addItem(item);
				workingBoxes.add(newWorkingBox);
				if (newWorkingBox.isFull()) {
					break;
				}
			}
		}
	}

	public boolean hasFullBox() {
		return this.workingBoxes.last().isFull();
	}

	public Box getElectedBox() {
		return this.workingBoxes.last();
	}

	private static class BoxItemCountComparator implements Comparator<Box> {

		public int compare(Box box1, Box box2) {
			return Integer.compare(box1.getItemsCount(), box2.getItemsCount());
		}

	}

}
