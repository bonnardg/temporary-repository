package com.xspeedit.box;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The Class BoxWorkingSet is used by a Robot to test combination of items and find the optimised one.
 * 
 * When adding item to a BoxWorkingSet, the new item will be added to each eligible (having enough space) box
 * from the set. 
 * The new created boxes will be sorted by item count and size inside the workingBoxes set. 
 * Example:
 *   - Instantiating the BoxWorkingSet with item 6:
 *     workingBoxes(0).items = [6]
 *   - Adding item 3
 *     workingBoxes(0).items = [6]
 *     workingBoxes(0).items = [6,3]
 *   - Adding item 2
 *     workingBoxes(0).items = [6]
 *     workingBoxes(0).items = [6,2]
 *     workingBoxes(0).items = [6,3]
 *     --> Item 2 can't be added to Box [6,3] as it exceeds the box size
 *     --> Box [6,2] will be stored before box [6,3] as the last one is using the more space in the box.
 *   - Adding item 1
 *     workingBoxes(0).items = [6]
 *     workingBoxes(0).items = [6,2]
 *     workingBoxes(0).items = [6,3]   
 *     workingBoxes(0).items = [6,2,1]   
 *     workingBoxes(0).items = [6,3,1]   
 * 
 * The elected box will be the box:
 *   - That is Full / with no space left
 *   - Otherwise, that has the larger number of item
 *   - If two boxes have the same number of items, the elected one will be the one with the less space left.
 * 
 * @author bonnardg
 */
public class BoxWorkingSet {

	/** The Constant BOX_ITEM_COUNT_ORDER. */
	public static final Comparator<Box> BOX_ITEM_COUNT_ORDER = new BoxItemCountComparator();

	/** The working boxes containing a set of box with its combination. */
	private SortedSet<Box> workingBoxes;

	/**
	 * Instantiates a new box working set.
	 *
	 * @param leadingItem the first itemof the boxes
	 * @param workingBoxSize the boxes size
	 */
	public BoxWorkingSet(Integer leadingItem, int workingBoxSize) {
		// The set of boxes will be automatically stored with the BoxItemCountComparator
		this.workingBoxes = new TreeSet<>(BOX_ITEM_COUNT_ORDER);
		Box leadingBox = new Box(workingBoxSize);
		leadingBox.addItem(leadingItem);
		workingBoxes.add(leadingBox);
	}

	/**
	 * Adding the item to each working boxes if it accepts the item.
	 * If it builds a full box, it stops to add the item to the other boxes.
	 *
	 * @param item the item to add in the boxes
	 */
	public void addItem(Integer item) {
		if (item == null) {
			throw new IllegalArgumentException("Illegal null item.");
		}
		Box newWorkingBox;
		SortedSet<Box> initialWorkingBoxes = new TreeSet<>(this.workingBoxes); 
		for (Box workingBox : initialWorkingBoxes) {
			if (workingBox.acceptItem(item)) {
				newWorkingBox = new Box(workingBox);
				newWorkingBox.addItem(item);
				workingBoxes.add(newWorkingBox);
				if (newWorkingBox.isFull()) {
					break;
				}
			}
		}
	}

	/**
	 * Checks if the last working box is a full box.
	 *
	 * @return true, if the last box is a full box.
	 */
	public boolean hasFullBox() {
		return this.workingBoxes.last().isFull();
	}

	/**
	 * Gets the elected box that is the last box from the box working set.
	 *
	 * @return the last box from the working set.
	 */
	public Box getElectedBox() {
		return this.workingBoxes.last();
	}

	/**
	 * The Class BoxItemCountComparator.
	 * Sort the boxes by number of items. 
	 * If they have the same number of items, sort the boxes by used space.
	 */
	private static class BoxItemCountComparator implements Comparator<Box> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(Box box1, Box box2) {
			int countComparison = Integer.compare(box1.getItemsCount(), box2.getItemsCount());
			if (countComparison == 0) {
				return Integer.compare(box1.getItemsSize(), box2.getItemsSize());
			}
			return countComparison;
		}

	}

}
